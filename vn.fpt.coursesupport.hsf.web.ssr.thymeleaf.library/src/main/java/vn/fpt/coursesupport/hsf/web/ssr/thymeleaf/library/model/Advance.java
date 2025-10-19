package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "advances")
public class Advance extends Membership {

	public static final int code = 1;
	
	public Advance() {}
	
	public Advance(LocalDate creationDate, LocalDate expiredDate) {
		super(creationDate, expiredDate);
	}
	
    @Override
    public int getNbLoans() {
        return 20;
    }

	@Override
	public long getPrice() {
		return 100;
	}

	@Override
	public Membership upgrade() {
		return new Premium();
	}

	@Override
	public final int getCode() {
		return code;
	}
	
}
