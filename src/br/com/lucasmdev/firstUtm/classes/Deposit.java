package br.com.lucasmdev.firstUtm.classes;

public class Deposit extends Transaction {

	/*dependencies*/
	private Double amount;
	private Keypad keypad;
	private DepositSlot depositSlot;
	private final static int CANCELED = 0;
	
	
	
	public Deposit(int currentAccountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad,
			DepositSlot depositSlot) 
	{
		super(  currentAccountNumber ,  screen , bankDatabase );
		this.keypad      = keypad;
		this.depositSlot = depositSlot;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		amount = promptForDepositAmount();
		
		if( amount != CANCELED) {
			
			screen.displaymessage("\nPlease insert a deposit envelope containint ");
			screen.displayDollarAmount(amount);
			screen.displaymessageLine(".");
			
			boolean envelopeReceived = depositSlot.isEnvelopeReceived();
			
			if( envelopeReceived ) {
				
				screen.displaymessageLine("\nYour envelope has been "
						+ "received.\nNOTE: The money just deposited will not be"
						+ "available until we verify the amout of any"
						+ " enclosed cash and your chacks clear");
				
				bankDatabase.credit( getAcountNumber(), amount);
			}
			
		}// fim do if ( amount != canceled )o usuario cancelou
		else {
			screen.displaymessageLine("\nYou did not insert an"
					+ " envelope, so the ATM has canceled your transaction.");
			
		}
	}

	private Double promptForDepositAmount() {
		// TODO Auto-generated method stub
		Screen screen = getScreen();
		
		screen.displaymessage("\nPlease enter a deposit amount in"
				+ "CENTS (or 0 to cancel): ");
		int input= keypad.getInput();
		
		if( input == CANCELED) { 
			return (double) CANCELED ; 
		}
		else {
			return (double) input / 100;
		}
		
			
		 
	}

	 
}
