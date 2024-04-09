package com.FWRP.model;

//Main.java (for testing purposes)
import java.util.List;

public class Main {
 public static void main(String[] args) {
     ItemDAO itemDAO = new ItemDAO();
     List<Item> itemList = itemDAO.getItems();

     for (Item item : itemList) {
         System.out.println(item.getName() + " - $" + item.getPrice());
     }
 }
}
