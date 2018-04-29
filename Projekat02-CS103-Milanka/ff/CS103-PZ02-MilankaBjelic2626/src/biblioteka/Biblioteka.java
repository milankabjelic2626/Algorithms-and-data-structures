package biblioteka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.in;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Biblioteka extends Knjiga
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //citajIzFajla();
        //spakujKnjigeUListu();
        //upakujPodatkeUObjekat();
        //ispisiKnjige();
        System.out.println(spakujKnjigeUListu());
    }
    /*
    public static void citajIzFajla() throws FileNotFoundException
    {
        String str = "";
        Scanner citaj = new Scanner(new File("knjige.txt")).useDelimiter(",\\s*");
        List<String> temps = new ArrayList<String>();

        while (citaj.hasNext()) 
        {
            str = citaj.next();
            temps.add(str);
        }
        citaj.close();
        
        String[] tempsArray = temps.toArray(new String[0]);

        for (String s : tempsArray) 
        {
            System.out.println(s);
        }
    }
    */
    

public static void ispisiKnjige() throws IOException 
{
    String fileName = "knjige.txt";
    Stream<String> lines = Files.lines(Paths.get(fileName));
    lines.toArray(String[]::new);

    List<String> readAllLines = Files.readAllLines(Paths.get(fileName));
    readAllLines.forEach(s -> System.out.println(s));

    //File file = new File(fileName);
    //Scanner scanner = new Scanner(file);
    //while (scanner.hasNext()) {
      //  System.out.println(scanner.next());
    //}
}    
    
    //List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
//List<String> list = new ArrayList<String>(Arrays.asList(string.split(" , ")));

    public static String spakujKnjigeUListu() 
    {
        FileReader fopen;
        BufferedReader opened;
        String line;

        ArrayList<Knjiga> listaKnjiga = new ArrayList<Knjiga>();
        try {
            fopen = new FileReader("knjige.txt");
            opened = new BufferedReader(fopen);

            int nFields = 5; // we have 5 fields in the Car class
            String[] fields = new String[nFields]; // to temporary store fields values read line by line
            int lineCounter = 0;
            while ((line = opened.readLine()) != null) 
            {
                fields[lineCounter] = line;
                lineCounter++;
                if ((lineCounter) % nFields == 0) 
                { //zato sto imamo po 5 atributa za svaku knjigu
                    listaKnjiga.add(new Knjiga(fields)); //therefore we create a new car and we add it to the list of cars
                }

            }
            //System.out.println(listaKnjiga);
            opened.close();
        } catch (IOException e) 
        {
            System.out.println("Fajl ne postoji!");
        }
        return listaKnjiga.toString();
    }
 /*   
    public static void upakujPodatkeUObjekat() throws FileNotFoundException, IOException
    {
        LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("File1")));
        lnr.skip(Long.MAX_VALUE);

        long lennn = lnr.getLineNumber();

        lnr.close();

        BufferedReader in = new BufferedReader(new FileReader( "knjige.txt" ));

        Knjiga[] niz = new Knjiga[lnr.getLineNumber() / 5];
        String currentLine;

        for(int i = 0; i < (lennn / 5); i += 5) 
        {
            String naslov = in.readLine();
            String autor = in.readLine();
            String izdavac = in.readLine();
            String godinaIzdavanja = in.readLine();
            String barKod = in.readLine();
            niz[i] = new Knjiga(naslov, autor, izdavac, godinaIzdavanja, barKod);
        }
    }  
  */  
}
