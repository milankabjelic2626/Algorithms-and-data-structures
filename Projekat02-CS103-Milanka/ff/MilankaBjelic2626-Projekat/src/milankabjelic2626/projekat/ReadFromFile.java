package milankabjelic2626.projekat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFromFile {
    
    Library library = new Library();

    public Library readFromFile() 
    {
        File file = new File("books.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
               List<String> booksList = Arrays.asList(line.split(","));
               Book book = new Book(booksList.get(0), booksList.get(1), booksList.get(2), Integer.parseInt(booksList.get(3)), booksList.get(4), Integer.parseInt(booksList.get(5)));
            
               library.addBook(book);
            }
        } catch (FileNotFoundException ex) {
        Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return library;
    }   
}
