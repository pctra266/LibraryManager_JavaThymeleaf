package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.AuthorDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.BookDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.LoanDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.ManagerDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto.UserDTO;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Book;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Library;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Loan;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Manager;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Person;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.User;
import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.repository.ILibraryRepository;

@Service
public class LibraryService {

	private Library library = null;

	@Autowired
	private final ILibraryRepository libraryRepository;

	public LibraryService(ILibraryRepository libRepo) {
		libraryRepository = libRepo;

		library = libraryRepository.findById(1L).orElse(null);
		if (library == null) createDefaultLibrary();
	}
	
	private void createDefaultLibrary() {
		library = new Library("FPT Library", "HOLA");
		Manager zondat = library.createManager("Zondat", LocalDate.parse("2002-12-12"));
		
		User datzon = library.createUser("Datzon");
		datzon.setBalance(250000);
		datzon.setDob(LocalDate.parse("2007-07-07"));
		datzon.setNationality("Việt Nam");
		datzon.createMembership(0, LocalDate.now().minusDays(7), LocalDate.now().plusMonths(1));
		
		Person romainGary = library.createAuthor("Romain Gary");
		romainGary.setDob(LocalDate.parse("1914-05-21"));
		romainGary.setNationality("Pháp");

		Person hectorMalot = library.createAuthor("Hector Malot");
		hectorMalot.setDob(LocalDate.parse("1830-05-20"));
		hectorMalot.setNationality("Pháp");
		
		Book racineDuCiel = library.createBook("Rễ của trời", 
								LocalDate.parse("1956-10-05"), 100, "Folio");
		
		Book promessdelaube = library.createBook("Lời hứa của rạng đông", 
								LocalDate.parse("1960-12-12"), 100, "Gallimard");
		
		Book sansFamille = library.createBook("Không gia đình", 
								LocalDate.parse("1878-12-12"), 100, "DongA");
		
		Book dansFamille = library.createBook("Trong gia đình", 
				LocalDate.parse("1893-12-12"), 100, "DongA");
		
		romainGary.addWrittenBook(racineDuCiel);
		romainGary.addWrittenBook(promessdelaube);
		hectorMalot.addWrittenBook(dansFamille);
		hectorMalot.addWrittenBook(sansFamille);
		
		zondat.lend(datzon, dansFamille, LocalDate.now(), LocalDate.now().plusMonths(1));
		libraryRepository.save(library);
	}
	
	@Transactional
	public ManagerDTO login(long id) {
		Manager manager = library.getManager(id);
		library.setCurrentManager(manager);
		return ManagerDTO.createDTOfromEntity(manager);
	}

	@Transactional
	public AuthorDTO saveAuthor(AuthorDTO dto) {
		Person author = library.createAuthor(dto.getName());
		author.setDob(LocalDate.parse(dto.getDob()));
		author.setNationality(dto.getNationality());
		for (long bookId : dto.getBookIdList()) {
			Book book = library.getBook(bookId);
			author.addWrittenBook(book);
		}
		
		saveAll();
		dto.setId(library.getAllAuthors().getLast().getId());
		return dto;
	}

	@Transactional
	public BookDTO saveBook(BookDTO dto) {
		Book newBook = library.createBook(dto.getName());
		newBook.setPublishDate(LocalDate.parse(dto.getPublishDate()));
		newBook.setAvailable(dto.getAvailable());
		newBook.setPublisher(dto.getPublisher());

		for (Long id : dto.getAuthorIdList()) {
			Person author = library.getAuthor(id);
			newBook.addAuthor(author);
		}

		saveAll();
		dto.setId(library.getAllBooks().getLast().getId());
		return dto;
	}
	
	@Transactional
	public UserDTO saveUser(UserDTO dto) {
		User user = library.createUser(dto.getName());
		user.setName(dto.getName());
		user.setDob(LocalDate.parse(dto.getDob()));
		user.setNationality(dto.getNationality());
		user.setBalance(dto.getBalance());
		user.setBanned(dto.isBanned());
		user.createMembership(dto.getMembership(), 
				LocalDate.now(), LocalDate.now().plusMonths(1));
		
		saveAll();
		dto.setId(library.getAllUsers().getLast().getId());
		return dto;
	}
	
	@Transactional
	public LoanDTO saveLoan(LoanDTO loanDTO) {		
		Manager currentManager = library.getCurrentManager();
		if (currentManager!=null) {
			Loan loan = currentManager.lend(library.getUser(loanDTO.getUserId()), library.getBook(loanDTO.getBookId()), 
					LocalDate.now(), LocalDate.parse(loanDTO.getDueDate()));
			
			if (loan!=null) {
				saveAll();
				loanDTO.setId(library.getCurrentManager().getCreatedLoans().getLast().getId());
			}
		}
		return loanDTO;
	}
	
	public AuthorDTO createAuthorDTO() {
		return new AuthorDTO();
	}

	public List<AuthorDTO> getAllAuthors() {
		List<AuthorDTO> authorList = new ArrayList<>();
		for (Person author: library.getAllAuthors()) {
			authorList.add(AuthorDTO.createDTOfromEntity(author));
		}
		return authorList;
	}

	public List<BookDTO> getAllBooks() {
		List<BookDTO> bookList = new ArrayList<>();
		for (Book book: library.getAllBooks()) {
			bookList.add(BookDTO.createDTOfromEntity(book));
		}
		return bookList;
	}

	public List<UserDTO> getAllUsers() {
		List<UserDTO> userList = new ArrayList<>();
		for (User user: library.getAllUsers()) {
			userList.add(UserDTO.createDTOfromEntity(user));
		}
		return userList;
	}
	

	public List<LoanDTO> getAllLoans() {
		List<LoanDTO> loanList = new ArrayList<>();
		for (Loan loan: library.getAllLoans()) {
			loanList.add(LoanDTO.createDTOfromEntity(loan));
		}
		return loanList;
	}
	
	public void removeAuthor(Long id) {
		library.removeAuthor(id);
		saveAll();
	}
	
	public void removeBook(Long id) {
		library.removeBook(id);
		saveAll();
	}

	public void removeUser(Long id) {
		library.removeUser(id); 
		saveAll();
	}

	public void returnBook(long id) {
		library.getCurrentManager().returnBook(library.getLoan(id));
		saveAll();
	}

	public void saveAll() {
		Manager currentManager = library.getCurrentManager();
		library = libraryRepository.save(library);
		library.setCurrentManager(currentManager);
	}
	
}
