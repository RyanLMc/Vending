package Vending;

public class VendingMachine 
{
	private String owner;
	private int maxItems;
	private int itemCount;
	private VendItem[] stock;
	private double totalMoney;
	private double userMoney=0;
	private Status vmStatus;
	
	public VendingMachine(String owner, int maxItems)//constructor for vending machine object
	{
		this.owner =owner;
		this.maxItems=maxItems;
		this.vmStatus= Status.VENDING_MODE;
		this.stock= new VendItem[maxItems];
		
	
	
	}
	
	public String getSystemInfo()//returns the information about the vending machine object
	{
		String info= "Owner: "+ owner +"\nNumber of Items: "+itemCount+"\nTotal money in machine: £"+String.format("%.2f",totalMoney)+"\nStatus:" +vmStatus;
		return info;
		
	}
	public double getUserMoney()// returns the value of the money entered by the user
	{
		return this.userMoney;
	}
	
	public void reset()// sets the vending machine back to a clean instance of the object
	{
		totalMoney=0;
		this.stock= new VendItem[0];
	}
	
	public String purchaseItem(int selection)// allows the user to purchase and item
	{
		String message= "Unable to vend Machine in service mode";
		selection= selection-1;
		if (vmStatus== Status.VENDING_MODE)//checks that the vending machine is in vending mode
		{
			if(stock[selection]!=null)//checks that the value selected is a valid choice
			{
				if(stock[selection].getQty() >0)// checks that the item is in stock
				{
					if(this.userMoney >= stock[selection].getPrice()*100)//checks that the user has input enough money to purchase the item 
					{
						 double change = userMoney-stock[selection].getPrice()*100; // calculates change to be return to the user
						 totalMoney= totalMoney -change;
						 userMoney=userMoney-(change +stock[selection].getPrice()*100);
						 int newQty= stock[selection].getQty() -1;
						 stock[selection].setQty(newQty);// decrements the stock available
						 VendItem.deliver(stock[selection].getName(), change);
					}
					else
					{
						System.out.println("There has not been enough money inserted for this product");
						vmStatus= Status.SERVICE_MODE;
					}
				}
				else 
				{
					System.out.println("There has not been enough money inserted for this product");
					vmStatus= Status.SERVICE_MODE;
					
				}
			}
			
		}
		return message;
		
	}
	
	public void insertCoin(int coin) //deals with the money that has been inserted by the user
	{
		switch(coin)
		{
		case 1 : this.userMoney+=5; break;
		case 2 : this.userMoney+=10; break;
		case 3 : this.userMoney+=20; break;
		case 4 : this.userMoney+=50;break;
		case 5 : this.userMoney+= 100; break;
		case 6:  this.userMoney+= 200; break;
		default: System.out.println("INVALID SELECTION PLEASE TRY AGAIN\n");break;
		}
		
	}
	
	public String[] listItems()//method displays all of the items available to vend including price and how many are available 
	{
		if(this.itemCount!=0)
		{
			String sStock[]= new String[this.itemCount];
			for (int i=0; i< this.itemCount; i++)
			{
				sStock[i]= "selection: " +(i+1)+" Name: "+stock[i].getName() + ", Price: £"+String.format("%.2f", stock[i].getPrice())+ ", Number Available: "+stock[i].getQty()+"\n";
			}
			return sStock;
		}
		else 
		{
			String[] error = new String[1];
			error[0]= "there are currently not items in this mahchine";
			return error;
		}
	}
	
	public void setStatus(int choice)// allows the status of the vending machine to be set manually
	{
		if(choice==1)
		{
			vmStatus= Status.VENDING_MODE;
		}
		else if(choice==2)
		{
			vmStatus= Status.SERVICE_MODE;
		}
		else
		System.out.println("Invalid selection");
	}
	
	public void restock(int choice)
	{
		stock[choice].restock(stock[choice].getId());
	}
	
	public boolean addNewItem(VendItem item)// allows new items to be added to the vending machine 
	{
		if(item != null && this.itemCount< maxItems)
		{
			for(int i=0; i<= maxItems; i++)
			{
				if(stock[i]==null)
				{
					stock[i]= item;
					this.itemCount++;
					break;
				} 
				
			}
			return true;
		}
		else 
		{
			System.out.println("Failed to add new item ");
			return false;
		}
		
		
		
	}

}
