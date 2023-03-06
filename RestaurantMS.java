import service.*;
import invoice.*;
import java.util.*;
import javax.swing.*;

public class RestaurantMS
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		boolean wantToContinue = true;
		while(wantToContinue)
		{
			try
			{
				Thread.sleep(100);
				System.out.println("\n------WELCOME TO RESTAURANT------\n");
				System.out.println("Login as,");
				System.out.println("---------");
				Thread.sleep(100);
				System.out.println("1) Owner");
				Thread.sleep(100);
				System.out.println("2) Customer");
				Thread.sleep(100);
				System.out.println("3) Exit");
				System.out.println("");
				Thread.sleep(100);
				System.out.println("Enter your choice :");
				int choice = sc.nextInt();
				switch(choice)
				{
					case 1:	service.Customeropr obj=new service.Customeropr();  //--->Class & Object
							wantToContinue=obj.Owner();
							break;
					case 2: service.Customeropr obj1=new service.Customeropr();
							wantToContinue=obj1.Customer();
							break;
					case 3: wantToContinue=false;
							break;
					default :	System.out.println("Invalid Choice!!! - choose numbers from 1-3");		
				}
			}
			catch(InputMismatchException ie)
			{
				System.out.println("\n!!!Enter Positive number!!!");
				sc.next();
			}
			catch(InterruptedException i)
			{
				i.printStackTrace();
			}
		}
	}
}