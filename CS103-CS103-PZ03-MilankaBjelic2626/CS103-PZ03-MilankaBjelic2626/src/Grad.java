public class Grad implements CvorInfo 
{		
    public int x, y;
    public String ime;

    Grad(String ime, int x, int y)
    {
	this.ime = ime;
	this.x = x;
	this.y = y;
    }

    @Override
    public int getX() 
    {
	return x;
    }

    @Override
    public int getY() 
    {
        return y;
    }

    @Override
    public String getIme() 
    {
        return ime;
    }
    
    @Override
    public String toString() 
    {
        return ime;
    }
}
