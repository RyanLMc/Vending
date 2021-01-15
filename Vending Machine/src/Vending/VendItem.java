package Vending;

public class VendItem 
{
	private int itemId;
	private int nextId=1;
	private String name;
	private double unitPrice;
	private int qtyAvailable;
	
	public VendItem(String name, double unitPrice)// constructor for the Vend Item Object
	{
		this.name = name;
		this.unitPrice = unitPrice;
		this.itemId= nextItemId();
		this.qtyAvailable =10;
	}
	
	private int nextItemId() // Generates a unique Item Id for each Vend item object
	{
		itemId =nextId ;
		nextId++;
		return itemId;
		
	}
	
	public VendItem(String name,double unitPrice, int qtyAvailable)// constructor for the Vend Item Object with additional parameters
	{
		this.name = name;
		this.unitPrice = unitPrice;
		this.itemId= nextItemId();
		this.qtyAvailable =qtyAvailable;
		
	}
	
	public String getName()// returns the name of the vend item
	{
		return this.name;
		
	}
	
	public double getPrice()// returns the price of the vend item
	{
		return this.unitPrice;
		
	}
	
	public int getId()// returns the price of the vend item
	{
		return this.itemId;
		
	}
	
	public int getQty()//returns the quantity of the vend item
	{
		return qtyAvailable;
		
	}
	
	public void setQty(int qty)// allows the quantity of the vend item to be changed manually
	{
		this.qtyAvailable =qty;
		System.out.println("Quantity available is now: "+this.qtyAvailable);
		
	}
	
	public boolean restock(int id)// sets the value of the value for quantity back to maximum
	{
		qtyAvailable =10;
		System.out.println("Item: "+id+" has been restocked");
		return false;
	
	}
	
	public static void deliver(String iname, double change)// Displays the message for purchasing a Vend Item
	{
		System.out.println("Thank you for Purchasing: "+iname+"\nYour change is: £"+String.format("%.2f",change/100));
	}
	
	
}
