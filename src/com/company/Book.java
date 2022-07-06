package com.company;

import java.io.Serializable;

public class Book implements Serializable, Comparable<Book> {
    //IInitializing the variables
    private static final long serialVersionUID = -6819779269432320369L;
    public String title;
    public String author;
    public int year;
    public long isbn;

    //Creating a argument function to take input from other classes
    public Book(String title,String author, int year, long isbn){
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    //Creating module to display output of the object
    public String displayBook(){
        String display = ("Title: "+this.title+"\nAuthor: "+this.author+"\nYear: "+this.year+"\nISBN: "+this.isbn);
        return display;
    }

    //Arranging file content in ascending order
    @Override
    public int compareTo(Book book) {
        //Comparing the title of the Book
        if (this.title.compareTo(book.title) > 0) {
            return 1;
        } else if (this.title.compareTo(book.title) < 0) {
            return -1;
        } else {
            //Then Comparing author of the book
            if (this.author.compareTo(book.author) > 0) {
                return 1;
            } else if (this.author.compareTo(book.author) < 0) {
                return -1;
            } else {
                //Then comparing year of the book
                if ((String.valueOf(this.year)).compareTo(String.valueOf(book.year)) > 0) {
                    return 1;
                } else if ((String.valueOf(this.year)).compareTo(String.valueOf(book.year)) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
