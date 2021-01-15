package Vending;

public enum Status 
{
	VENDING_MODE(0), SERVICE_MODE(1);

	private int statusValue;
	
	private String VALUES[]= {"VENDING_MODE", "SERVICE_MODE"};
	
	private Status (int value) 
	{
		statusValue =value;
	}

public String getStatus()
{
	
	
	return VALUES[statusValue];
}
}