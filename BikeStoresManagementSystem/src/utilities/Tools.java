/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is used to store methods for inputting.
 * @author phamm
 */
public class Tools {

    public static Scanner sc = new Scanner(System.in);
    public static boolean loopMore = true;
    /**
     * This method is used to get a choice from the menu.
     * @param msg The announcement when users input data.
     * @param err The announcement when input is error.
     * @return 
     */
    public static int getAChoice(String msg, String err) {
        int a = 0;
        System.out.println("======== BIKE STORES MANAGEMENT ========\n"
                        + "1. Add a product.\n"
                        + "2. Search product by product name.\n"
                        + "3. Update product.\n"
                        + "4. Delete product.\n"
                        + "5. Save products to file.\n"
                        + "6. Print list products from the file.\n"
                        + "========================================");
        while (loopMore) {
            try {
                System.out.print(msg);
                a = Integer.parseInt(sc.nextLine());
                return a;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
        return 0;
    }

    /**
     * This method allow users to input a String from keyboard.
     * @param msg The announcement when users input data.
     * @param err The announcement when input is error.
     * @return String that users have just input.
     */
    public static String inputAString(String msg, String err) {
        String str = null;
        while (loopMore) {
            try {
                System.out.print(msg);
                str = sc.nextLine().trim().replaceAll("\\s+", " ");
                return str;
            } catch (Exception e) {
                System.out.println(msg);
            }
        }
        return null;
    }

    /**
     * This method allow users to input an integer from keyboard.
     *
     * @param msg The announcement when users input data.
     * @param err The announcement when input is error.
     * @param min The minimum integer that users can input.
     * @param max The maximum integer that users can input.
     * @return The integer number that users have just input
     */
    public static int inputAnInteger(String msg, String err, int min, int max) {
        String convert = null;
        int a = 0;

        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }

        while (loopMore) {
            try {
                System.out.print(msg);
                convert = sc.nextLine().trim();
                if (convert.isEmpty()) {
                    return a;
                } else {
                    a = Integer.parseInt(convert);

                    if (a <= min || a >= max) {
                        throw new Exception();
                    }
                    return a;
                }
            } catch (Exception e) {
                System.out.println(err);
            }
        }
        return 0;
    }

    /**
     * This method allow users to input an integer from keyboard.
     *
     * @param msg The announcement when users input data.
     * @param err The announcement when input is error.
     * @param min The minimum double number that users can input.
     * @return The double number that users have just input.
     */
    public static double inputADouble(String msg, String err, double min) {
        String convert = null;
        double a = 0;

        while (loopMore) {
            try {
                System.out.print(msg);
                convert = sc.nextLine().trim();
                if (convert.isEmpty()) {
                    return a;
                } else {
                    a = Double.parseDouble(convert);

                    if (a <= min) {
                        throw new Exception();
                    }
                    return a;
                }
            } catch (Exception e) {
                System.out.println(err);
            }
        }
        return 0;
    }

    /**
     * This method allow users to input an ID from keyboard.
     *
     * @param msg The announcement when users input data.
     * @param err The announcement when input is error.
     * @param filePath The path to the file contains valid ID.
     * @return The valid ID that users have just input.
     */
    public static String inputAnId(String msg, String err, String filePath) {
        String format = "^[B-C]\\d{3}$";
        boolean check = false;

        while (loopMore) {
            try {
                System.out.print(msg);
                String id = sc.nextLine().trim();
                check = id.matches(format);

                if ((!check && !id.isEmpty()) || !checkId(id, filePath)) {
                    throw new Exception();
                }

                return id;
            } catch (Exception e) {
                System.out.println(err);
            }
        }
        return null;
    }

    /**
     * This method is used to check if the ID is existed in file.
     *
     * @param id The ID you want to check.
     * @param filePath The path to the file contains valid ID.
     * @return true if it is existed, otherwise false.
     */
    public static boolean checkId(String id, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",\\s*");

                if (splitLine[0].equalsIgnoreCase(id) || id.isEmpty()) {
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }
    
    /**
     * This method allow users to input a Product's ID from keyboard.
     * @param msg The first announcement when users input data.
     * @param err The announcement when input is error.
     * @return The valid ID that users have just input.
     */
    public static String inputProductId(String msg, String err) {
        String format = "^[P]\\d{3}$";
        boolean check = false;

        while (loopMore) {
            try {
                System.out.print(msg);
                String id = sc.nextLine().trim();
                check = id.matches(format);

                if (!check || id.isEmpty()) {
                    throw new Exception();
                }

                return id;
            } catch (Exception e) {
                System.out.println(err);
            }
        }
        return null;
    }
    
    /**
     * This method is used to generate an unique Product ID when users add a new Product.
     * @param index The current index or count used to generate the ID. It represents the sequential number that will be included in the Product ID.
     * @return valid Product ID.
     */
    public static String generateId(int index) {
        return String.format("P%03d", index);
    }
    
    /**
     * This method is used to ask to go back to the main menu.
     * @return true when they want to continue.
     */
    public static boolean askToContinue() {
        System.out.println("\nWould you like to continue or go back to the main menu?\n"
                        + "(Y) to continue,\n"
                        + "or press any key to go back to the main menu.");
        return (sc.nextLine().trim().toUpperCase().matches("Y"));
    }
}