
package milankabjelic2626.projekat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Library 
{
    
    ArrayList<Book> library = new ArrayList<>();

    public Library() 
    {      
    }
    
    public void addBook(Book book)
    {
        library.add(book);
    }

    public ArrayList<Book> getLibrary() 
    {
        return library;
    }

    public void setLibrary(ArrayList<Book> library) 
    {
        this.library = library;
    }
    
    public void printAll()
    {
        
        library.forEach(b -> b.bookToString());
        
        /* Istoo
        for(Book b : library){
            b.toString();
        }
        */
    }
    
    public void printFiltered(ArrayList<Book> books)
    {
        books.forEach(b -> b.bookToString());
    }
    
    public ArrayList<Book> filterByAuthor(String author)
    {
        ArrayList<Book> filteredItems = library.stream()
                .filter(b -> b.getAutor().equals(author))
                .collect(Collectors.toCollection(ArrayList::new));
        
        return filteredItems;
    }
    
    public Book findByBarCode(String barCode)
    {
        ArrayList<Book> filteredItems = library.stream()
                .filter(b -> b.getBarKod().equals(barCode))
                .collect(Collectors.toCollection(ArrayList::new));
        
        return filteredItems.get(0);
    }
    
    public void deleteByBookName(String bookName)
    {
        for (Iterator<Book> iterator = library.iterator(); iterator.hasNext();) {
            Book next = iterator.next();
            if(next.getNaslov().equals(bookName)){
                iterator.remove();
            }
        }
    }
    
    public void deleteByBarCode(String barCode)
    {
        for (Iterator<Book> iterator = library.iterator(); iterator.hasNext();) 
        {
            Book next = iterator.next();
            if(next.getBarKod().equals(barCode)){
                iterator.remove();
            }
        }
    }
    
    public void sortByAuthor()
    {
        Collections.sort(library, new Book.AuthorSorting());
    }
    
    public void sortByBarCode()
    {
        Collections.sort(library, new Book.BarCodeSorting());
    }  
    
    public void writeToFile(String novaLinija) throws IOException 
    {
        
        Writer output;
        output = new BufferedWriter(new FileWriter("books.txt", true));
        
        output.append("\n" + novaLinija);
        output.flush();
        output.close();
        
    }
}
