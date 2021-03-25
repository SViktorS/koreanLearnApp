package com.myapp.koreanLearnWebApp.VocBook;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.myapp.koreanLearnWebApp.Word.Word;

@Service
public class VocBookService {

	@Autowired
	private VocBookRepository vocBookRepository;
	
	@Value("classpath:csvFiles/providedVocBooks.csv")
	Resource resource;
	
	public List<VocBook> displayVocBooks() {
		return (List<VocBook>) vocBookRepository.findAll();
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
			    String description = record.get("description");
			    vocBookRepository.save(new VocBook(name, description)); 
			}
		} catch (IOException e) {
			System.err.print("File could not be opened");
			e.printStackTrace();
		}
	}

	
}
