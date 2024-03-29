package com.myapp.koreanLearnWebApp.controller;

import com.myapp.koreanLearnWebApp.model.VocBook;
import com.myapp.koreanLearnWebApp.model.Word;
import com.myapp.koreanLearnWebApp.service.VocBookService;
import com.myapp.koreanLearnWebApp.service.WordService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private WordService wordService;

    @Autowired
    private VocBookService vocBookService;

    @GetMapping("/")
    public String displayAllVocBooks(Model model) {
        List<VocBook> vocBooks = vocBookService.getAllVocBooks();
        int lessonProgress = calculateLessonPracticeProgress(vocBooks);
        model.addAttribute("lessonProgress", lessonProgress);
        model.addAttribute("totalNumberOfLessons", vocBooks.size());
        model.addAttribute("vocbooks", vocBookService.getAllVocBooks());
        return "index";
    }

    private int calculateLessonPracticeProgress(List<VocBook> vocBooks) {
        int lessonProgress = 0;
        for (VocBook vocBook : vocBooks) {
            if (vocBook.isDeskVocBook() || vocBook.isHiddenVocBook()) {
                lessonProgress += 1;
            }
        }
        return lessonProgress;
    }

    @GetMapping("/vocbook/{vocbookId}")
    public String displayWordsOfSpecificVocbook(Model model, @PathVariable int vocbookId) {
        model.addAttribute("vocbookInfo", vocBookService.getOneVocBook(vocbookId).get());
        model.addAttribute("words", wordService.getWordsByVocBookId(vocbookId));
        return "vocbook";
    }

    @GetMapping("/vocbook/{vocbookId}/statistics")
    public String displayStatisticsOfSpecificVocbook(Model model, @PathVariable int vocbookId) {
        model.addAttribute("vocbookInfo", vocBookService.getOneVocBook(vocbookId).get());
        model.addAttribute("words", wordService.getWordsByVocBookId(vocbookId));
        return "statistics";
    }

    @GetMapping("/vocbook/{vocbookId}/practice")
    public String practiceWordOfSpecificVocbook(Model model, @PathVariable int vocbookId) {
        List<Word> vocbookList = wordService.getWordsByVocBookIdAndPracticeAnswer(vocbookId, "");
        List<Word> vocbookListForTotalResults = wordService.getWordsByVocBookId(vocbookId);
        model.addAttribute("vocbookInfo", vocBookService.getOneVocBook(vocbookId).get());
        if (!vocbookList.isEmpty()) {
            Collections.shuffle(vocbookList);
            model.addAttribute("wordForPractice", vocbookList.get(0));
        } else {
            model.addAttribute("allWordsOfVocbook", vocbookListForTotalResults);
            return "totalResults";
        }
        return "practice";
    }

    @GetMapping("/vocbook/{vocbookId}/practice/result/{wordId}")
    public String showPracticeResultOfSpecificWord(
        Model model, @PathVariable int vocbookId, @PathVariable int wordId) {
        Word w = wordService.getWordById(wordId).get();
        boolean correctAnswer;
        if (w.getKoreanWord() == w.getPracticeAnswer()) {
            correctAnswer = true;
        } else {
            correctAnswer = false;
        }
        model.addAttribute("correctAnswer", correctAnswer);
        model.addAttribute("vocbookInfo", vocBookService.getOneVocBook(vocbookId).get());
        model.addAttribute("wordForPractice", w);
        return "result";
    }

    @PostMapping("/vocbook/{vocbookId}/practice/{wordId}")
    public String registerPracticeWordAnswerAndUpdateStatistics(
        @RequestParam("answer") String answer,
        Model model,
        @PathVariable int wordId,
        @PathVariable int vocbookId) {
        VocBook vocBook = vocBookService.getOneVocBook(vocbookId).get();
        Word w = wordService.getWordById(wordId).get();
        w.setPracticeAnswer(answer);
        if (w.getKoreanWord().equals(answer)) {
            w.incRightAnswers();
            vocBook.incRightAnswersAllWords();
        } else {
            w.incWrongAnswers();
            vocBook.incWrongAnswersAllWords();
        }
        vocBook.incCurrentProgress();
        vocBook.setLastPracticeDate();
        if (vocBook.getCurrentProgress() == vocBook.getNumberWords()) {
            vocBook.incTimesPracticed();
            vocBook = setBestResultAndSuccessPercentageOfASpecificVocbook(vocBook);
        }
        vocBookService.storeVocBookInDatabase(vocBook);
        wordService.storeWordInDatabase(w);
        return "redirect:/vocbook/{vocbookId}/practice/result/{wordId}";
    }

    private VocBook setBestResultAndSuccessPercentageOfASpecificVocbook(VocBook vocBook) {
        int vocBookRight = vocBook.getRightAnswersAllWords();
        int vocBookWrong = vocBook.getWrongAnswersAllWords();
        float percentageRight;
        if (vocBookRight + vocBookWrong == 0) {
            percentageRight = 0.0f;
        } else {
            percentageRight = vocBookRight * 100 / (vocBookRight + vocBookWrong);
        }
        if (percentageRight > vocBook.getBestResult()) {
            vocBook.setBestResult(percentageRight);
        }
        return vocBook;
    }

    @PostMapping("/deleteAnswers/{vocbookId}")
    public String deleteAnswersAndResetCurrentProgressOfAllWordsInSpecificVocbook(
        Model model, @PathVariable int vocbookId) {
        VocBook vocBook = vocBookService.getOneVocBook(vocbookId).get();
        vocBook.setCurrentProgress(0);
        vocBook.setRightAnswersAllWords(0);
        vocBook.setWrongAnswersAllWords(0);
        vocBookService.storeVocBookInDatabase(vocBook);
        List<Word> words = wordService.getWordsByVocBookId(vocbookId);
        for (Word word : words) {
            word.setPracticeAnswer("");
            wordService.storeWordInDatabase(word);
        }
        return "redirect:/vocbook/{vocbookId}/practice";
    }

    @PostMapping("/vocbookIntoDrawer/{vocbookId}")
    public String putSpecificVocbookIntoDeskDrawer(Model model, @PathVariable int vocbookId) {
        VocBook vocBook = vocBookService.getOneVocBook(vocbookId).get();
        vocBook.setHiddenVocBook(true);
        vocBook.setDeskVocBook(false);
        vocBookService.storeVocBookInDatabase(vocBook);
        return "redirect:/";
    }

    @PostMapping("/vocbookOnDesk/{vocbookId}")
    public String putSpecificVocbookOnDesk(Model model, @PathVariable int vocbookId) {
        VocBook vocBook = vocBookService.getOneVocBook(vocbookId).get();
        vocBook.setHiddenVocBook(false);
        vocBook.setDeskVocBook(true);
        vocBookService.storeVocBookInDatabase(vocBook);
        return "redirect:/";
    }

    @PostMapping("/addNewLesson/")
    public String addNewLessonToLearn(Model model) {
        List<VocBook> vocBooks = vocBookService.getAllVocBooks();
        for (VocBook vocBook : vocBooks) {
            if (!vocBook.isDeskVocBook() && !vocBook.isHiddenVocBook()) {
                vocBook.setDeskVocBook(true);
                vocBookService.storeVocBookInDatabase(vocBook);
                break;
            }
        }
        return "redirect:/";
    }
}
