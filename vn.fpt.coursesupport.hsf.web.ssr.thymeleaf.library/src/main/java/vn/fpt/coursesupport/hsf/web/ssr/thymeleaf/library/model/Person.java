package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dob;
    private String nationality;
    
    @ManyToMany(
    	fetch = FetchType.EAGER, 
    	mappedBy = "authors"
    )
    protected List<Book> writtenBooks;

    public Person() {
    	writtenBooks = new ArrayList<>();
    }
    
    public Person(String name) {
    	this();
    	setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void addWrittenBook(Book book) {
        writtenBooks.add(book);
        book.authors.add(this);
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj==null || !(obj instanceof Person)) return false;
    	Person p = (Person) obj;
    	return name.compareTo(p.getName()) == 0;
    }
}
