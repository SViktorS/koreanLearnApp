package com.myapp.koreanLearnWebApp.VocBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VocBookController {

	@Autowired
	private VocBookService vocBookService;
	
	
	@GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("vocbooks", vocBookService.displayVocBooks());
        return "index";
    }
	
}
