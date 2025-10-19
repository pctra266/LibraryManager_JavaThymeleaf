package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "managers")
public class Manager extends Person {
	
	@OneToMany(
		fetch = FetchType.EAGER,
		cascade = CascadeType.ALL
	)
	private List<Loan> createdLoans;

	@OneToMany(
		fetch = FetchType.EAGER,
		cascade = CascadeType.ALL
	)
	private List<Loan> returnedLoans;
	
	public Manager() {
		super();
		createdLoans = new ArrayList<>();
		returnedLoans = new ArrayList<>();
	}

	public Manager(String name) {
		super(name);
		createdLoans = new ArrayList<>();
		returnedLoans = new ArrayList<>();
	}

	public void addReturnedLoan(Loan loan) {
		if (!returnedLoans.contains(loan)) returnedLoans.add(loan);
	}
	
	public List<Loan> getReturnedLoans() {
		return returnedLoans;
	}
	
	public List<Loan> getCreatedLoans() {
		return createdLoans;
	}

	
	public Loan createLoan(Book book, LocalDate creationDate, LocalDate dueDate) {
		Loan loan = new Loan();
		loan.setBook(book);
		loan.setCreationDate(creationDate);
		loan.setDueDate(dueDate);
		createdLoans.add(loan);
		return loan;
	}
	
	public Loan lend(User user, Book book, LocalDate creationDate, LocalDate dueDate) {
		if (user.isBanned() || user.reachMaxLoan() ||
				!user.getMembership().isValid() || !book.isAvailable())
			return null;

		Loan loan = createLoan(book, creationDate, dueDate);
		book.decrease(1);
		user.addCurrentLoan(loan);
		return loan;
	}
	
	public void returnBook(Loan loan) {
		User user = loan.getUser();
		Book returnedBook = loan.getBook();
		LocalDate returnDate = LocalDate.now();
		LocalDate dueDate = loan.getDueDate();
		loan.setReturnedDate(LocalDate.now());
		addReturnedLoan(loan);
			
		if (returnDate.isAfter(dueDate)) {
			if (!user.spend(getPunishmentFee()))	{
				user.setBanned(true);
			}
		} else user.returnLoan(loan);
		
		returnedBook.increase(1);
	}

	private long getPunishmentFee() {
		return 20;
	}
	
}
