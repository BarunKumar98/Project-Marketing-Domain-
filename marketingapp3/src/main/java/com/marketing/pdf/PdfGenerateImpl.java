package com.marketing.pdf;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.marketing.entities.Lead;
import com.marketing.repositories.LeadRepository;

@Service
@Transactional
public class PdfGenerateImpl implements PdfGenarate{

	@Autowired
	private LeadRepository leadRepo;
	
	@Override
	public List<Lead> listAll() {
		return leadRepo.findAll(Sort.by("id").ascending());
	}

}
