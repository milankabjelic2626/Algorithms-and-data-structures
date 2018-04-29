/*
Interfejs koji cuva info o svakom gradu tj cvoru: njegov naziv, i 
x i y koordinate kako bi se prikazali svi cvorovi. Cvorovi su instance interfejsa.
*/
import java.awt.Color;

public interface CvorInfo 
{
    public int getX();
    public int getY(); 
    public String getIme(); 
}