package milankabjelic2626.projekat;

import java.util.ArrayList;

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
                System.out.println("Uspesno ste iznajmili knjigu!\n");
            }else{
                book.addToQueue(this);
                System.out.println("Dodali smo Vas na cekanje!");
            }
    }
    
    public void listUserBooks(){
        userBooks.forEach(b -> b.bookToString());
    }
    
    public void printUser(){
        System.out.println("Id: " + id + "\n" + "Ime: " + name + "\n" + "Prezime: " + lastname + "\n\n");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public ArrayList<Book> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(ArrayList<Book> userBooks) {
        this.userBooks = userBooks;
    }
}
