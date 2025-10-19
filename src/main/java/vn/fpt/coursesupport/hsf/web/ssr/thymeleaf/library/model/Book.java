package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String name;
    private LocalDate publishDate;
    private String publisher;
    
    @ManyToMany(
    	fetch = FetchType.EAGER
    )
    protected List<Person> authors;
    
    @Column(name = "avail")
    private int available;

    public Book() {
    	authors = new ArrayList<>();
    }
    
    public Book(String name) {
    	this();
    	setName(name);
    }
    
    public Book(String name, int avail) {
    	this(name);
    	setAvailable(avail);
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
    
    public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public List<Person> getAuthors() {
        return authors;
    }

    public void addAuthor(Person author) {
        authors.add(author);
        author.writtenBooks.add(this);
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

	public void decrease(int i) {
		available -= i;
	}
	
	public void increase(int i) {
		available += i;
	}

	public boolean isAvailable() {
		return available>0;
	}
}