package br.com.lucasmdev.firstUtm.classes;

public class Account {

	private Integer accountNumber;
	private Integer pin;
	private Double  availableBalance;
	private Double  totalBalance;
	
	public Account ( 
	  int accountNumber ,
	  int pin ,
	  double availableBalance,
	  double totalBalance 
    ){
		
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.availableBalance = availableBalance;
		this.totalBalance = totalBalance;
		
	}
	
	public boolean validatePIN( int userPIN ) {
		
		if( userPIN == pin )
			return true;
		else 
			return false;
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}
	
	public Double getTotalBalance() {
		return totalBalance;
	}
	
	public void credit( double amount ) {
		availableBalance += amount;
	}
	public void debit( double amount ) {
		availableBalance -= amount;
	}

	public int getAccountNumber() {
		
		return accountNumber ;
	}
	
}
