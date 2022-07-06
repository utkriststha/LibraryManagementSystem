package com.company;

import java.io.*;
import java.util.*;

public class Main extends FileRW {

    public static void main(String[] args) throws ISBNFormatError,IOException,InvalidClassException,StreamCorruptedException {
        //Creating scanner object as input
        Scanner input = new Scanner(System.in);

        //Initializing boolean flag
        boolean flag = true;

        //Initializing option
        int option;

        //Creating a loop to run the program unless the user wants to exit
        while (flag) {
            //Creating try catch to hold the exceptions
            try {
                //Displaying the option menu
                System.out.println("\n## Welcome to the Home Library System ##");
                System.out.println("Please select one of the following options:");
                System.out.println("1. Add a new book");
                System.out.println("2. Delete a book");
                System.out.println("3. Search for a book");
                System.out.println("4. Display all books");
                System.out.println("5. Exit");
                //Asking the user of input
                System.out.print("What would you like to do? [1|2|3|4|5]: ");
                option = input.nextInt();

                //If the user choose option 1
                if (option == 1) {
                    System.out.println("Please enter the following: ");
                    System.out.print("Title of the book: ");
                    //Allowing users to input string with space
                    String title = input.useDelimiter("\n").next();
                    title = title.replaceAll("\n", "_");
                    System.out.print("Author of the book: ");
                    //Allowing users to input string with space
                    String author = input.useDelimiter("\n").next();
                    author = author.replaceAll("\n", "_");
                    System.out.print("Year of publication: ");
                    int year = input.nextInt();
                    //To make sure the year has 4 digits.
                    while (yearCheck(year)) {
                        System.out.print("The year you enter should be in 4 digit: ");
                        year = input.nextInt();
                    }
                    System.out.print("ISBN number: ");
                    long isbn = input.nextLong();
                    //To make sure the year has 10 digits and not other similar ISBN.
                    while (isbnCheck(isbn)) {
                        System.out.println("The isbn you enter should be in 10 digit");
                        isbn = input.nextLong();
                    }
                    //Calling writeToFile function
                    writeToFile(title, author, year, isbn);
                }
                //If the user choose option 2
                else if (option == 2) {
                    //Creating Arraylist
                    ArrayList<Book> arrayList = new ArrayList<>();
                    arrayList.addAll(readFromFile());
                    //Displaying the option
                    System.out.println("Search a book for deletion by");
                    System.out.println("1. Title of the book");
                    System.out.println("2. Author of the book");
                    System.out.println("3. Year of publication");
                    System.out.println("4. ISBN number");
                    //Asking user for input
                    System.out.print("Option: ");
                    int deleteOption = input.nextInt();
                    //If the user choose 1
                    if (deleteOption == 1) {
                        System.out.print("Enter the title of the book: ");
                        String title = input.useDelimiter("\n").next();
                        title = title.replaceAll("\n", "_");
                        //Creating loop to access all object in the file
                        for (Book book : arrayList) {
                            //If the input matches any object it will delete the file
                            if (title.equals(book.title)) {
                                arrayList.remove(book);
                                updateBook(arrayList);
                                for (Book bo : arrayList) {
                                    System.out.println(bo.displayBook() + "\n");
                                }
                                System.out.println("Book successfully deleted");
                                break;
                            }
                        }
                    }
                    //If the user choose 2
                    else if (deleteOption == 2) {
                        System.out.print("Enter the author of the book:");
                        String author = input.useDelimiter("\n").next();
                        author = author.replaceAll("\n", "_");
                        //Creating loop to access all object in the file
                        for (Book book : arrayList) {
                            //If the input matches any object it will delete the file
                            if (author.equals(book.author)) {
                                arrayList.remove(book);
                                updateBook(arrayList);
                                System.out.println("Book successfully deleted");
                                break;
                            }
                        }
                    }
                    //If the user choose 3
                    else if (deleteOption == 3) {
                        System.out.print("Year of publication: ");
                        int year = input.nextInt();
                        while (yearCheck(year)) {
                            System.out.print("The year you enter should be in 4 digit: ");
                            year = input.nextInt();
                        }
                        //Creating loop to access all object in the file
                        for (Book bookYear : arrayList) {
                            //If the input matches any object it will delete the file
                            if (String.valueOf(year).equals(String.valueOf(bookYear.year))) {
                                arrayList.remove(bookYear);
                                updateBook(arrayList);
                                System.out.println("Book successfully deleted");
                                break;
                            }
                        }
                    }
                    //If the user choose 3
                    else if (deleteOption == 4) {
                        System.out.print("ISBN number: ");
                        long isbn = input.nextLong();
                        while (isbnCheck(isbn)) {
                            System.out.println("The isbn you enter should be in 10 digit");
                            isbn = input.nextLong();
                        }
                        //Creating loop to access all object in the file
                        for (Book book : arrayList) {
                            //If the input matches any object it will delete the file
                            if (String.valueOf(isbn).equals(String.valueOf(book.isbn))) {
                                arrayList.remove(book);
                                updateBook(arrayList);
                                System.out.println("Book successfully deleted");
                                break;
                            }
                        }
                    }
                    //If the inputs digit other than 1,2,3,4
                    else {
                        System.out.println("Wrong Input!!!");
                    }
                }
                //If the user choose 3
                else if (option == 3) {
                    //Creating Arraylist
                    ArrayList<Book> arrayList = new ArrayList<>();
                    arrayList.addAll(readFromFile());
                    //Displaying option
                    System.out.println("Search for a book by");
                    System.out.println("1. Title of the book");
                    System.out.println("2. Author of the book");
                    System.out.println("3. Year of publication");
                    System.out.println("4. ISBN number");
                    //Asking user for input
                    System.out.print("Option: ");
                    int searchOption = input.nextInt();
                    // if the user choose option 1
                    if (searchOption == 1) {
                        System.out.print("Enter the title of the book: ");
                        String title = input.useDelimiter("\n").next();
                        title = title.replaceAll("\n", "_");
                        //Displays book object with similar titles
                        for (Book book : arrayList) {
                            if (title.equals(book.title)) {
                                System.out.println("\n" + book.displayBook());
                            }
                        }
                    }
                    //if the user choose option 2
                    else if (searchOption == 2) {
                        System.out.print("Enter the author of the book:");
                        String author = input.useDelimiter("\n").next();
                        author = author.replaceAll("\n", "_");
                        //Displays book object with similar author
                        for (Book book : arrayList) {
                            if (author.equals(book.author)) {
                                System.out.println("\n" + book.displayBook());
                            }
                        }
                    }
                    //if the user choose option 3
                    else if (searchOption == 3) {
                        System.out.print("Year of publication: ");
                        int year = input.nextInt();
                        while (yearCheck(year)) {
                            System.out.print("The year you enter should be in 4 digit: ");
                            year = input.nextInt();
                            //Displays book object with similar year
                            for (Book book : arrayList) {
                                if (String.valueOf(year).equals(String.valueOf(book.year))) {
                                    System.out.println("\n" + book.displayBook());
                                }
                            }
                        }
                    }
                    //if the user choose option 4
                    else if (searchOption == 4) {
                        System.out.print("ISBN number: ");
                        long isbn = input.nextLong();
                        while (isbnCheck(isbn)) {
                            System.out.println("The isbn you enter should be in 10 digit");
                            isbn = input.nextLong();
                            //Displays book object with similar isbn
                            for (Book book : arrayList) {
                                if (String.valueOf(isbn).equals(String.valueOf(book.isbn))) {
                                    System.out.println("\n" + book.displayBook());
                                }
                            }
                        }
                    }
                    //if the user choose option other than 1,2,3,4
                    else {
                        System.out.println("Wrong Input!!!");
                    }

                }
                //if the user choose option 4
                else if (option == 4) {
                    //Creating Arraylist
                    ArrayList<Book> arrayList = new ArrayList<>();
                    arrayList.addAll(readFromFile());
                   //Displays all the content of the array file
                    Collections.sort(arrayList);
                    for (Book book : arrayList) {
                        System.out.println(book.displayBook() + "\n");
                    }
                }
                //If the user choose option 5
                else if (option == 5) {
                    //Make the boolean flag = false to exit the program
                    flag = false;
                }

                //if the user choose option other than [1|2|3]
                else {
                    System.out.println("\nWrong Input!! \nPlease choose option from [1|2|3|4|5]");
                }
            }
            //catch ConcurrentModificationException
            //If an exception occur the message will be displayed and the program will start again
            catch (ConcurrentModificationException e) {
            } catch (InputMismatchException e) {
                System.out.println("\nThe data you entered is wrong!! \nPlease enter the correct data type.");
                input.next();
            }
        }
    }

    //Checks isbn inputs
    public static boolean isbnCheck(long isbn) throws ISBNFormatError {
        ArrayList<Book> arrayList = new ArrayList<>();
        arrayList.addAll(readFromFile());
        //Checks the length if ibs
        if (String.valueOf(isbn).length() != 10){
            return true;
        }
        //Checks if there is any already existing isbn number
        for (Book book : arrayList) {
            if (String.valueOf(isbn).equals(String.valueOf(book.isbn))) {
                throw new ISBNFormatError("The ISBN number already exists");
            }
        }
        return false;
    }

    //Checks the year if it has 4 digits or not
    public static boolean yearCheck(int year) {
        return String.valueOf(year).length() != 4;
    }

    //Updates the book file with new array list
    public static void updateBook(ArrayList<Book> arrayList) {
        try {
            FileOutputStream file = new FileOutputStream("book1.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(file);
            for (Book book : arrayList) {
                outputStream.writeObject(book);
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
        } catch (InvalidClassException e){
        } catch (IOException e) {
        }
    }

}
