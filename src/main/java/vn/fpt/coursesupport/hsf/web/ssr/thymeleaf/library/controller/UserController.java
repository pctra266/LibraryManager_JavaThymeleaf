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
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.UserDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private final LibraryService libraryService;

	public UserController(LibraryService userService) {
		this.libraryService = userService;
	}

    @PostMapping
    public String save(@ModelAttribute("newUser") UserDTO userDTO) {
        libraryService.saveUser(userDTO);
        return "redirect:/users";
    }
    
	@GetMapping
	public String getAllUsers(Model model) {
		model.addAttribute("users", libraryService.getAllUsers());
		model.addAttribute("newUser", new UserDTO());
		return "users";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		libraryService.removeUser(id);
		return "redirect:/users";
	}
}
