package Vending;
import java.util.Scanner;//initialize Scanner

public class Main 
{
	static String title="QUB Vending";// Title for the vending machine object
	static final String[] options = {"View Options", "Enter Coins", "View Entered Cash", "Purchase item", "Quit","Engineer Mode"};//options for the menu object
	static final String[] engineerOptions={"View System Information","Restock","Reset","Exit Service Mode"}; //adds options for the Engineer Menu
	static final String[] coinOptions= {"5p", "10p", "20p", "50p","£1","£2"};
	static Menu myMenu= new Menu(title, options );// creation of the menu object passing the values for title and options
	static Menu eMenu= new Menu(title, engineerOptions );
	static VendingMachine vendingMachine = new VendingMachine("QUB", 10);// Creation of the vending machine object passing values for owner and max items
	static VendItem ripple= new VendItem("Ripple", 1.00, 9);// Creating Vend Items to be added to the vending machine 
	static VendItem twix= new VendItem("Twix", 0.80, 11);
	static VendItem bounty= new VendItem("Bounty", 0.70, 8);
	static VendItem skittles= new VendItem("Skittles", 1.50);
	static Scanner input = new Scanner(System.in);// Scanner to read in user input 
	
	public static void main(String[]args)
	{
		
		vendingMachine.addNewItem(ripple);//adding the Vend Item Objects to the vending machine
		vendingMachine.addNewItem(twix);
		vendingMachine.addNewItem(bounty);
		vendingMachine.addNewItem(skittles);
		
		int choice = 0;// integer to hold user selection 
		boolean quit = false;// value to run the loop
	
		do 
		{
			choice=myMenu.getChoice();//calls the get choice method to allow for user selection and menu display 
			if(choice ==5)
			{
				quit= true; //Allows the user to quit the program
			}
			else
			{
				processChoice(choice);//Calls the process method to deal with the user request
			}
		}
		while(quit !=true); 
		
		
		System.out.println("Thank you for your patronage Goodbye");// Exit message
	
	}

	private static void processChoice(int choice) // method to deal with user interaction 
	{
		switch(choice)
		{
		case 1 : String [] items= vendingMachine.listItems(); for(int i=0; i< items.length; i++) {System.out.println(items[i]);}; break;
		case 2 : System.out.println("Please eneter the value of the coin\n"); for(int i=0; i< coinOptions.length; i++) {System.out.println("selection " +(i+1)+": "+coinOptions[i]);} int coin =input.nextInt();   vendingMachine.insertCoin(coin); ; break;
		case 3 : System.out.println("You have entered: £"+ String.format("%.2f",vendingMachine.getUserMoney()/100)+"\n"); ; break;
		case 4 : System.out.println("Please enter the number of the item you would like to purchase"); int selection=input.nextInt(); vendingMachine.purchaseItem(selection)  ; break;
		case 6 : engineerMode(); break;
		}
	}
	
	public static void engineerMode()
	{
		 final Menu eMenu= new Menu("Engineer Menu",Main.engineerOptions);
		 int choice;
		 boolean quit = false;
		 int validPinCode=2112;
		 vendingMachine.setStatus(2);
		 System.out.println("Please enter pin code");
		 int pinCode = input.nextInt();
		 
		 if (pinCode==validPinCode)
		 {do 
			{
			choice=eMenu.getChoice();
				if(choice ==4)
				{
					quit= true;
				}
				else
				{
					 processEChoice(choice);
				}
			}while(quit !=true); 
		 }
		 else
		 {
			 System.out.println("Password Inccorrect");
		 }
	}
	

	private static void processEChoice(int choice)
	{
		switch(choice)
		{
		case 1 : String info= vendingMachine.getSystemInfo() ;System.out.println("\n"+info); break;
		case 2 : String [] items= vendingMachine.listItems(); for(int i=0; i< items.length; i++) {System.out.println(items[i]);} System.out.println("which item would you like to restock"); choice=input.nextInt(); vendingMachine.restock(choice); ; break;
		case 3 : vendingMachine.reset(); ; break;

		}
	}
	
	
}
