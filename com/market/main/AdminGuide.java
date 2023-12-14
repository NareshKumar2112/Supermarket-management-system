package com.market.main;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import com.market.admin.AdminEditor;
import com.market.admin.AdminEliminator;
import com.market.adminService.AdminHelper;
import com.market.adminService.ProductService;
import com.market.adminService.WorkerService;
import com.market.product.ProductCreator;
import com.market.product.ProductEditor;
import com.market.product.ProductEliminator;
import com.market.store.StoreCreator;
import com.market.store.StoreEditor;
import com.market.store.StoreEliminator;
import com.market.unit.UnitCreator;
import com.market.unit.UnitEditor;
import com.market.unit.UnitEliminator;
import com.market.worker.WorkerCreator;
import com.market.worker.WorkerEditor;
import com.market.worker.WorkerEliminator;

public class AdminGuide {

	private String command;
	private String[] arr;
	private Scanner s;
	
	public AdminGuide()
	{
		s=new Scanner(System.in);
		System.out.print(">>");
		this.command=s.nextLine();
	}
	public String getCommand()
	{
		return command;
	}
	public void allocate(String command) throws ClassNotFoundException, SQLException
	{
		this.command=command;
		arr=command.trim().split("\\s+");
		
		if(arr[0].toLowerCase().trim().equals("create"))
		{
			if(arr[arr.length-1].trim().toLowerCase().equals("store"))
			{
				
				StoreCreator store=new StoreCreator();
				try
				{
					store.create();
				}
				catch(Exception e)
				{
					System.out.println("ScGuide->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("product"))
			{
				ProductCreator product=new ProductCreator();
				try
				{
					product.create();
				}
				catch(Exception e)
				{
					System.out.println("pcGuide->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("worker"))
			{
				WorkerCreator user=new WorkerCreator();
				try
				{
					user.create();
				}
				catch(Exception e)
				{
					System.out.println("ucGuide->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("unit"))
			{
				UnitCreator unit=new UnitCreator();
				try
				{
					unit.create();
				}
				catch(Exception e)
				{
					System.out.println("uncGuide->"+e);
				}
			}
			else
			{
				System.out.println("Command not found");
			}
		}
		else if(arr[0].toLowerCase().trim().equals("edit")||arr[0].toLowerCase().equals("update"))
		{
			if(arr[arr.length-1].trim().toLowerCase().equals("worker"))
			{
					WorkerEditor user=new WorkerEditor();
					try
					{
						user.edit();
					}
					catch(Exception e)
					{
						System.out.println("ueGuide->"+e);
					}
				
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("admin"))
			{
				AdminEditor editor=new AdminEditor();
				try
				{
					editor.edit();
				}
				catch(Exception e)
				{
					System.out.println("Adminguide->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("product"))
			{
					ProductEditor product=new ProductEditor();
					try
					{
						product.update();
					}
					catch(Exception e)
					{
						System.out.println("peGuide->"+e);
					}
					
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("store"))
			{
				StoreEditor store=new StoreEditor();
				try
				{
					store.update();
				}
				catch(Exception e)
				{
					System.out.println("seGuide->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("unit"))
			{
				UnitEditor unit=new UnitEditor();
				try
				{
					unit.update();
				}
				catch(Exception e)
				{
					System.out.println("uneGuide->"+e);
				}
			}
			else
			{
				System.out.println("Command not found");
			}
		}
		else if(arr[0].toLowerCase().trim().equals("delete"))
		{
			if(arr[arr.length-1].trim().toLowerCase().equals("store"))
			{
				
				StoreEliminator store=new StoreEliminator();
				try
				{
					store.delete();
				}
				catch(Exception e)
				{
					System.out.println("sdGuide->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("admin"))
			{
				AdminEliminator admin=new AdminEliminator();
				try
				{
					admin.delete();
				}
				catch(Exception e)
				{
					System.out.println("Admindelete->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("product"))
			{

				ProductEliminator product=new ProductEliminator();
				try
				{
					product.delete();
				}
				catch(Exception e)
				{
					System.out.println("pdGuide->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("worker"))
			{

				WorkerEliminator user=new WorkerEliminator();
				try
				{
					user.delete();
				}
				catch(Exception e)
				{
					System.out.println("udGuide->"+e);
				}
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("unit"))
			{

				UnitEliminator unit=new UnitEliminator();
				try
				{
					unit.delete();
				}
				catch(Exception e)
				{
					System.out.println("undGuide->"+e);
				}
			}
			else
			{
				System.out.println("Command not found");
			}
		}
		else if(arr[0].toLowerCase().equals("list"))
		{
			if(arr[1].trim().toLowerCase().equals("product"))
			{	
				ProductService product=new ProductService();
				if(arr.length>=5&&arr[3].trim().toLowerCase().equals("10")&&arr[2].equals("-p"))
				{
					product.paginate(arr[4]);
				}
				else if(arr.length>=3&&arr[2].equals("-s"))
				{
					product.search();
				}
				else if(arr.length==2)
				{
					product.list();
				}
				else if(arr[1].trim().toLowerCase().equals("product")&&arr.length==3)
				{
					product.displayCat(arr[arr.length-1]);
				}
				else
				{
					System.out.println("Command not found");
				}
			}
			else if(arr[1].trim().toLowerCase().equals("unit"))
			{
				ProductService service=new ProductService();
				service.listUnit();
			}
			else if(arr[1].toLowerCase().trim().equals("records"))
			{
				ProductService service=new ProductService();
				if(arr.length==3)
				{
					service.displayCat();
				}
				else
				{
					service.displayRecords();
				}
			}
			else if(arr[1].trim().toLowerCase().equals("worker"))
			{
				WorkerService user=new WorkerService();
				
				if(arr.length>=5&&arr[3].trim().toLowerCase().equals("10")&&arr[2].equals("-p"))
				{
					user.paginate(arr[4]);
				}
				else if(arr.length>=3&&arr[2].equals("-s"))
				{
					user.search();
				}
				else if(arr.length==2)
				{
					user.list();
				}
				
				else 
				{
					System.out.println("Command not found");
				}
			}
			else
			{
				System.out.println("Command not found");
			}
		}
		else if(arr[0].toLowerCase().equals("count"))
		{
			if(arr[arr.length-1].trim().toLowerCase().equals("worker"))
			{
				WorkerService user=new WorkerService();
				user.count();
			}
			else if(arr[arr.length-1].trim().toLowerCase().equals("product"))
			{
				ProductService product=new ProductService();
				product.count();
			}
		}
		else if(arr[0].trim().toLowerCase().equals("help"))
		{
			AdminHelper helper=new AdminHelper();
			helper.help();
		}
		else if(arr[0].trim().toLowerCase().equals("exit"))
		{
			Guide guide=new Guide();
			guide.start();
		}
		else
		{
			System.out.println("Command not found");
		}
	}
}
