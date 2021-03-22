package com.myapp.koreanLearnWebApp.Word;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {
	
	@Autowired
	private WordRepository wordRepository;

	public List<Word> displayWordsByVocBookId(int vocBookId) {
		return (List<Word>) wordRepository.findByVocBookId(vocBookId);
	}
	
	@PostConstruct
	public void createEntities() {
		wordRepository.save(new Word("Korea", "한국", 1));
		wordRepository.save(new Word("City", "도시", 1));
		wordRepository.save(new Word("Name", "이름", 1));
		wordRepository.save(new Word("I, me (formal)", "저", 1));
		wordRepository.save(new Word("I, me (informal)", "나", 1));
		wordRepository.save(new Word("Man", "남자", 1));
		wordRepository.save(new Word("Woman", "여자", 1));
		wordRepository.save(new Word("This", "이", 1));
		wordRepository.save(new Word("That", "그", 1));
		wordRepository.save(new Word("That (when something is far away)", "저", 1));
		wordRepository.save(new Word("Thing", "것", 1));
		wordRepository.save(new Word("This (thing)", "이것", 1));
		wordRepository.save(new Word("That (thing)", "그것", 1));
		wordRepository.save(new Word("That (thing) (far away)", "저것", 1));
		wordRepository.save(new Word("Chair", "의자", 1));
		wordRepository.save(new Word("Table", "탁자", 1));
		wordRepository.save(new Word("Teacher", "선생님", 1));
		wordRepository.save(new Word("Bed", "침대", 1));
		wordRepository.save(new Word("House", "집", 1));
		wordRepository.save(new Word("Car", "차", 1));
		wordRepository.save(new Word("Person", "사람", 1));
		wordRepository.save(new Word("Book", "책", 1));
		wordRepository.save(new Word("Computer", "컴퓨터", 1));
		wordRepository.save(new Word("Tree/Wood", "나무", 1));
		wordRepository.save(new Word("Sofa", "소파", 1));
		wordRepository.save(new Word("China", "중국", 1));
		wordRepository.save(new Word("Japan", "일본", 1));
		wordRepository.save(new Word("Door", "문", 1));
		wordRepository.save(new Word("Doctor", "의사", 1));
		wordRepository.save(new Word("Student", "학생", 1));
		wordRepository.save(new Word("To be", "이다", 1));
		wordRepository.save(new Word("Yes", "네", 1));
		wordRepository.save(new Word("No", "아니", 1));
		wordRepository.save(new Word("TEST", "TEST", 2));
	}

	
	
}
