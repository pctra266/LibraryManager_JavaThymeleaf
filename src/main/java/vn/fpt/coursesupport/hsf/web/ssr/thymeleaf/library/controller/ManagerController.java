package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.ManagerDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service.LibraryService;

@Controller
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	private final LibraryService libraryService;
	
	public ManagerController(LibraryService service) {
		this.libraryService = service;
	}
	
	@GetMapping("/{id}")
	public String login(@PathVariable Long id, Model model) {
		ManagerDTO managerDTO = libraryService.login(id);
		model.addAttribute("manager", managerDTO);
		return "manager";
	}
}
