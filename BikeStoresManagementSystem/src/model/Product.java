/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * This class is used to store data of the Product.
 * @author phamm
 */
public class Product implements Serializable {
    private String id;
    private String name;
    private String brandId;
    private String categoryId;
    private int modelYear;
    private double price;
    
    /**
     * Default constructor.
     */
    public Product() {
    }
    
    /**
     * The Product constructor.
     * @param id
     * @param name
     * @param brandId
     * @param categoryId
     * @param modelYear
     * @param price 
     */
    public Product(String id, String name, String brandId, String categoryId, int modelYear, double price) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method is used format Product's information to write to file.
     * @return The String format of Product's information.
     */

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %.2f",
                            this.id, this.name, this.brandId, this.categoryId, this.modelYear, this.price);
    }
    
    /**
     * This method is used to show Product's information
     */
    public void showInfo() {
        System.out.println("|---------------------------------------------------------------------------------------|\n"
                        + "|  ID  |        Name        |  Brand ID  |  Category ID  |  Model year  |     Price     |\n"
                        + "|---------------------------------------------------------------------------------------|");
        System.out.printf("| %4s | %-18s |    %4s    |     %4s      |     %d     | %-13.3f |\n",
                            this.id, this.name, this.brandId, this.categoryId, this.modelYear, this.price);
        System.out.println("|---------------------------------------------------------------------------------------|");
        
    }
}