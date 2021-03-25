package com.myapp.koreanLearnWebApp.Word;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class WordService {
	
	@Autowired
	private WordRepository wordRepository;
	
	@Value("classpath:csvFiles/providedVocBookWords.csv")
	Resource resource;

	public List<Word> displayWordsByVocBookId(int vocBookId) {
		return (List<Word>) wordRepository.findByVocBookId(vocBookId);
	}
	
	@PostConstruct
	public void createWords() {
		File csvFile;
		try {
			csvFile = resource.getFile();
			Reader in = new FileReader(csvFile);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
			    String englishWord = record.get("englishWord");
			    String koreanWord = record.get("koreanWord");
			    String vocBookId = record.get("vocBookId");
			    wordRepository.save(new Word(englishWord, koreanWord, Integer.parseInt(vocBookId))); 
			}
		} catch (IOException e) {
			System.err.print("File could not be opened");
			e.printStackTrace();
		}
	}

	
	
}
