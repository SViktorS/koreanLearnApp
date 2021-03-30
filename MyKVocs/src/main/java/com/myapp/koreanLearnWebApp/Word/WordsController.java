package com.myapp.koreanLearnWebApp.Word;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.koreanLearnWebApp.VocBook.VocBookService;

@Controller
public class WordsController {
	
	@Autowired
	private WordService wordService;
	
	@Autowired
	private VocBookService vocBookService;
	
	
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
		if(!vocbookList.isEmpty()) {
			Collections.shuffle(vocbookList);
			model.addAttribute("wordForPractice", vocbookList.get(0));
			model.addAttribute("progressPractice", (vocbookListOnlyForSize.size()-vocbookList.size())+1);
		}
		else {
			//TODO
		}
        model.addAttribute("vocbookSize", vocbookListOnlyForSize.size());
        model.addAttribute("vocbookInfo", vocBookService.getOneVocBook(vocbookId).get());
        return "practice";
    }
	
	@PostMapping("/vocbook/{vocbookId}/practice/{wordId}") 
	public String practiceWordRegisterAnswer(@RequestParam("answer") String answer, Model model, @PathVariable int wordId) {
        Word w = wordService.getWordById(wordId).get();
        w.setPracticeAnswer(answer);
        wordService.storeWord(w);
        return "redirect:/vocbook/{vocbookId}/practice";
	}
}
