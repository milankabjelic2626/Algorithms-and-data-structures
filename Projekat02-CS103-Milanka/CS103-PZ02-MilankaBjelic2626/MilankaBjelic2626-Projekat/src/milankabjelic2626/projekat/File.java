package milankabjelic2626.projekat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class File 
{    
    Library library = new Library();

    public File() 
    {   
    }
    
    public void update(ArrayList<Book> books)
    {
        
        try {
            BufferedWriter first = new BufferedWriter(new FileWriter("books.txt"));
            first.write("");
            
            BufferedWriter output = new BufferedWriter(new FileWriter("books.txt", true));
            for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
                Book next = iterator.next();
                output.append("\n" + next.toString());
            }
            output.flush();
            output.close();
        } catch (IOException ex) {
            
        }
        
    }
    
    public void writeToFile(String novaLinija) throws IOException 
    {       
        Writer output;
        output = new BufferedWriter(new FileWriter("books.txt", true));
        
        output.append("\n" + novaLinija);
        output.flush();
        output.close();       
    }

    public Library readFromFile() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
               String[] booksList = line.split(Pattern.quote(","));
                if(booksList.length == 6){
                    Book book = new Book(booksList[0], booksList[1], booksList[2], Integer.parseInt(booksList[3]), booksList[4], Integer.parseInt(booksList[5]));
                    library.addBook(book);
                }
            }
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {

        }       
        return library;
    }   
}
