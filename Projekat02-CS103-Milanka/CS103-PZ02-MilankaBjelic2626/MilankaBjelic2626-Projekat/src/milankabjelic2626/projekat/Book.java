package milankabjelic2626.projekat;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Book 
{   
    private String naslov;
    private String autor;
    private String izdavac;
    private int godinaIzdavanja;
    private String barKod;
    private int stanje;
    
    Queue<User> queue = new LinkedList<User>();

    public Book() 
    {   
    }

    public void addToQueue(User user)
    {
        queue.add(user);
    }
    
    public int getStanje() 
    {
        return stanje;
    }

    public void setStanje(int stanje) 
    {
        this.stanje = stanje;
    }
    
    public void addStanje(int stanje)
    {
        this.stanje = stanje;
        for (Iterator<User> iterator = queue.iterator(); iterator.hasNext();) {
            User next = iterator.next();
            next.addBookToUser(this);
            iterator.remove();
        }       
    }   
    
    public Book(String naslov, String autor, String izdavac, int godinaIzdavanja, String barKod, int stanje) 
    {
        this.naslov = naslov;
        this.autor = autor;
        this.izdavac = izdavac;
        this.godinaIzdavanja = godinaIzdavanja;
        this.barKod = barKod;
        this.stanje = stanje;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public int getGodinaIzdavanja() {
        return godinaIzdavanja;
    }

    public void setGodinaIzdavanja(int godinaIzdavanja) {
        this.godinaIzdavanja = godinaIzdavanja;
    }

    public String getBarKod() {
        return barKod;
    }

    public void setBarKod(String barKod) {
        this.barKod = barKod;
    }

    public void bookToString() {
        System.out.println("Naslov: " + naslov + "\nAutor: " + autor + "\nIzdavac: " + izdavac + "\nGodina izdavanja: " + godinaIzdavanja + "\nBar-kod: " + barKod + "\nStanje: " + stanje + "\n\n");
    }

    @Override
    public String toString() {
       return naslov + "," + autor + "," + izdavac + "," + godinaIzdavanja + "," + barKod + "," + stanje;
    }
    
    public static class AuthorSorting implements Comparator<Book>
    {
        @Override
        public int compare(Book b1, Book b2) 
        {
            return b1.getAutor().compareTo(b2.getAutor());
        }
        
    }
     
    public static class BarCodeSorting implements Comparator<Book>
    {
        @Override
        public int compare(Book b1, Book b2) 
        {
            return b1.getBarKod().compareTo(b2.getBarKod());
        }       
    }    
}
