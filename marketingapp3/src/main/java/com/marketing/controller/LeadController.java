package com.marketing.controller;

import java.util.List;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;
import com.marketing.pdf.PdfGenarate;
import com.marketing.pdf.UserPDFExporter;

import com.marketing.dto.LeadData;
import com.marketing.entities.Lead;
import com.marketing.services.LeadService;
import com.marketing.util.EmailService;

@Controller
public class LeadController {

	// object of emailer it only work when we written @component in EmailServiceImpl class
	@Autowired
	private EmailService emailservice;
// Handler methods
	// http://localhost:8080/create
	@Autowired
	private LeadService leadservice;
	
	@Autowired
    private PdfGenarate service;

	// if we put
	@RequestMapping("/create")
	public String viewCreateLeadPage() {
		return "create_lead";
	}
//	@RequestMapping("/saveLead")
//	public String saveOneLead(@ModelAttribute("l")Lead lead,ModelMap model) {
//		//controller calling service layer
//		leadservice.saveLeadInfo(lead);
//		model.addAttribute("msg", "record is saved!!");
//		return "create_lead";
//	}

//	public String saveOneLead(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
//			@RequestParam("email") String email,@RequestParam("mobile") String mobile,ModelMap model){
//				Lead lead=new Lead();
//				lead.setFirstName(firstName);
//				lead.setLastName(lastName);
//				lead.setEmail(email);
//				lead.setMobile(mobile);
//				
//				leadservice.saveLeadInfo(lead);
//				model.addAttribute("msg", "record is saved!!");
//				return "create_lead";
//			}
	@RequestMapping("/saveLead")
	public String saveOneLead(LeadData leadData, ModelMap model) {
		Lead lead = new Lead();
		lead.setFirstName(leadData.getFirstName());
		lead.setLastName(leadData.getLastName());
		lead.setEmail(leadData.getEmail());
		lead.setMobile(leadData.getMobile());

		leadservice.saveLeadInfo(lead);
emailservice.sendEmail(lead.getEmail(),"Welcome to psa","Test email from springboot");
		model.addAttribute("msg", "record is saved!!");
		return "create_lead";

	}

	// http://localhost:8080/listAll
	@RequestMapping("/listAll")
	public String listAllLeads(Model model) {
		List<Lead> leads = leadservice.getLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}

	@RequestMapping("/delete")
	public String deleteoneLead(@RequestParam("id") long id, Model model) {
		leadservice.deleteLead(id);
		List<Lead> leads = leadservice.getLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}

	@RequestMapping("/update")
	public String getLeadInfo(@RequestParam("id") long id, Model model) {
		Lead lead = leadservice.getOneLead(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}

	@RequestMapping("/updateLead")
	public String updateLeadInfo(LeadData data, Model model) {
		Lead l = new Lead();
		l.setId(data.getId());
		l.setFirstName(data.getFirstName());
		l.setLastName(data.getLastName());
		l.setEmail(data.getEmail());
		l.setMobile(data.getMobile());

		leadservice.saveLeadInfo(l);
		List<Lead> leads = leadservice.getLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	@GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=listTable_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Lead> listUsers = service.listAll();
         
        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
         
    }
}
