package com.myapp.koreanLearnWebApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.koreanLearnWebApp.model.Word;

@Repository
public interface WordRepository extends CrudRepository<Word, Integer> {

	public List<Word> findByVocBookId(int vocBookId);
	
	public List<Word> findByVocBookIdAndPracticeAnswer(int vocBookId, String practiceAnswer);
	
}
