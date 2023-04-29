package com.myapp.koreanLearnWebApp.service;

import com.myapp.koreanLearnWebApp.model.VocBook;
import com.myapp.koreanLearnWebApp.model.Word;
import com.myapp.koreanLearnWebApp.repository.WordRepository;
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

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private VocBookService vocBookService;

    @Value("classpath:static/csvFiles/providedVocBookWords.csv")
    Resource resource;

    public List<Word> getWordsByVocBookId(int vocBookId) {
        return (List<Word>) wordRepository.findByVocBookId(vocBookId);
    }

    public Optional<Word> getWordById(int wordId) {
        return wordRepository.findById(wordId);
    }

    public List<Word> getWordsByVocBookIdAndPracticeAnswer(int vocBookId, String practiceAnswer) {
        return (List<Word>) wordRepository.findByVocBookIdAndPracticeAnswer(vocBookId,
            practiceAnswer);
    }

    public List<Word> getWordsByEnglishWordAndKoreanWordAndVocBookId(
        String englishWord, String koreanWord, int vocBookId) {
        return wordRepository.findByEnglishWordAndKoreanWordAndVocBookId(
            englishWord, koreanWord, vocBookId);
    }

    public Word storeWordInDatabase(Word word) {
        return wordRepository.save(word);
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
                if (getWordsByEnglishWordAndKoreanWordAndVocBookId(
                    englishWord, koreanWord, Integer.parseInt(vocBookId))
                    .isEmpty()) {
                    VocBook book = vocBookService.getOneVocBook(Integer.parseInt(vocBookId)).get();
                    book.incNumberWords();
                    vocBookService.storeVocBookInDatabase(book);
                    wordRepository.save(
                        new Word(englishWord, koreanWord, Integer.parseInt(vocBookId)));
                }
            }
        } catch (IOException e) {
            System.err.print("File could not be opened");
            e.printStackTrace();
        }
    }
}
