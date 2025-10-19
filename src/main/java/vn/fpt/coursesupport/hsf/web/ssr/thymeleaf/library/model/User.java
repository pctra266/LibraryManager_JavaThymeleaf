package vn.fpt.coursesupport.hsf.web.ssr.thymeleaf.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Person {
	
	private boolean isBanned = false;
	private long balance;
	
	@OneToOne(
		fetch = FetchType.EAGER,
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
    private Membership membership;
	
	@OneToMany(
		fetch = FetchType.EAGER,
		orphanRemoval = true
	)
    protected List<Loan> currentLoans;
    
	@OneToMany(
		fetch = FetchType.EAGER,
		mappedBy = "user",
		orphanRemoval = true
	)
	protected List<Loan> allLoans;

    public User() {
    	super();
    	balance = 0;
    	allLoans = new ArrayList<>();
    	currentLoans = new ArrayList<>();
    }
    
    public User(String name) {
    	super(name);
    	balance = 0;
    	allLoans = new ArrayList<>();
    	currentLoans = new ArrayList<>();
    }
    
    public Membership createMembership(int code, LocalDate creationDate, LocalDate endDate) {
    	if (code==Ordinary.code) membership = new Ordinary(creationDate, endDate);
    	else if (code==Advance.code) membership = new Advance(creationDate, endDate);
    	else membership = new Premium(creationDate, endDate);
    	return membership;
    }

    public void deposit(long amount) {
    	balance += amount;
    }
    
    public boolean spend(long amount) {
    	if (balance>=amount) {
    		balance -= amount;
    		return true;
    	}
    	return false;
    }
    
    public boolean upgrade() {
    	Membership upgraded = membership.upgrade();
    	if (upgraded != null) {
    		if (spend(upgraded.getPrice())) {
    			membership = upgraded;
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public List<Loan> getAllLoans() {
        return allLoans;
    }
    
    public List<Loan> getCurrentLoans() {
    	return currentLoans;
    }
    
    public void addCurrentLoan(Loan loan) {
    	if (!currentLoans.contains(loan)) currentLoans.add(loan);
    	addLoan(loan);
    }
    
    public void returnLoan(Loan loan) {
    	currentLoans.remove(loan);
    }

    public void addLoan(Loan newLoan) {
        if (!allLoans.contains(newLoan)) allLoans.add(newLoan);
        newLoan.user = this;
    }

	public boolean reachMaxLoan() {
		return currentLoans.size() == membership.getNbLoans();
	}
    
}
