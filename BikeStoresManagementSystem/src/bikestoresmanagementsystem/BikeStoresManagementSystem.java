/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikestoresmanagementsystem;

import java.io.File;
import java.util.Scanner;
import model.BussProduct;
import model.Product;
import utilities.Tools;

/**
 * This class is the Main class.
 * @author phamm
 */
public class BikeStoresManagementSystem {
    
    public static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String pathFile = "src/Product.txt";
        File f = new File(pathFile);
        BussProduct list = new BussProduct(pathFile);
        int choice = 0, index = 0;
        
//        //test
//        list.addAProduct(new Product("P001", "Galaxy", "B001", "C002", 2009, 200));
//        list.addAProduct(new Product("P002", "Bianchi", "B001", "C001", 2012, 333));
//        list.addAProduct(new Product("P003", "Twitter", "B001", "C001", 2020, 234));
//        list.addAProduct(new Product("P004", "ThongNhat", "B002", "C002", 2022, 888));
//        list.addAProduct(new Product("P005", "Cervelo", "B001", "C002", 2019, 234));
//        list.displayAll();
//        index = 5;
//        //endtest
        
        do {
            choice = Tools.getAChoice("Please choose an option: ", "ERROR! Please choose again!");
            switch (choice) {

                //Add a product.
                case 1: {
                    do {
                        System.out.println("You have chosen Option 1: Create a Product.");
                        String id = Tools.generateId(++index);
                        list.addAProduct(list.inputProduct(id));
                        System.out.println("Added successfully.");
                        System.out.println("Product's List after adding");
                        list.displayAll();
                    } while(Tools.askToContinue());
                    break;
                }

                //Search Product by name.
                case 2: {
                    do {
                        System.out.println("You have chosen Option 2: Search Product by name.");
                        if (list.isEmptyList()) {
                            System.out.println("Empty list. Please add at least one Product!");
                            } else {
                            String name = Tools.inputAString("Please input a Product's name to search: ", "Please input again!");
                            list.searchProductByName(name);
                        }
                    } while (Tools.askToContinue());
                    break;
                }

                //Update Product
                case 3: {
                    do {
                        System.out.println("You have chosen Option 3: Update Product");
                        if (list.isEmptyList()) {
                            System.out.println("Empty list. Please add at least one Product!");
                        } else {
                            String id = Tools.inputProductId("Please input a Product's ID (Pxxx): ", "Please input again!");
                            list.updateProduct(id);
                        }
                    } while (Tools.askToContinue());
                    break;
                }

                //Delete Product
                case 4: {
                    do {
                        System.out.println("You have chosen Option 4: Delete Product");
                        if (list.isEmptyList()) {
                            System.out.println("Empty list. Please add at least one Product!");
                        } else {
                            String id = Tools.inputProductId("Please input a Product's ID (Pxxx): ", "Please input again!");
                            list.deleteProduct(id);
                        }
                    } while (Tools.askToContinue());
                    break;
                }

                //Save to file
                case 5: {
                    System.out.println("You have chosen Option 5: Save to file");
                    if (list.isEmptyList()) {
                        System.out.println("Empty list. Please add at least one Product!");
                    } else {
                        list.saveToFile(f);
                    }
                    System.out.println("Press any key to go back to the main menu..");
                    sc.nextLine();
                    break;
                }

                //Print all lists from file
                case 6: {
                    System.out.println("You have chosen Option 6: Print all lists from file");
                    if (list.isEmptyList()) {
                        System.out.println("Empty list. Please add at least one Product!");
                    } else {
                        list.printFromFile(f);
                    }
                    System.out.println("Press any key to go back to the main menu..");
                    sc.nextLine();
                    break;
                }
                default: {
                    f.delete();
                    System.out.println("Thank you. See you again!");
                }
            }

        } while (choice >= 1 && choice <= 6);
    }
}