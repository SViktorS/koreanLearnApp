package com.myapp.koreanLearnWebApp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
public class VocBook {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int vocBookId;
	
	private String name;
	
	private int numberWords;
	
	private int currentProgress;
	
	private int timesPracticed;
	
	private int rightAnswersAllWords;
	
	private int wrongAnswersAllWords;
	
	private float bestResult;
	
	@Temporal(TemporalType.DATE)
	private Date lastPracticeDate;
	
	private boolean isDeskVocBook;
	
	private boolean isHiddenVocBook;
	
	public VocBook() {}

	public VocBook(String name) {
		super();
		this.name = name;
		this.numberWords = 0;
		this.currentProgress = 0;
		this.timesPracticed = 0;
		this.rightAnswersAllWords = 0;
		this.wrongAnswersAllWords = 0;
		this.bestResult = 0.0f;
		this.isDeskVocBook = false;
		this.isHiddenVocBook = false;
	}

	@Override
	public String toString() {
		return "VocBook [vocBookId=" + vocBookId + ", name=" + name + "]";
	}

	public int getVocBookId() {
		return vocBookId;
	}

	public void setVocBookId(int vocBookId) {
		this.vocBookId = vocBookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberWords() {
		return numberWords;
	}
	
	public void incNumberWords() {
		this.numberWords+=1;
	}
	
	public void incCurrentProgress() {
		this.currentProgress+=1;
	}
	
	public void incTimesPracticed() {
		this.timesPracticed+=1;
	}
	
	public void incRightAnswersAllWords() {
		this.rightAnswersAllWords+=1;
	}
	
	public void incWrongAnswersAllWords() {
		this.wrongAnswersAllWords+=1;
	}

	public void setNumberWords(int numberWords) {
		this.numberWords = numberWords;
	}

	public int getCurrentProgress() {
		return currentProgress;
	}

	public void setCurrentProgress(int currentProgress) {
		this.currentProgress = currentProgress;
	}

	public int getTimesPracticed() {
		return timesPracticed;
	}

	public void setTimesPracticed(int timesPracticed) {
		this.timesPracticed = timesPracticed;
	}

	public int getRightAnswersAllWords() {
		return rightAnswersAllWords;
	}

	public void setRightAnswersAllWords(int rightAnswersAllWords) {
		this.rightAnswersAllWords = rightAnswersAllWords;
	}

	public int getWrongAnswersAllWords() {
		return wrongAnswersAllWords;
	}

	public void setWrongAnswersAllWords(int wrongAnswersAllWords) {
		this.wrongAnswersAllWords = wrongAnswersAllWords;
	}

	public float getBestResult() {
		return bestResult;
	}

	public void setBestResult(float bestResult) {
		this.bestResult = bestResult;
	}
	
	public void setLastPracticeDate() {
		this.lastPracticeDate = new Date();
	}
	
	public Date getLastPracticeDate() {
		return this.lastPracticeDate;
	}

	public boolean isDeskVocBook() {
		return isDeskVocBook;
	}

	public void setDeskVocBook(boolean isDeskVocBook) {
		this.isDeskVocBook = isDeskVocBook;
	}

	public boolean isHiddenVocBook() {
		return isHiddenVocBook;
	}

	public void setHiddenVocBook(boolean isHiddenVocBook) {
		this.isHiddenVocBook = isHiddenVocBook;
	}
}
