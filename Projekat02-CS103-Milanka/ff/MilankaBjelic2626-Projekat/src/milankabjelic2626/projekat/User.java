package milankabjelic2626.projekat;

import java.util.ArrayList;
import java.util.Iterator;

public class User 
{
    
    private int id;
    private String name;
    private String lastname;
    
    ArrayList<Book> userBooks = new ArrayList<Book>();

    public User(int id, String name, String lastname) 
    {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }
    
    public boolean hasStanje(Book book)
    {
        if(book.getStanje() != 0)
        {
            return true;
        }else
        {
            return false;
        }
    }
    
    public void addBookToUser(Book book)
    {
            if(hasStanje(book)){
                userBooks.add(book);
                book.setStanje(book.getStanje()-1);
            }else{
                book.addToQueue(this);
                System.out.println("Dodali smo Vas na cekanje!");
            }
    }
    
    public void listUserBooks(){
        userBooks.forEach(b -> b.bookToString());
    }   
}
