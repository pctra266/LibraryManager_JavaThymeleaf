package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Book;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Person;

public class BookDTO {
    private Long id;
    private String name;
    private String publishDate;
    private String publisher;
    private List<Long> authorIdList;
    private List<String> authorNameList;
    private int available;

    public BookDTO() {
    	authorIdList = new ArrayList<>();
    	authorNameList = new ArrayList<>();
    }

	public static BookDTO createDTOfromEntity(Book book) {
		BookDTO dto = new BookDTO();
		dto.setId(book.getId());
		dto.setName(book.getName());
		dto.setAvailable(book.getAvailable());
		dto.setPublishDate(book.getPublishDate().toString());
		dto.setPublisher(book.getPublisher());
		
		for (Person author: book.getAuthors()) {
			dto.addAuthorId(author.getId());
			dto.addAuthorName(author.getName());
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public List<Long> getAuthorIdList() {
        return authorIdList;
    }

    public void addAuthorId(long id) {
    	authorIdList.add(id);
    }
        
    public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
    
    public void addAuthorName(String authorName) {
    	authorNameList.add(authorName);
    }
    
    public List<String> getAuthorNameList() {
    	return authorNameList;
    }

	public void setAuthorIdList(List<Long> authorIdList) {
		this.authorIdList = authorIdList;
	}

	public void setAuthorNameList(List<String> authorNameList) {
		this.authorNameList = authorNameList;
	}    
    
}