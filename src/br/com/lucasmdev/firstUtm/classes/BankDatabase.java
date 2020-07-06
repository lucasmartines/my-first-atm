package br.com.lucasmdev.firstUtm.classes;

public class BankDatabase {

	private Account [] accounts;
	public BankDatabase() {
		
		accounts = new Account[ 2 ];
		accounts[ 0 ] = new Account( 12345 ,1997 , 5000.0  ,1200.0);
		accounts[ 1 ] = new Account( 98765 ,12345, 10000.0 ,5000.0);
	}
	public boolean authenticateUser( int userAccountNumber , int  userPIN ) {
		
		Account userAccount = getAccount( userAccountNumber );
		if( userAccount != null) {
			return userAccount.validatePIN( userPIN );
		}
		else 
			return false;
	}
	private Account getAccount( int accountNumber ) {
		
		for( Account currentAccount: accounts ) {
			if( currentAccount.getAccountNumber() == accountNumber) {
				return currentAccount;
			}	
		}
		
		return null;
	}
	public double getAvailableBalance(int userAccountNumber) {
		
		return getAccount( userAccountNumber )
			.getAvailableBalance();
	}
	public double getTotalBalance( int userAccountNumber ) {
		
		return getAccount( userAccountNumber )
				.getTotalBalance();
	}
	
	
	public void credit( int userAccountNumber,double amount) {
		getAccount( userAccountNumber )
		  .credit( amount );
	}
	
	public void debit( int userAccountNumber,double amount) {
		getAccount( userAccountNumber)
		  .debit( amount );
	}
	
}
