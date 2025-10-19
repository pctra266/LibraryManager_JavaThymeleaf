package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordinaries")
public class Ordinary extends Membership {

	public static final int code = 0;
	
	public Ordinary() {}
	
	public Ordinary(LocalDate creationDate, LocalDate expiredDate) {
		super(creationDate, expiredDate);
	}

	@Override
	public int getNbLoans() {
		return 5;
	}

	@Override
	public long getPrice() {
		return 0;
	}

	@Override
	public Membership upgrade() {
		return new Advance();
	}

	@Override
	public final int getCode() {
		return code;
	}

}
