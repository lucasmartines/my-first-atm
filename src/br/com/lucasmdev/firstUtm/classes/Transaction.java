package br.com.lucasmdev.firstUtm.classes;

public abstract class Transaction {

	private int acountNumber ;
	private Screen screen;
	private BankDatabase bankDatabase;
	
	public Transaction( 
	  int userAccountNumber,
	  Screen atmScreen,
	  BankDatabase atmDatabase
	) {
		this.acountNumber = userAccountNumber;
		this.screen = atmScreen;
		this.bankDatabase = atmDatabase;
	}
	public int getAcountNumber() {
		return acountNumber;
	}
	
	public Screen getScreen() {
		return screen;
	}
	public BankDatabase getBankDatabase() {
		return bankDatabase;
	}
	
	public abstract void execute();
	
}
