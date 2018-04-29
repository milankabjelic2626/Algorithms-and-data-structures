package milankabjelic2626.projekat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main 
{
    public static void main(String[] args) throws IOException 
    {
        File file = new File();
        Library library = file.readFromFile();
        
        Users users = new Users();

        User korisnik1 = new User(0, "Pera", "Peric");
        User korisnik2 = new User(1, "Jovan", "Jovic");
        User korisnik3 = new User(2, "Marko", "Markovic");
        
        users.addUser(korisnik1);
        users.addUser(korisnik2);
        users.addUser(korisnik3);
        
        int izbor;
        
        do{
            
            BufferedReader ulaz = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Izaberite jednu od sledecih opcija\n");
            
            System.out.println("1. Dodajte novu knjigu");
            System.out.println("2. Izbrisite knjigu po naslovu");
            System.out.println("3. Izbrisite knjigu po bar-kodu");
            System.out.println("4. Sortiraj knjige po autoru");
            System.out.println("5. Sortiraj knjige po bar-kodu");
            System.out.println("6. Prikazi sve knjige odredjenog autora");
            System.out.println("7. Prikazu sve knjige");
            System.out.println("8. Azuriraj fajl");
            System.out.println("9. Lista korisnika");
            System.out.println("10. Iznajmi knjigu");
            System.out.println("11. Ispisi knjige korisnika");
            System.out.println("12. Dodaj stanje");
            System.out.println("13. Izlaz");
            
            izbor = Integer.parseInt(ulaz.readLine());
            
            if(izbor >= 1 && izbor <= 12)
            {
                switch(izbor)
                {
                    case 1:
                        Book novaBook = new Book();
                        System.out.println("Naslov: ");
                        novaBook.setNaslov(ulaz.readLine());
                        System.out.println("Autor: ");
                        novaBook.setAutor(ulaz.readLine());
                        System.out.println("Izdavac: ");
                        novaBook.setIzdavac(ulaz.readLine());
                        System.out.println("Godina izdavanja: ");
                        novaBook.setGodinaIzdavanja(Integer.parseInt(ulaz.readLine()));
                        System.out.println("Bar-kod: ");
                        novaBook.setBarKod(ulaz.readLine());
                        System.out.println("Stanje: ");
                        novaBook.setStanje(Integer.parseInt(ulaz.readLine()));
                        
                        library.addBook(novaBook);
                        file.writeToFile(novaBook.toString());
                        break;
                    case 2:
                        System.out.println("Naslov: ");
                        library.deleteByBookName(ulaz.readLine());
                        break;
                    case 3:
                        System.out.println("Bar-kod: ");
                        library.deleteByBarCode(ulaz.readLine());
                        break;
                    case 4:                    
                        library.sortByAuthor();
                        break;
                    case 5:
                        library.sortByBarCode();
                        break;
                    case 6:
                        System.out.println("Autor: ");
                        library.printFiltered(library.filterByAuthor(ulaz.readLine()));
                        break;
                    case 7:
                        library.printAll();
                        break;
                    case 8:
                        file.update(library.getLibrary());
                        izbor = 9;
                        break;
                    case 9:
                        users.listUsers();
                        break;
                    case 10:
                        System.out.println("Unesite id korisnika: ");
                        int id = Integer.parseInt(ulaz.readLine());
                        System.out.println("Unesite bar-kod knjige: ");
                        String barKod = ulaz.readLine();
                        users.addBookToUser(id, barKod, library);
                        break;
                    case 11:
                        System.out.println("Unesite id korisnika: ");
                        int idK = Integer.parseInt(ulaz.readLine());
                        users.listUserBooks(idK);
                        break;
                    case 12:
                        System.out.println("Unesite Bar kod za knjigu");
                        String kod = ulaz.readLine();
                        System.out.println("Novi broj knjiga na stanju: ");
                        int novoStanje = Integer.parseInt(ulaz.readLine());
                        library.findByBarCode(kod).addStanje(novoStanje);
                        break;
                    default:
                        System.out.println("Greska pri izboru opcije, pokusajte ponovo.\n");
                        break;
                }
            }
            
            }while(izbor != 13);      
    }   
}
