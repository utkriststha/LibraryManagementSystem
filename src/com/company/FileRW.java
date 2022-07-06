package com.company;

import java.io.*;
import java.util.ArrayList;

public class FileRW {
    //Initiating file name and file object as global
    private static final String filename = "book1.txt";
    private static final File file = new File (filename);

    //Creating a class for writing file
    public static void writeToFile (String title, String author, int year, long isbn){
        //Creating object for Book
        Book book = new Book(title,author,year,isbn);
        //initiating object output stream
        ObjectOutputStream outputStream = null;
        try{
            //Run the program if the file exists
            if (!file.exists()) outputStream = new ObjectOutputStream (new FileOutputStream(filename));
            else outputStream = new AppendableOutputStream(new FileOutputStream (filename, true));
            //writes the book object in the file
            outputStream.writeObject(book);
            outputStream.flush ();
        }catch (Exception e){
            e.printStackTrace ();
        }finally{
            //Closes output stream if it is null
            try{
                if (outputStream != null) outputStream.close ();
            }catch (Exception e){
                e.printStackTrace ();
            }
        }
    }

    //Creating module to read file and return file content in arraylist
    public static ArrayList<Book> readFromFile (){
        //Initiating array list
        ArrayList<Book> arrayList = new ArrayList();
        //runs if the file exist
        if (file.exists ()) {
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(new FileInputStream(filename));
                while (true) {
                    //Creating object book to read from the file
                    Book book = (Book) inputStream.readObject();
                    //Adding the book object in arraylist
                    arrayList.add(book);
                }
            }
            //Exception
            catch (EOFException e) {
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //Close input stream if it is null
                try {
                    if (inputStream != null) inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //returns arraylist
        return arrayList;
    }

    //Creating Appendable object output stream for appending on the file
    private static class AppendableOutputStream extends ObjectOutputStream {
        public AppendableOutputStream(FileOutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {}
    }
}
