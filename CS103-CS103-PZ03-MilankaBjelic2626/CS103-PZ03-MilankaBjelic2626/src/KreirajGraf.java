import java.util.List;
/*
Prosiruje klasu ApstraktniGraf sa konstruktorima za kreiranje grafa tj instance tipa Graf.
Ova klasa nasledjuje sve metode aps klase ApstraktniGraf
*/
public class KreirajGraf<V> extends ApstraktniGraf<V> 
{
    public KreirajGraf() 
    {
    }

    public KreirajGraf(int[][] grane, V[] cvorovi) 
    {
        super(grane, cvorovi);
    }

    public KreirajGraf(List<ApstraktniGraf.Grana> grane, List<V> cvorovi) 
    {
        super(grane, cvorovi);
    }

    public KreirajGraf(List<ApstraktniGraf.Grana> grane, int brojCvorova) 
    {
        super(grane, brojCvorova);
    }

    public KreirajGraf(int[][] grane, int brojCvorova) 
    {
        super(grane, brojCvorova);
    }
}

