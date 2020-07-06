package br.com.lucasmdev.firstUtm.classes;

public class Screen {

	public void displaymessage( String message ) {
		System.out.printf(message);
	}

	public void displaymessageLine(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
	public void displayDollarAmount( double amount ) {
		System.out.printf( "$%,.2f" , amount );
	}
}
