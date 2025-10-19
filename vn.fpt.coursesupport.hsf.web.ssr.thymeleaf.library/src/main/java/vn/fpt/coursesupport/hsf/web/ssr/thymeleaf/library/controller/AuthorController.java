package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.AuthorDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@Controller
@RequestMapping("/authors")
public class AuthorController {
	
	@Autowired
	private final LibraryService libraryService;

	public AuthorController(LibraryService authorService) {
		this.libraryService = authorService;
	}

    @PostMapping
    public String save(@ModelAttribute("newAuthor") AuthorDTO authorDTO) {
        libraryService.saveAuthor(authorDTO);
        return "redirect:/authors";
    }
    
	@GetMapping
	public String getAllAuthors(Model model) {
		model.addAttribute("authors", libraryService.getAllAuthors());
		model.addAttribute("books", libraryService.getAllBooks());
		model.addAttribute("newAuthor", new AuthorDTO());
		return "authors";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteAuthor(@PathVariable Long id) {
		libraryService.removeAuthor(id);
		return "redirect:/authors";
	}
}