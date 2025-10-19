package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "libraries")
public class Library {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String address;

	@OneToMany(
		fetch = FetchType.EAGER,
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<User> allUsers;
	
	@OneToMany(
		fetch = FetchType.EAGER,
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Book> allBooks;
	
	@OneToMany(
		fetch = FetchType.EAGER,
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Person> allAuthors;
	
	@OneToMany(
		fetch = FetchType.EAGER,
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Manager> allManagers;

	@Transient
	private Manager currentManager;
	
	public Library() {
		allUsers = new ArrayList<>();
		allManagers = new ArrayList<>();
		allBooks = new ArrayList<>();
		allAuthors = new ArrayList<>();
	}

	public Library(String name) {
		this();
		setName(name);
	}

	public Library(String name, String address) {
		this(name);
		setAddress(address);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User createUser(String name) {
		User user = new User(name);
		allUsers.add(user);
		return user;
	}
	
	public Manager createManager(String name) {
		Manager manager = new Manager(name);
		allManagers.add(manager);
		return manager;
	}
	
	public Manager createManager(String name, LocalDate dob) {
		Manager manager = new Manager(name);
		manager.setDob(dob);
		allManagers.add(manager);
		return manager;
	}

	public Book createBook(String name) {
		Book book = new Book(name);
		allBooks.add(book);
		return book;
	}
	
	public Book createBook(String name, LocalDate publishDate, int avail, String publisher) {
		Book book = new Book(name);
		book.setAvailable(avail);
		book.setPublishDate(publishDate);
		book.setPublisher(publisher);
		allBooks.add(book);
		return book;
	}

	public Person createAuthor(String name) {
		Person author = new Person(name);
		allAuthors.add(author);
		return author;
	}
	
	public User getUser(long id) {
		for (User user : allUsers) {
			if (user.getId() == id) return user;
		}
		return null;
	}
	
	public void removeUser(long id) {
		User user2Remove = null;
		for (User user: allUsers) {
			if (user.getId() == id) user2Remove = user;
		}
		allUsers.remove(user2Remove);
	}

	public Manager getManager(long id) {
		return allManagers.get((int)id);
	}
	
	public Person getAuthor(Long id) {
		for (Person author : allAuthors) {
			if (author.getId() == id) return author;
		}
		return null;
	}
	
	public Book getBook(long bookId) {
		for (Book book : allBooks) {
			if (book.getId() == bookId) return book;
		}
		return null;
	}
	
	public Loan getLoan(long id) {
		for (Loan loan : getAllLoans()) {
			if (loan.getId() == id) return loan;
		}
		return null;
	}

	public void removeAuthor(Person author) {
		allAuthors.remove(author);
	}
	
	public void removeBook(Book book) {
		allBooks.remove(book);
	}

	public void removeBook(Long id) {
		Book book2Remove = null;
		for (Book book : allBooks) {
			if (book.getId() == id) book2Remove = book;
		}
		if (book2Remove!=null)  allBooks.remove(book2Remove);
	}

	public void removeAuthor(Long id) {
		Person authorToRemove = null;
		for (Person author : allAuthors) {
			if (author.getId() == id) {
				authorToRemove = author;
			}
		}
		if (authorToRemove!=null) removeAuthor(authorToRemove);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCurrentManager(Manager manager) {
		currentManager = manager;
	}
	
	public Manager getCurrentManager() {
		return currentManager;
	}
	
	public List<Manager> getAllManagers() {
		return allManagers;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}
	
	public List<Book> getAllBooks() {
		return allBooks;
	}

	public List<Person> getAllAuthors() {
		return allAuthors;
	}

	public List<Loan> getAllLoans() {
		List<Loan> allLoans = new ArrayList<>();
		for (Manager manager : allManagers) {
			allLoans.addAll(manager.getCreatedLoans());
		}
		return allLoans;
	}
}