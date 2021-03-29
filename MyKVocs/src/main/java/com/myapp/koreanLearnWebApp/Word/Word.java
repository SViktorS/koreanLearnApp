package com.myapp.koreanLearnWebApp.Word;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Word {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	private int wordId;
	
	private String englishWord;
	
	private String koreanWord;
	
	private String practiceAnswer;
	
	private boolean skipped;
	
	private int vocBookId;
	
	public Word() {}
	
	public Word(String englishWord, String koreanWord, int vocBookId) {
		this.englishWord = englishWord;
		this.koreanWord = koreanWord;
		this.vocBookId = vocBookId;
		this.practiceAnswer = "";
		this.skipped = false;
	}
	
	public String getEnglishWord() {
		return englishWord;
	}
	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}
	public String getKoreanWord() {
		return koreanWord;
	}
	public void setKoreanWord(String koreanWord) {
		this.koreanWord = koreanWord;
	}
	public int getWordId() {
		return wordId;
	}
	public void setId(int wordId) {
		this.wordId = wordId;
	}

	@Override
	public String toString() {
		return "Word [wordId=" + wordId + ", englishWord=" + englishWord + ", koreanWord=" + koreanWord + "]";
	}

	public int getVocBookId() {
		return vocBookId;
	}

	public void setVocBookId(int vocBookId) {
		this.vocBookId = vocBookId;
	}

	public String getPracticeAnswer() {
		return practiceAnswer;
	}

	public void setPracticeAnswer(String practiceAnswer) {
		this.practiceAnswer = practiceAnswer;
	}

	public boolean isSkipped() {
		return skipped;
	}

	public void setSkipped(boolean skipped) {
		this.skipped = skipped;
	}
}
