package com.myapp.koreanLearnWebApp.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.koreanLearnWebApp.model.Word;
import com.myapp.koreanLearnWebApp.service.VocBookService;
import com.myapp.koreanLearnWebApp.service.WordService;

@Controller
public class MainController {
	
	@Autowired
	private WordService wordService;
	
	@Autowired
	private VocBookService vocBookService;
	
	@GetMapping("/")
    public String displayAllVocBooks(Model model) {
        model.addAttribute("vocbooks", vocBookService.getAllVocBooks());
        return "index";
    }
	
	@GetMapping("/vocbook/{vocbookId}")
    public String displayWordsOfSpecificVocbook(Model model, @PathVariable int vocbookId) {
		model.addAttribute("vocbookInfo", vocBookService.getOneVocBook(vocbookId).get());
        model.addAttribute("words",  wordService.getWordsByVocBookId(vocbookId));
        return "vocbook";
    }
	
	@GetMapping("/vocbook/{vocbookId}/practice")
    public String practiceWordOfSpecificVocbook(Model model, @PathVariable int vocbookId) {
		List<Word> vocbookList = wordService.getWordsByVocBookIdAndPracticeAnswer(vocbookId, "");
		List<Word> vocbookListOnlyForSize = wordService.getWordsByVocBookId(vocbookId);
		model.addAttribute("vocbookSize", vocbookListOnlyForSize.size());
        model.addAttribute("vocbookInfo", vocBookService.getOneVocBook(vocbookId).get());
		if(!vocbookList.isEmpty()) {
			Collections.shuffle(vocbookList);
			model.addAttribute("wordForPractice", vocbookList.get(0));
			model.addAttribute("progressPractice", (vocbookListOnlyForSize.size()-vocbookList.size())+1);
		}
		else {
			model.addAttribute("allWordsOfVocbook", vocbookListOnlyForSize);
			return "totalResults";
		}
        return "practice";
    }
	
	@GetMapping("/vocbook/{vocbookId}/practice/result/{wordId}")
    public String showPracticeResultOfSpecificWord(Model model, @PathVariable int vocbookId, @PathVariable int wordId) {
		Word w = wordService.getWordById(wordId).get();
		boolean correctAnswer;
		if(w.getKoreanWord() == w.getPracticeAnswer()) {
			correctAnswer = true;
		}
		else {
			correctAnswer = false;
		}
		model.addAttribute("correctAnswer", correctAnswer);
		List<Word> vocbookList = wordService.getWordsByVocBookIdAndPracticeAnswer(vocbookId, "");
		List<Word> vocbookListOnlyForSize = wordService.getWordsByVocBookId(vocbookId);
		model.addAttribute("vocbookSize", vocbookListOnlyForSize.size());
        model.addAttribute("vocbookInfo", vocBookService.getOneVocBook(vocbookId).get());
        model.addAttribute("wordForPractice", w);
		if(!vocbookList.isEmpty()) {
			model.addAttribute("progressPractice", (vocbookListOnlyForSize.size()-vocbookList.size()));
		}
		else {
			model.addAttribute("progressPractice", (vocbookListOnlyForSize.size()));
		}
        return "result";
    }
	
	@PostMapping("/vocbook/{vocbookId}/practice/{wordId}")
	public String registerPracticeWordAnswer(@RequestParam("answer") String answer, Model model, @PathVariable int wordId, @PathVariable int vocbookId) {
        Word w = wordService.getWordById(wordId).get();
        w.setPracticeAnswer(answer);
        wordService.storeWordInDatabase(w);
        return "redirect:/vocbook/{vocbookId}/practice/result/{wordId}";
	}
	
	@PostMapping("/deleteAnswers/{vocbookId}")
	public String deleteAnswersOfAllWordsInSpecificVocbook(Model model, @PathVariable int vocbookId) {
		List<Word> words = wordService.getWordsByVocBookId(vocbookId);
		for(Word word : words) {
			word.setPracticeAnswer("");
			wordService.storeWordInDatabase(word);
		}
        return "redirect:/vocbook/{vocbookId}/practice";
	}
}
