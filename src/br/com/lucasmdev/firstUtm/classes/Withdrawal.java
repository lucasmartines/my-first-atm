package br.com.lucasmdev.firstUtm.classes;


/*withdrawal representa o saque */
public class Withdrawal extends Transaction {
	
	private int amount;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	
	
	private static final int CANCELED = 6;
	public Withdrawal
	( int currentAccountNumber,
	  Screen screen, 
	  BankDatabase bankDatabase, 
	  Keypad keypad, 
	  CashDispenser cashDispenser
	) {
		
		super(  currentAccountNumber ,  screen , bankDatabase );
		this.keypad = keypad;
		this.cashDispenser = cashDispenser;		
	}
	
	@Override
	public void execute() {
		
		boolean cashDispensed = false;
		double availableBalance;
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		do {
			
			amount = displayMenuOfAmounts();
			availableBalance = bankDatabase.getAvailableBalance( getAcountNumber() );
			if( amount != CANCELED) {
				
				availableBalance = bankDatabase.getAvailableBalance( getAcountNumber() );
				if( amount <= availableBalance) {
					if( cashDispenser.isSufficientCashAvailable( amount ) ) {
						
						bankDatabase.debit( getAcountNumber(), amount );
						cashDispenser.dispenseCash( amount );
						cashDispensed = true;
						
						screen.displaymessageLine("\n"
							+ "Your cash has been dispensed. Please take your cash now.");
						
					}
				}
				else {
					screen.displaymessageLine( "\nInsuficient funds in your account.");
					screen.displaymessageLine( "\nPlease choose a smaller amount.");
				}
			}// if amount != canceled
			else {
				screen.displaymessageLine("\n Cancelling transaction... ");
				return;
			}
		}
		while( !cashDispensed );
		
	}
	private int displayMenuOfAmounts() {
		int userChoise = 0;
		
		Screen screen = getScreen();
		
		int [] amounts = { 0 , 20 , 40 , 60 , 100 , 200 };
		
		while( userChoise == 0) {
			
			screen.displaymessageLine("\nWithdrawal Menu: ");
			screen.displaymessageLine("1 - $20");
			screen.displaymessageLine("2 - $40");
			screen.displaymessageLine("3 - $60");
			screen.displaymessageLine("4 - $100");
			screen.displaymessageLine("5 - $200");
			screen.displaymessageLine("6 - Cancel transaction");
			screen.displaymessage("\nChoose a withdrawal amount:");
			
			int input = keypad.getInput();
			
			switch(input) {
				case 1: case 2: case 3: case 4: case 5:
					userChoise = amounts[input];
					break;
				case CANCELED: // CASE CANCELED
					userChoise = CANCELED;
					break;
				default: 
					screen.displaymessageLine("\nInvalid selecrion try again");
			}
		}// fim do while
		
		return userChoise;
	}
	
}
