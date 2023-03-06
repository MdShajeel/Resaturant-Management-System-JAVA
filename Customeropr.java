package service;  //--->Package
import invoice.*;
import java.io.*;
import java.util.*;

interface Restaurant  //--->Interface
{
	boolean Owner();
	boolean Customer();
}
abstract class Authentication implements Restaurant  //--->Abstract class
{
	Scanner sc = new Scanner(System.in);
	Console con =System.console();
	static String foodList[][] = new String[100][2];  //---> Static keyword
	static String dupfoodList[][] = new String[100][2];
	static String orderList[][] = new String[100][3];
	static String duporderList[][] = new String[100][3];
	static boolean r=true;
	int noOfItems;
	final public boolean Owner()  //--->Final keyword
	{
		boolean login = true,x=true;
		String c;
		System.out.println("------AUTHENTICATION-------");
		while(login)
		{
			System.out.println("Enter username");
			String uname = con.readLine();
			System.out.println("Enter password");
			String password = con.readLine();
			if(uname.equals("rest") && password.equals("rest123"))
			{
				login=false;
				this.O_operations();
			}
			else
			{
				System.out.println("\nEnter valid Username or Password ");
				System.out.println("Do you want to Re-enter [y/n] : ");
				c=con.readLine();
				if(c.equalsIgnoreCase("n"))
				login=false;
			}
		}
		return x;
	}
	void O_operations()
	{
		boolean x=true;
		int j=0,flag=0,emp=0;
		while(x)
		{
			System.out.println("\n-----OPERATIONS-----");
			System.out.println("1) Add Food Item\n" + 
							"2) Remove Food Item \n" + 
							"3) Update Food Item \n" + 
							"4) Display Menu\n" + 
							"5) Exit");
			System.out.println("\nPlease select your choice :");
			try
			{
				int operation = sc.nextInt();		
				switch(operation)
				{ 
					case 1 :	do
								{
									try  
									{
										r=true;
										System.out.print("Enter number of food items to add in menu.");
										noOfItems = sc.nextInt();
										for(int i=0;i<noOfItems;i++)
										{	
											int z=1;
											System.out.println("\nEnter Food Item-"+(i+1)+ " : ");
											String strf="";
											strf+=con.readLine();
											foodList[j][0] = strf;
											z = check(j);
											if(z==1)
											{
												do
												{
													System.out.println("Enter the Price of food item :");
													foodList[j][1]=sc.next();
													if(foodList[j][1].matches("[0-9]+"))
													{
														System.out.println(foodList[j][0]+ " added in menu");
														j++;
														r=false;
													}
													else
													System.out.println("\n!!!Enter Positive number!!!");
												}while(r);
											}
										}
										r=this.continuecond();
										break;
									}
									catch(Exception e)
									{
										System.out.println("\n!!!Enter Positive number!!!");       //--->Exception Handling
										sc.nextLine();
									}
								}while(r);
								break;
					case 2 :	System.out.println("Enter the food item to be removed :");
								String  foodItem = con.readLine();
								int k=0;
								flag=0;
								emp=0;
								if(foodList[0][0]==null)
								{
								System.out.println("Menu is  Empty");
								emp=1;
								}

								for(int i=0;(foodList[i][0]!=null)&&emp!=1;i++)
								{
									if(!foodList[i][0].equalsIgnoreCase(foodItem))
									{
										dupfoodList[k][0]=foodList[i][0];
										dupfoodList[k][1]=foodList[i][1];
										k++;
									}
									else
									{
										System.out.println(foodItem+" removed");
										j--;
										flag=1;
									}
								}
								for(int i=0;(i<dupfoodList.length)&&(flag==1);i++) 
								{
									foodList[i][0]=dupfoodList[i][0];
									foodList[i][1]=dupfoodList[i][1];
								}
								foodList[k][0]=null;
								foodList[k][1]=null;
								if(flag==0&&emp!=1)
								System.out.println("Item not found");
								x=this.continuecond();  //--->This keyword
								break;
					case 3 :	System.out.println("Enter food item to update its price ");   
								foodItem = con.readLine();
								flag=0;
								r=true;
								for(int i=0;foodList[i][0]!=null;i++) 
								{
									if(foodList[i][0].equalsIgnoreCase(foodItem))
									{
										do
										{
											System.out.println("Enter its price ");  
											foodList[i][1]=con.readLine();
											if(foodList[i][1].matches("[0-9]+"))
											{
												System.out.println("Menu updated");
												flag=1;
												break;
											}
											else
											System.out.println("\n!!!Enter Positive number!!!");
										}while(r);	
									}
								}
								if(flag==0)
								System.out.println("Failed to update menu");
								x=this.continuecond();
								break;
					case 4 : 	System.out.println("\n===============Menu===============\n");
								System.out.println("--------------------------------");
								System.out.println("S.no\tFood Item\tPrice");
								System.out.println("--------------------------------");
								for(int i=0;foodList[i][0]!=null;i++) 
								{
									String itm=foodList[i][0];
									if(foodList[i][0].length()>15)
									itm=foodList[i][0].substring(0,13);
									else
									for(;itm.length()!=15;)
									itm+=" ";
									System.out.println(i+1+"\t"+itm+"\t"+foodList[i][1]);
								}
								this.displayMenu("display menu");
								x=this.continuecond();
								break;
					case 5 :	x=false;
								break;
					default :	System.out.println("Invalid Choice!!! - choose numbers from 1-5");
				}
			}
			
			catch(InputMismatchException ie)
			{
				System.out.println("\n!!!Enter Positive number!!!"); 
				sc.nextLine();
			}
		}
	}
	int check(int j)
	{
		for(int i=0;foodList[i][0]==null;i++) 
		{
			if(i==j)
			continue;
			if(foodList[i][0].equalsIgnoreCase(foodList[j][0]))
			{
				System.out.println("Item already available ");
				return 0;
			}
		}
		return 1;
	}
	int check(String foodItem)     //---> Overloading
	{
		for(int i=0;orderList[i][0]!=null;i++) 
		{ 
			if(orderList[i][0].equalsIgnoreCase(foodItem))
			{
				System.out.println("Item already available ");
				return 0;
			}
		}
		return 1;
	}
	boolean continuecond()
	{
		System.out.println("\nDo you want to continue [y/n] : ");
		String c=sc.next();
		boolean x=true;
		if(c.equalsIgnoreCase("n"))
		x=false;
		return x;
	}
	void displayMenu(String str)
	{
		try
		{
			FileOutputStream fos =new FileOutputStream("Displaymenu.txt");
			String w="\n===============Menu===============\n"+
			"\n----------------------------------"+
			"\nS.no    Food Item        Price"+
			"\n----------------------------------\n";
			byte b[]=w.getBytes();
			fos.write(b);	
			for(int i=0;foodList[i][0]!=null;i++)
			{ 
				String itm=foodList[i][0];
				if(foodList[i][0].length()>15)
				itm=foodList[i][0].substring(0,13);
				else
				for(;itm.length()!=15;)
				itm+=" ";
				w=""+(i+1)+"        "+itm+"      "
				+foodList[i][1]+"\n";
				byte bb[]=w.getBytes();
				fos.write(bb);
			}	
			fos.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		new invoice.InvoicePrint("Display menu");								
	}
}
public class Customeropr extends Authentication  //--->Public-Modifier
{
	public boolean Customer() //--->Overriding
	{
		
		boolean clogin = true;
		Scanner sc = new Scanner(System.in);
		int flag=0,sno=0;
		boolean x = true; 
		while(clogin)
		{
			System.out.println("------------------------------------");
			System.out.println("1) Display Menu \n" + "2) Order Food Item \n" + 
							"3) Cancel Food Item \n"+ "4) Update Food Item Quantity \n" + 
							"5) Display Order\n" + "6) Pay Bill\n" + "7) Print Invoice\n" + "8) Exit\n");
			String foodItem;
			String quantity;
			int k=0;
			int choice = sc.nextInt();
			switch(choice)
			{
				case 1:	System.out.println("\n===============Menu===============\n");
						System.out.println("--------------------------------");
						System.out.println("S.no\tFood Item\tPrice");
						System.out.println("------------------------------------");
						for(int i=0;i<foodList.length;i++)
						{ 
							if(foodList[i][0]==null)
							break;
							System.out.println(i+1+"\t"+foodList[i][0]+"\t\t"+foodList[i][1]);
						}
						super.displayMenu("displaymenu");
						clogin=super.continuecond();   //--->Super keyword
						break;
				case 2:	int z=0;
						flag=0;
						r=true;
						System.out.println("Enter food item to add ");
 						foodItem=con.readLine();
						do
						{
 							System.out.println("Enter " +foodItem+"'s quantity");						
							quantity= con.readLine();
							if(quantity.matches("[0-9]+"))
							{
								for(int i=0;foodList[i][0]!=null;i++) 
								{
									if(foodList[i][0].equalsIgnoreCase(foodItem))
									{
										z=super.check(foodItem);
										if(z==1)
										{
											orderList[sno][0]=foodItem;
											orderList[sno][1]=quantity;
											orderList[sno][2]=foodList[i][1];
											System.out.println(quantity + " " +foodItem +" added in order");
											sno++;
											break;
										}
										else
										System.out.println(foodItem+" does not exists");
									}
								}
								r=false;
							}
							else
							System.out.println("\n!!!Enter Positive number!!!");
						}while(r);
						
						clogin=super.continuecond();
						break;
				case 3:	System.out.println("Enter food item to remove ");
						foodItem=con.readLine();
						flag=0;
						k=0;
						for(int i=0;orderList[i][0]!=null;i++)
						{
							if(!orderList[i][0].equalsIgnoreCase(foodItem))
							{
								duporderList[k][0]=orderList[i][0];
								duporderList[k][1]=orderList[i][1];
								duporderList[k][2]=orderList[i][2];
								k++;
							}
							else
							{
								System.out.println(foodItem+" cancelled");
								sno--;
								flag=1;
							}
     					}
						if(flag==0)
						System.out.println("Item not found");
  						for(int i=0;(i<duporderList.length)&&(flag==1);i++) 
     					{
							orderList[i][0]=duporderList[i][0];
							orderList[i][1]=duporderList[i][1];
							orderList[i][2]=duporderList[i][2];
     					}
						clogin=super.continuecond();
						break;
				case 4:	System.out.println("Enter food item to update its Quantity ");   
						foodItem = con.readLine();
						flag=0;
						r=true;
						for(int i=0;orderList[i][0]!=null;i++)
						{
							if(orderList[i][0].equalsIgnoreCase(foodItem))
							{
								do
								{
									System.out.println(foodItem+"'s Quantity");
									String uq = con.readLine();
									if(uq.matches("[0-9]+"))
									{
										orderList[i][1]= (String)uq;
										flag=1;
										System.out.println(foodItem+" Qunatity updated to "+uq);
										r=false;
										break;
									}
									else
									System.out.println("\n!!!Enter Positive number!!!");
								}while(r);
								break;
							}
						}
						if(flag==0)
						System.out.println("Failed to update menu");
						clogin=super.continuecond();
						break;
				case 5:	System.out.println("\nS.no\tFoodItem\tQty\tPrice");
						System.out.println("---------------------------------------");
						for(int i=0;orderList[i][0]!=null;i++)
							System.out.println(i+1+"\t"+orderList[i][0]+"\t\t"+orderList[i][1]+"\t"+orderList[i][2]);
							clogin=super.continuecond();
						break;	
				case 6:	int t=0;  
						System.out.println("\n---------------------BILL---------------------\n");
						System.out.println("S.no\tFoodItem\tQty\tRate\tValue");
						System.out.println("----------------------------------------------\n");
						for(int i=0;i<orderList.length;i++)
						{ 
							if(orderList[i][0]==null)
							break;
							int s=(Integer.parseInt(orderList[i][1]))*(Integer.parseInt(orderList[i][2])); 
							t+=s; 
							System.out.println(i+1+"\t"+orderList[i][0]+"\t\t"+orderList[i][1]+"\t"+orderList[i][2]+"\t"+s);
						}
						System.out.println("----------------------------------------------");
						System.out.println("Total Amount = "+t);
						System.out.println("----------------------------------------------");
						clogin=super.continuecond();
						break;	
				case 7: try
						{
							int sum=0;
							FileOutputStream fos =new FileOutputStream("Ordermenu.txt");
							String w="\n----------------------BILL---------------------\n"+
							"\n S.no    FoodItem       Qty    Rate    Value"+
							"\n-----------------------------------------------\n";
							byte b[]=w.getBytes();
							fos.write(b);	
							for(int i=0;i<orderList.length;i++)
							{ 
								if(orderList[i][0]==null)
								break;
								int s=(Integer.parseInt(orderList[i][1]))*(Integer.parseInt(orderList[i][2])); 
								sum+=s; 
								w=""+i+1+"    "+orderList[i][0]+"        "+orderList[i][1]+"        "+orderList[i][2]+"    "+s;
								byte bb[]=w.getBytes();
								fos.write(bb);
							}	
							w=""+"\n-----------------------------------------------"+
							"\nTotal Amount = "+sum+"\n-----------------------------------------------";
							byte bbb[]=w.getBytes();
							fos.write(bbb);
							fos.close();
						}
						catch(Exception e)
						{
							System.out.println(e);
						}
						new invoice.InvoicePrint();	
						clogin=super.continuecond();
						break;
				case 8: clogin=false;	
						break;
				default :	System.out.println("Invalid Choice!!! - choose numbers from 1-7");
			}
		}
		return x;
	}
}