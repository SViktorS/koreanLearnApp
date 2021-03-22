package com.myapp.koreanLearnWebApp.Word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WordsController {
	
	@Autowired
	private WordService wordService;
	
	
	@GetMapping("/vocbook/{vocbookId}")
    public String showUserList(Model model, @PathVariable int vocbookId) {
        model.addAttribute("words", wordService.displayWordsByVocBookId(vocbookId));
        return "vocbook";
    }
}
