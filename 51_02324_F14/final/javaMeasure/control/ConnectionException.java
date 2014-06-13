package javaMeasure.control;

public class ConnectionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int e;
	private boolean C=false;
	
	public ConnectionException(int errorNumber) {
		this.e=errorNumber;
}
	public ConnectionException(int x, boolean b) {
		C=b;
		this.e= x;
	
	}
	public String getMessage(){
		String r="";
		if(C){
			switch (e){
				case 1:
					r="DAQ not connected or malfunctioning";
					break;
				case 2:
					r="Illegal port";
					break;
				case 3:
					r="Illegal number of measurements";
					break;
				case 4:
					r="Illegal period";
					break;
				case 5:
					r="Message does not conform to protocol";
			}
		}
		else{
		switch(e){
			case 1:
				r="No connection between java and C#";
				break;
			case 2:
				r="Return from C# does not follow protocol";
				break;
			case 3:
				
				break;
			case 4: 
				r="Connection timed out";
				break;
			case 5:
				r="Can not connect to C# part";
				break;
			case 6: 
				r="Connection is already closed.";
				break;
			case 7:
				r="Could not properly set Socket timeout";
				break;
			case 8:
				r="Could not create either socket writer or reader.";
				break;
			case 9:
				r="Could not read what was sent from C#";
				break;
		}
		}
		
		return r;
	}
}