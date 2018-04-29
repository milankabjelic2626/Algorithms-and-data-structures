package biblioteka;

/**
 *
 * @author milankabjelic
 */
public class Knjiga 
{
    private String naslov, autor, izdavac, godinaIzdavanja, barKod;

    public Knjiga() 
    {
    }

    public Knjiga(String naslov, String autor, String izdavac, String godinaIzdavanja, String barKod) 
    {
        this.naslov = naslov;
        this.autor = autor;
        this.izdavac = izdavac;
        this.godinaIzdavanja = godinaIzdavanja;
        this.barKod = barKod;
    }
    
    public Knjiga(String[] polja)
    {
        naslov = polja[0];
        autor = polja[0];
        izdavac = polja[0];
        godinaIzdavanja = polja[0];
        barKod = polja[0];
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

    public String getGodinaIzdavanja() {
        return godinaIzdavanja;
    }

    public void setGodinaIzdavanja(String godinaIzdavanja) {
        this.godinaIzdavanja = godinaIzdavanja;
    }

    public String getBarKod() {
        return barKod;
    }

    public void setBarKod(String barKod) {
        this.barKod = barKod;
    }
    
    
}

