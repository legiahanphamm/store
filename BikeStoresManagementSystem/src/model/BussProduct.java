/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import utilities.Tools;
import java.util.ArrayList;

/**
 * This class is used to store methods of the Product list.
 *
 * @author phamm
 */
public class BussProduct {

    private ArrayList<Product> list;
    private String pathFile;

    /**
     * Default constructor.
     */
    public BussProduct() {
        this.list = new ArrayList<>();
    }

    /**
     * BussProduct constructor.
     *
     * @param pathFile The link to file contains Product's information.
     */
    public BussProduct(String pathFile) {
        this.list = new ArrayList<>();
        this.pathFile = pathFile;
    }

    /**
     * This method is used to check if the list is empty or not.
     *
     * @return true if the list is empty, otherwise false.
     */
    public boolean isEmptyList() {
        return this.list.isEmpty();
    }

    /**
     * This method is used to add a Product.
     *
     * @param x The Product will be added to the list.
     */
    public void addAProduct(Product x) {
        while (x.getName().isEmpty() || x.getBrandId().isEmpty() || x.getCategoryId().isEmpty()
                || x.getModelYear() == 0 || x.getPrice() == 0) {
            System.out.println("You have just input at least one blank information. Please input again!");
            x = inputProduct(x.getId());
        }
        this.list.add(x);
    }

    /**
     * This method is used to allow users to input Product's information from
     * the keyboard.
     *
     * @param id Product ID
     * @return The Product users have just input.
     */
    public Product inputProduct(String id) {
        Product x = new Product();
        
        x.setId(id);
        x.setName(Tools.inputAString("Name: ", "Please input Name again!"));
        x.setBrandId(Tools.inputAnId("Brand ID: ", "Please input Brand ID again!", "src/Brand.txt"));
        x.setCategoryId(Tools.inputAnId("Category ID: ", "Please input Category ID again!", "src/Category.txt"));
        x.setModelYear(Tools.inputAnInteger("Model Year: ", "Please input Model Year again!", 0, 2025));
        x.setPrice(Tools.inputADouble("Price: ", "Please input Price again!", 0));

        return x;
    }

    /**
     * This method is used to search Product by Name or ID.
     *
     * @param inputValue The ID or name that users input from keyboard to search Product.
     * @param productValue The ID or name of Product that use to check if the inputValue is existed or not.
     * @return true if that Product is existed, otherwise false.
     */
    public boolean searchAProduct(String inputValue, String productValue) {
        if (inputValue.equalsIgnoreCase(productValue) || productValue.toLowerCase().contains(inputValue.toLowerCase())) {
            return true;
        }
        return false;
    }
    
    /**
     * This method is used to search Product by Name.
     * @param name 
     */
    public void searchProductByName(String name) {
        BussProduct searchResult = new BussProduct();
        for (Product s : list) {
            if(searchAProduct(name, s.getName()))
                searchResult.addAProduct(s);
        }
        if(searchResult.isEmptyList())
            System.out.println("Not found any Product with Name: " + name);
        else {
            System.out.println("The search result sorted ascending by model year");
            searchResult.sortProducts(1);
            searchResult.displayAll();
        }
    }

    /**
     * This method is used to sort Products by (1): Model Year - Ascending and
     * default: Price - Descending
     *
     * @param choice This param will allow users to choose sort by Year or
     * Price.
     */
    public void sortProducts(int choice) {
        if (choice == 1) {
            list.sort((p1, p2) -> Integer.compare(p1.getModelYear(), p2.getModelYear()));
        } else {
            list.sort((p1, p2) -> {
                int priceComparison = Double.compare(p2.getPrice(), p1.getPrice()); // Descending price.
                if (priceComparison != 0) {
                    return priceComparison;
                }
                return p1.getName().compareTo(p2.getName()); // Ascending name.
            });
        }
    }
    
    /**
     * This product is used to 
     * @param id
     * @return 
     */
    public Product searchProductById(String id) {
        for (Product s : list) {
            if(searchAProduct(id, s.getId()))
                return s;
        }
        return null;
    }

    /**
     * This method is used to update Product's information, find it by the ID
     * input from the keyboard.
     *
     * @param id The ID that users input from keyboard to search Product.
     */
    public void updateProduct(String id) {
        if (searchProductById(id) != null) {
            Product u = searchProductById(id);
            System.out.println("Found Product with ID: " + id);
            u.showInfo();
            Product x = inputProduct(id);
            u.setName(x.getName().isEmpty() ? u.getName() : x.getName());
            u.setBrandId(x.getBrandId().isEmpty() ? u.getBrandId() : x.getBrandId());
            u.setCategoryId(x.getCategoryId().isEmpty() ? u.getCategoryId() : x.getCategoryId());
            u.setModelYear(x.getModelYear() == 0 ? u.getModelYear() : x.getModelYear());
            u.setPrice(x.getPrice() == 0 ? u.getPrice() : x.getPrice());

            System.out.println("Updated successfully.");
            System.out.println("Product " + u.getId() + "'s new information:");
            u.showInfo();
        } else {
            System.out.println("Not found any Product with ID: " + id);
            System.out.println("Updated unsuccessfully.");
        }    
    }

    /**
     * This method is used to delete Product's information, find it by the ID
     * input from the keyboard.
     *
     * @param id The ID that users input from keyboard to search Product.
     */
    public void deleteProduct(String id) {
        for (Product d : list) {
            if (searchAProduct(id, d.getId())) {
            list.remove(d);
            System.out.println("Deleted successfully.");

            System.out.println("Product's List after deleting");
                if (!list.isEmpty()) {
                    displayAll();
                } else {
                    System.out.println("You have just deleted the last Product in this list.");
                }
            return;
            }
        }
        System.out.println("Not found any Product with ID: " + id);
        System.out.println("Deleted unsuccessfully.");
    }

    /**
     * This method is used to print all the Product list.
     */
    public void displayAll() {
        System.out.println("|---------------------------------------------------------------------------------------|");
        System.out.println("|  ID  |        Name        |  Brand ID  |  Category ID  |  Model year  |     Price     |");
        System.out.println("|---------------------------------------------------------------------------------------|");
        for (Product p : list) {
            System.out.printf("| %4s | %-18s |    %4s    |     %4s      |     %d     | %-13.3f |\n",
                            p.getId(), p.getName(), p.getBrandId(), p.getCategoryId(), p.getModelYear(), p.getPrice());
            System.out.println("|---------------------------------------------------------------------------------------|");
        }
    }

    /**
     * This method is used to write a list of Product's information to the file
     * (Product.txt).
     * @param f The file to write information.
     */
    public void saveToFile(File f) {
        boolean status = false;
        try {
//            //Create a new file
//            File f = new File(this.pathFile);

            //Stream output
            FileOutputStream fos = new FileOutputStream(f);

            //Create an Object Output Stream
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //Write Object's data
            for (Product p : list) {
                oos.writeObject(p);
            }

            //Close
            oos.close();
            fos.close();
            status = true;
        } catch (FileNotFoundException fnf) {
            System.out.println("Invalid file path!");
        } catch (IOException ioe) {
            System.out.println("Error while writing Object's data!");
        }
        if (status)
            System.out.println("Saved successfully.");
        else
            System.out.println("Error. Saved unsuccessfully.");
    }
    
    /**
     * This method is used to print all lists from file.
     * @param f The file that stores list's information.
     */
    public void printFromFile(File f) {
        try {
//            //Create a new file
//            File f = new File(this.pathFile);

            //Stream input
            FileInputStream fis = new FileInputStream(f);

            //Create an Object Input Stream
            ObjectInputStream ois = new ObjectInputStream(fis);

            //Read data from file
            BussProduct bp = new BussProduct();
        try {
            while (true) {
                Product p = (Product) ois.readObject();
                bp.addAProduct(p);  // Add each product read from the file
            }
        } catch (EOFException eof) {
        }
            ois.close();
            fis.close();
            bp.sortProducts(2);
            bp.displayAll();

        } catch (FileNotFoundException fnf) {
            System.out.println("Empty file. You need to add at least one Product to file.");
        } catch (IOException ioe) {
            System.out.println("Error while reading data!");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Not Found Class!");
        }
    }
}