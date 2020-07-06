package br.com.lucasmdev.firstUtm.classes;

public class BalanceInquiry extends Transaction {
	
	public BalanceInquiry(int currentAccountNumber, Screen screen, BankDatabase bankDatabase) {
		
		super(  currentAccountNumber ,  screen , bankDatabase );
		
	}

	@Override
	public void execute() {

		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		screen.displaymessageLine("\nBalance information: ");
		screen.displaymessage(" - Available balance: ");
		
		screen.displayDollarAmount(
			bankDatabase.getAvailableBalance( getAcountNumber() )
		);
		
		screen.displaymessage( "\n - Total balance: ");
		
		screen.displayDollarAmount(
			bankDatabase.getAvailableBalance( getAcountNumber() )
		);
		
		screen.displaymessageLine("");
	}

	
}
