package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Book;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Person;

public class AuthorDTO {
	private Long id;
	private String name;
	private String dob;
	private String nationality;
	private List<Long> bookIdList;
	private List<String> bookNameList;

	public AuthorDTO() {
		bookIdList = new ArrayList<>();
		bookNameList = new ArrayList<>();
	}

	public static AuthorDTO createDTOfromEntity(Person author) {
		AuthorDTO dto = new AuthorDTO();
		dto.setId(author.getId());
		dto.setName(author.getName());
		dto.setDob(author.getDob().toString());
		dto.setNationality(author.getNationality());
		
		for (Book book : author.getWrittenBooks()) {
			dto.addBookId(book.getId());
			dto.addBookName(book.getName());
		}
		return dto;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void addBookId(long id) {
		if (!bookIdList.contains(id))
			bookIdList.add(id);
	}

	public List<Long> getBookIdList() {
		return bookIdList;
	}

	public void addBookName(String name) {
		bookNameList.add(name);
	}

	public List<String> getBookNameList() {
		return bookNameList;
	}

	public void setBookIdList(List<Long> bookIdList) {
		this.bookIdList = bookIdList;
	}

	public void setBookNameList(List<String> bookNameList) {
		this.bookNameList = bookNameList;
	}
	
}