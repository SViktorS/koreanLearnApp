package com.myapp.koreanLearnWebApp.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
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

    public void incNumberWords() {
        this.numberWords += 1;
    }

    public void incCurrentProgress() {
        this.currentProgress += 1;
    }

    public void incTimesPracticed() {
        this.timesPracticed += 1;
    }

    public void incRightAnswersAllWords() {
        this.rightAnswersAllWords += 1;
    }

    public void incWrongAnswersAllWords() {
        this.wrongAnswersAllWords += 1;
    }

    public void setLastPracticeDate() {
        this.lastPracticeDate = new Date();
    }
}
