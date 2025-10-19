package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.Loan;

public class LoanDTO {

	private Long id;
	private Long userId;
	private Long bookId;
	private String userName;
	private String bookName;
	private String creationDate;
	private String dueDate;
	private String returnDate;
	
	public LoanDTO() {}
	
	public LoanDTO(Long id, Long userId, Long bookId, 
					String userName, String bookName,
					String creationDate, String dueDate, String returnDate) {
		setId(id);
		setUserId(userId);
		setBookId(bookId);
		setUserName(userName);
		setBookName(bookName);
		setCreationDate(creationDate);
		setDueDate(dueDate);
		setReturnDate(returnDate);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getBookId() {
		return bookId;
	}
	
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public static LoanDTO createDTOfromEntity(Loan loan) {
		return new LoanDTO(loan.getId(), loan.getUser().getId(),
				loan.getBook().getId(), loan.getUser().getName(),
				loan.getBook().getName(), loan.getCreationDate().toString(), 
				loan.getDueDate().toString(), loan.getReturnedDate() == null ? "" : loan.getReturnedDate().toString());
	}
	
	
}
