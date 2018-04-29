package milankabjelic2626.projekat;

import java.io.IOException;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) throws IOException 
    {
        ReadFromFile rff = new ReadFromFile();
        Library library = rff.readFromFile();
/*
        User user1 = new User(0, "Pera", "Peric");
        User user2 = new User(1, "Jovan", "Jovic");
        User user3 = new User(2, "Marko", "Markovic");
        
        user1.addBookToUser(library.findByBarCode("111"));
        user2.addBookToUser(library.findByBarCode("111"));
        
        System.out.println(library.findByBarCode("111").getStanje());
        
        user3.addBookToUser(library.findByBarCode("111"));
        
        library.findByBarCode("111").addStanje(4);
        user3.listUserBooks();
        System.out.println(library.findByBarCode("111").getStanje());
    */    
        int izbor;
        
        do{
            
            Scanner ulaz = new Scanner(System.in);
            
            System.out.println("Izaberite jednu od sledecih opcija\n");
            System.out.println("1. Dodajte novu knjigu\n");
            System.out.println("2. Izbrisite knjigu po naslovu\n");
            System.out.println("3. Izbrisite knjigu po bar-kodu\n");
            System.out.println("4. Sortiraj knjige po autoru\n");
            System.out.println("5. Sortiraj knjige po bar-kodu\n");
            System.out.println("6. Prikazi sve knjige odredjenog autora\n");
            System.out.println("7. Prikazu sve kanjige\n");
            System.out.println("8. Izlaz\n");
            izbor = ulaz.nextInt();
            
            if(izbor >= 1 && izbor <= 7)
            {
                switch(izbor)
                {
                    case 1:
                        Book novaBook = new Book();
                        System.out.println("Naslov: ");
                        novaBook.setNaslov(ulaz.next());
                        System.out.println("Autor: ");
                        novaBook.setAutor(ulaz.next());
                        System.out.println("Izdavac: ");
                        novaBook.setIzdavac(ulaz.next());
                        System.out.println("Godina izdavanja: ");
                        novaBook.setGodinaIzdavanja(ulaz.nextInt());
                        System.out.println("Bar-kod: ");
                        novaBook.setBarKod(ulaz.next());
                        System.out.println("Stanje: ");
                        novaBook.setStanje(ulaz.nextInt());
                        String novi = novaBook.getNaslov() + "," + novaBook.getAutor() 
                                + "," + novaBook.getIzdavac() + "," + novaBook.getGodinaIzdavanja() 
                                + "," + novaBook.getBarKod()+ "," + novaBook.getStanje();
                        library.addBook(novaBook);
                        library.writeToFile(novi);
                        break;
                    case 2:
                        System.out.println("Naslov: ");
                        library.deleteByBookName(ulaz.next());
                        break;
                    case 3:
                        System.out.println("Bar-kod: ");
                        library.deleteByBarCode(ulaz.next());
                        break;
                    case 4:                    
                        library.sortByAuthor();
                    case 5:
                        library.sortByBarCode();
                    case 6:
                        System.out.println("Autor: ");                        
                        library.printFiltered(library.filterByAuthor(ulaz.next()));
                        //library.filterByAuthor(ulaz.next());
                        break;
                    case 7:
                        library.printAll();
                        break;
                    default:
                        System.out.println("Greska pri izboru opcije, pokusajte ponovo.\n");
                        break;
                }
            }
            
            }while(izbor != 8);
        
        
        /*
        //library.deleteByBookName("Zoki");
        //library.deleteByBarCode("333");
        System.out.println("Pre");
        library.printAll();
        
        // Po autoru
        library.sortByAuthor();
        System.out.println("\nPo autoru");
        library.printAll();
        
        // Po bar kodu
        library.sortByBarCode();
        System.out.println("\nPo kar kodu");
        library.printAll();
        
        //library.filterByAuthor("Dusan");
        //library.filterByAuthor("Mica");
        */       
    }   
}
