package br.com.lucasmdev.firstUtm.classes;


public class ATM {

	/* state */
	private boolean userAuthenticated = false ;
	private int currentAccountNumber = 0;
	
	/* Aggregations */
	private DepositSlot depositSlot;
	private Keypad keypad ;
	private CashDispenser cashDispenser ;
	private Screen screen ;
	private BankDatabase bankDatabase;
	
	
	public ATM() {
		
		
		depositSlot   = new DepositSlot();
		keypad        = new Keypad();
		cashDispenser = new CashDispenser();
		screen        = new Screen();
		bankDatabase  = new BankDatabase();
	}
	
	/**
	 * START ATM APP
	 */
	public void run() {
		
		// app loop 
		while(true) 
		{
			/* VALIDATE USER AUTHENTICATION 
			 *  while not authenticated show authentication screen
			 *  get pin and authentication number 
			 *  */
 
			while( !userAuthenticated ) {
				screen.displaymessage("\nWelcome!");
				authenticateUserMenu( );
			}
			
			
			performTransactionsMenu();
			
			userAuthenticated    = false;
			currentAccountNumber = 0;
			screen.displaymessageLine("\nThank you! Goodbye!");	
		}// end of app loop
	}// end of .run()
	
	private void performTransactionsMenu() {
		
		Transaction currentTransaction = null;
		
		boolean userExited = false;
		
		while( !userExited ) {
			
			/* show display menu and get main menu selection int */
			displayMainMenu();
			int mainMenuSelection = keypad.getInput();
			
			switch( mainMenuSelection ) {
				
				case MenuOptions.BALANCE_INQUIRY:
				case MenuOptions.DEPOSIT:
				case MenuOptions.WITHDRAWAL:
					
					currentTransaction = createTransaction( mainMenuSelection );
					currentTransaction.execute();
					
					break;
				case MenuOptions.EXIT:
					screen.displaymessageLine("\nExitin the system...");
					userExited = true; // exit from loop					
					
					break;
				default:
					screen.displaymessageLine("\nYou did not enter a valid selection. Try Again.");
					
					break;
			}
		}
	}

	private Transaction createTransaction(int mainMenuSelection) {
		
		Transaction temp = null;
		
		switch( mainMenuSelection  ) {
			
			case MenuOptions.BALANCE_INQUIRY:
				temp = new BalanceInquiry(
					currentAccountNumber,
					screen,
					bankDatabase
				);
				break;
			case MenuOptions.WITHDRAWAL:
				temp = new Withdrawal(
						currentAccountNumber,
						screen,
						bankDatabase,
						keypad,
						cashDispenser
					);
				break;
			case MenuOptions.DEPOSIT:
				temp = new Deposit(
					currentAccountNumber,
					screen,
					bankDatabase,
					keypad,
					depositSlot
				);
				break;
		}
		
		return temp;
	}

	private void displayMainMenu() {
		
		screen.displaymessageLine("\nMain Menu: ");
		screen.displaymessageLine("1 - View my balance ");
		screen.displaymessageLine("2 - Withdraw cash ");
		screen.displaymessageLine("3 - Deposit funds ");
		screen.displaymessageLine("4 - Exit\n ");
		
		screen.displaymessage( "Enter a choice: ");
	}

	/*
	 * get accountNumber and pin 
	 * and authenticate user if user is correct 
	 * change currentAccountNumber 
	 * or if is not then show invalid accont number
	 * */
	private void authenticateUserMenu() {
		
		screen.displaymessage( "\nPlease enter your acount number: ");
		int accountNumber = keypad.getInput();
		
		screen.displaymessage( "\nEnter your PIN:  ");
		int pin = keypad.getInput();
		
		userAuthenticated =  
		    bankDatabase.authenticateUser( accountNumber , pin ) ;
		
		if( userAuthenticated )
		{
			currentAccountNumber = accountNumber;
		}else {
			screen.displaymessageLine( "Invalid account number or PIN, Please try again!");
		}
			
	}
	@SuppressWarnings("unused")
	@Override
	public String toString() {
		return "[ATM "
				+ " isUserAuth: "  + userAuthenticated
				+ " countNumber: " + currentAccountNumber
				+ "  ]";
	}
}


class MenuOptions {

	public static final int BALANCE_INQUIRY = 1;
	public static final int WITHDRAWAL = 2;
	public static final int DEPOSIT = 3;
	public static final int EXIT = 4;
}

