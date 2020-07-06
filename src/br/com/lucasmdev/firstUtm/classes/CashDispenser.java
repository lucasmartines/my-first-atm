package br.com.lucasmdev.firstUtm.classes;

public class CashDispenser {

	private final static int INITIAL_COUNT = 500;
	private Integer count;
	
	public CashDispenser() {
		count = INITIAL_COUNT;
	}
	
	/* simular a entrega das cedulas*/
	public void dispenseCash( int amount ) {
		
		int billsRequired = amount / 20;
		count -= billsRequired;
	}
	
	/*true if atm machine have enough money*/
	public Boolean isSufficientCashAvailable( int amount ) {
		
		int billsRequired = amount / 20;
		
		if( count >= billsRequired ) 
			return true;
		else
			return false;
	}
}
