package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.LoanDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@Controller
@RequestMapping("/loans")
public class LoanController {

	@Autowired
	private final LibraryService libraryService;

	public LoanController(LibraryService userService) {
		this.libraryService = userService;
	}
	
	@PostMapping
    public String save(@ModelAttribute("newLoan") LoanDTO loanDTO) {
        libraryService.saveLoan(loanDTO);
        return "redirect:/loans";
    }
    
	@GetMapping
	public String getAllLoans(Model model) {
		model.addAttribute("loans", libraryService.getAllLoans());
		model.addAttribute("users", libraryService.getAllUsers());
		model.addAttribute("books", libraryService.getAllBooks());
		model.addAttribute("newLoan", new LoanDTO());
		return "loans";
	}
	
	@PostMapping("/return/{id}")
    public String returnBook(@PathVariable long id) {
        libraryService.returnBook(id);
        return "redirect:/loans";
    }
}
