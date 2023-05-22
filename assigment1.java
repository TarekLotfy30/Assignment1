package assignment1;

import java.util.ArrayList;
import java.util.Scanner;

class MenuItem 
{
    String code;
    String type;
    double price;
    
    public MenuItem(String code, String type, double price) 
    {
        this.code = code;
        this.type = type;
        this.price = price;
    }
}

class CoffeeShop 
{
    String name;
    ArrayList<MenuItem> menu;
    ArrayList<String> orders;
    
    public CoffeeShop(String name, ArrayList<MenuItem> menu) 
    {
        this.name = name;
        this.menu = menu;
        this.orders = new ArrayList<String>();
    }
    
    public String addOrder(String code) 
    {
        for (MenuItem menuItem : menu) 
        {
            if (menuItem.code.equalsIgnoreCase(code)) 
            {
                orders.add(code);
                return "Order added!";
            }
        }
        return "This item is currently unavailable!";
    }
    
    public String fulfillOrder() 
    {
        if (!orders.isEmpty()) 
        {
            String code = orders.get(0);
            orders.remove(0);
            return "The " + code + " is ready!";
        } 
        else 
        {
            return "All orders have been fulfilled!";
        }
    }
    
    public ArrayList<String> listOrders() 
    {
        return orders;
    }
    
    public double dueAmount() 
    {
        double total = 0.0;
        for (String order : orders) 
        {
            for (MenuItem menuItem : menu) 
            {
                if (menuItem.code.equalsIgnoreCase(order)) 
                {
                    total += menuItem.price;
                }
            }
        }
        return total;
    }
    
    public String cheapestItem() 
    {
        double minPrice = Double.MAX_VALUE;
        String cheapest = "";
        for (MenuItem menuItem : menu)
        {
            if (menuItem.price < minPrice) 
            {
                minPrice = menuItem.price;
                cheapest = menuItem.code;
            }
        }
        return cheapest;
    }
    
    public ArrayList<String> drinksOnly() 
    {
        ArrayList<String> drinkList = new ArrayList<String>();
        for (MenuItem menuItem : menu) 
        {
            if (menuItem.type.equalsIgnoreCase("drink")) 
            {
                drinkList.add(menuItem.code);
            }
        }
        return drinkList;
    }
    
    public ArrayList<String> foodOnly() 
    {
        ArrayList<String> foodList = new ArrayList<String>();
        for (MenuItem menuItem : menu) 
        {
            if (menuItem.type.equalsIgnoreCase("food")) 
            {
                foodList.add(menuItem.code);
            }
        }
        return foodList;
    }
}

public class Main 
{
    public static void main(String[] args)
    {
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        menu.add(new MenuItem("L1", "Drink", 3.50));
        menu.add(new MenuItem("C1", "Drink", 3.00));
        menu.add(new MenuItem("E1", "Drink", 2.50));
        menu.add(new MenuItem("Cr1", "Food", 2.00));
        menu.add(new MenuItem("B1", "Food", 2.50));
        
        CoffeeShop coffeeShop = new CoffeeShop("My Coffee Shop", menu);
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) 
        {
            System.out.println("Enter your order (or 'exit' to quit): ");
            String order = scanner.nextLine();
            if (order.equalsIgnoreCase("exit")) {
                break;
            }
            String response = coffeeShop.addOrder(order);
            System.out.println(response);
        }
        
        System.out.println(coffeeShop.listOrders());
        
        System.out.println("Total due: " + coffeeShop.dueAmount());
        
        System.out.println("Cheapest item: " + coffeeShop.cheapestItem());
    }
}
