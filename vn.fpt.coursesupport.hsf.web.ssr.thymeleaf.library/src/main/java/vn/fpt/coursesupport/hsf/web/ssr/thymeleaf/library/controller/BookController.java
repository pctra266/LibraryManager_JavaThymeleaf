package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.BookDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private final LibraryService libraryService;

	public BookController(LibraryService bookService) {
		this.libraryService = bookService;
	}

    @PostMapping
    public String save(@ModelAttribute("newBook") BookDTO bookDTO) {
        libraryService.saveBook(bookDTO);
        return "redirect:/books";
    }
    
	@GetMapping
	public String getAllBooks(Model model) {
		model.addAttribute("authors", libraryService.getAllAuthors());
		model.addAttribute("books", libraryService.getAllBooks());
		model.addAttribute("newBook", new BookDTO());
		return "books";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		libraryService.removeBook(id);
		return "redirect:/books";
	}
}
