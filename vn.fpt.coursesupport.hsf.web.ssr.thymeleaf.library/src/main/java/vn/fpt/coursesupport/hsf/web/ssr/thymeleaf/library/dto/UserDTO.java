package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.dto;

import vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model.User;

public class UserDTO {
	private Long id;
	private String name;
	private String dob;
	private String nationality;
	private int membership;
	private long balance;
	private boolean isBanned;

	public UserDTO() {}

	public static UserDTO createDTOfromEntity(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setDob(user.getDob().toString());
		dto.setNationality(user.getNationality());
		dto.setBalance(user.getBalance());
		dto.setBanned(user.isBanned());
		dto.setMembership(user.getMembership().getCode());
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

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public int getMembership() {
		return membership;
	}

	public void setMembership(int membership) {
		this.membership = membership;
	}

	
}