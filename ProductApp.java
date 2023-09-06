/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productapp;

import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import za.ac.tut.bl.*;
import za.ac.tut.entity.*;

/**
 *
 * @author MNCEDISI
 */
public class ProductApp
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        
        //Database connection
        String dbURL = "jdbc:derby://localhost:1527/ProductDataBase";
        String userName = "ProductDB";
        String password = "123";
        
        try
        {
            ProductManager pm = new ProductManager(dbURL, userName, password);
            Product product = null;
            
            int option = showOption();
            
            do
            {
                switch(option)
                {
                    case 1:
                        //add product into database
                        product = product();
                        
                        if(pm.addProduct(product))
                            System.out.println("The product is added");
                        else
                            System.err.println("The product is not added");
                    break;
                        
                    case 2:
                        //update product
                        product = product();
                        
                        if(pm.updateProduct(product))
                            System.out.println("The product is updated");
                        else
                            System.err.println("The product is not updated");
                    break;
                        
                    case 3:
                        //delete product
                        Integer idNumber = promptingIdNumber();
                        
                        if(pm.deleteProduct(idNumber))
                            System.out.println("The product is deleted");
                        else
                            System.err.println("The product is not deleted");
                    break;
                        
                    case 4:
                        //search or get product
                        idNumber = promptingIdNumber();;
                        
                        product = pm.getProduct(idNumber);
                        
                        if(product!=null)
                            displayProduct(product);
                        else
                            System.err.println("No product much that ID Number");
                    break;
                        
                    case 5:
                        //display all the product store in the database
                        List<Product> theProduct = pm.getAllProduct();
                        
                        if(!theProduct.isEmpty())
                            displayAllProducts(theProduct);
                        else
                            System.err.println("Nothing on the list");
                    break;
                        
                    default:
                        System.err.println("Invalid option.Please re-enter again");
                    break;
                        
                }
                
                option = showOption();
            }
            while(option!=6);
        
        }
        catch (SQLException ex)
        {
            System.err.println("Something went wrong\n"+ex.getMessage());
        }
        
        catch(InputMismatchException ex)
        {
            System.err.println("Invalid option.Enter a digit option");
        }
    }

    private static int showOption() 
    {
        Scanner  sc = new Scanner(System.in);
        
        String menu = "\nPlease select one of the following option :\n\n"+
                       "1. Add a product in the database.\n"+
                       "2. Update the quantity of a product.\n"+
                       "3. Delete a product.\n"+
                       "4. Get product information.\n"+
                       "5. Get all the products.\n"+
                       "6. Exit\n\n"+
                       "Your option :";
        
        System.out.print(menu);
        
        return sc.nextInt();
    }

    private static Product product() 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the ID number of the product you want to [add/update] :");
        Integer idNumber = sc.nextInt();
        
        sc.nextLine();
        
        System.out.print("Enter the description\t:");
        String description = sc.nextLine();
        
        System.out.print("Enter the quantity\t:");
        Integer quantity = sc.nextInt();
        
        System.out.print("Enter the unity price\t:R");
        Double unityPrice = sc.nextDouble();
        
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        
        Product product = new Product(idNumber, description, quantity, unityPrice, timestamp);
        
        return product;
    }

    private static Integer promptingIdNumber() 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Please enter the ID number of the product you want to[search/delete] :");
        
        return sc.nextInt();
    }

    private static void displayProduct(Product product) 
    {
        DecimalFormat dec = new DecimalFormat("#.00");
        
        String outcome = "\nID Number\t:"+product.getIdNumber()+"\n"+
                         "Description\t:"+product.getDescription()+"\n"+
                         "Quantity No\t:"+product.getQuantity()+"\n"+
                         "Unity Price\t:R"+dec.format(product.getUnityPrice())+"\n"+
                         "Time Stamp\t:"+product.getTimestamp()+"\n";
        
        System.out.println(outcome);
    }   

    private static void displayAllProducts(List<Product> theProduct)
    {
        DecimalFormat dec = new DecimalFormat("#.00");
        String outcome = "";
        
        for (Product display : theProduct)
        {
            outcome += "\nID Number\t:"+display.getIdNumber()+"\n"+
                         "Description\t:"+display.getDescription()+"\n"+
                         "Quantity No\t:"+display.getQuantity()+"\n"+
                         "Unity Price\t:R"+dec.format(display.getUnityPrice())+"\n"+
                         "Time Stamp\t:"+display.getTimestamp()+"\n";
        }
        System.out.println(outcome);
    }
}
