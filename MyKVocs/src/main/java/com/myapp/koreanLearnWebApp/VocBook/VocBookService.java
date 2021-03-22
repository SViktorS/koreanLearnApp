package com.myapp.koreanLearnWebApp.VocBook;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VocBookService {

	@Autowired
	private VocBookRepository vocBookRepository;
	
	public List<VocBook> displayEntities() {
		return (List<VocBook>) vocBookRepository.findAll();
	}
	
	@PostConstruct
	public void createEntities() {
		vocBookRepository.save(new VocBook("Lesson 1", "This is the first Lesson"));
		vocBookRepository.save(new VocBook("Lesson 2", "This is the second Lesson"));
	}

	
}
