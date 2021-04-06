package com.myapp.koreanLearnWebApp.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.myapp.koreanLearnWebApp.model.VocBook;
import com.myapp.koreanLearnWebApp.model.Word;
import com.myapp.koreanLearnWebApp.repository.VocBookRepository;

@Service
public class VocBookService {

	@Autowired
	private VocBookRepository vocBookRepository;
	
	@Value("classpath:static/csvFiles/providedVocBooks.csv")
	Resource resource;
	
	public List<VocBook> getAllVocBooks() {
		return (List<VocBook>) vocBookRepository.findAll();
	}
	
	public Boolean checkForexistanceByName(String name) {
		return vocBookRepository.existsByName(name);
	}
	
	public Optional<VocBook> getOneVocBook(int id) {
		return vocBookRepository.findById(id);
	}
	
	public VocBook storeVocBookInDatabase(VocBook vocBook) {
		return vocBookRepository.save(vocBook);
	}
	
	@PostConstruct
	public void createVocBooks() {
		File csvFile;
		try {
			csvFile = resource.getFile();
			Reader in = new FileReader(csvFile);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
			    String name = record.get("name");
			    if(!checkForexistanceByName(name)) {
			    	vocBookRepository.save(new VocBook(name));
			    }
			}
		} catch (IOException e) {
			System.err.print("File could not be opened");
			e.printStackTrace();
		}
	}

	
}
