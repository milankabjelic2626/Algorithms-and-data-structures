import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ApstraktniGraf<V> implements Graf<V> 
{
    protected List<V> cvorovi = new ArrayList<V>(); 
    protected List<List<Integer>> susedi = new ArrayList<List<Integer>>(); 
	
    protected ApstraktniGraf() 
    {
    }

    protected ApstraktniGraf(int[][] grane, V[] cvorovi) 
    {
	for (int i = 0; i < cvorovi.length; i++)
            this.cvorovi.add(cvorovi[i]);

	kreirajListuSusedstva(grane, cvorovi.length);
    }

		
    protected ApstraktniGraf(List<Grana> grane, List<V> cvorovi) 
    {
	for (int i = 0; i < cvorovi.size(); i++)
            this.cvorovi.add(cvorovi.get(i));

	ApstraktniGraf.this.kreirajListuSusedstva(grane, cvorovi.size());
    }
		
    protected ApstraktniGraf(List<Grana> grane, int brojCvorova) 
    {
	for (int i = 0; i < brojCvorova; i++)
            cvorovi.add((V) (new Integer(i)));

	ApstraktniGraf.this.kreirajListuSusedstva(grane, brojCvorova);
    }

    protected ApstraktniGraf(int[][] grane, int brojCvorova) 
    {
	for (int i = 0; i < brojCvorova; i++)
            cvorovi.add((V) (new Integer(i))); 
	kreirajListuSusedstva(grane, brojCvorova);
    }
	
    private void kreirajListuSusedstva(int[][] grane, int brojCvorova) 
    {			
	for (int i = 0; i < brojCvorova; i++) 
        {
            susedi.add(new ArrayList<Integer>());
	}

	for (int i = 0; i < grane.length; i++) 
        {
            int u = grane[i][0];
            int v = grane[i][1];
            susedi.get(u).add(v);
	}
    }

    private void kreirajListuSusedstva(List<Grana> grane, int brojCvorova) 
    {			
        for (int i = 0; i < brojCvorova; i++) 
        {
            susedi.add(new ArrayList<Integer>());
	}

	for (Grana edge : grane) 
        {
            susedi.get(edge.u).add(edge.v);			
        }
    }

    @Override		
    public int getSize() 
    {
	return cvorovi.size();
    }

    @Override		
    public List<V> getCvorovi() 
    {
	return cvorovi;
    }

    @Override		
    public V getCvor(int index) 
    {
	return cvorovi.get(index);
    }

    @Override		
    public int getIndeks(V v) 
    {
	return cvorovi.indexOf(v);
    }

    @Override		
    public List<Integer> getSusedi(int index) 
    {
	return susedi.get(index);
    }

    @Override		
    public int getStepenCvora(int v) 
    {
	return susedi.get(v).size();
    }
             
    @Override		
    public void clear() 
    {
	cvorovi.clear();
	susedi.clear();
    }

    @Override		
    public void dodajCvor(V cvor) 
    {
	cvorovi.add(cvor);
	susedi.add(new ArrayList<Integer>());
    }

    @Override		
    public void dodajGranu(int u, int v) 
    {
	susedi.get(u).add(v);
	susedi.get(v).add(u);
    }

    public static class Grana 
    {
	public int u; 
	public int v;

	public Grana(int u, int v) 
        {
            this.u = u;
            this.v = v;
	}
    }

    @Override		
    public Stablo dfs(int v) 
    {
	List<Integer> pretrazeni = new ArrayList<Integer>();
	int[] roditelj = new int[cvorovi.size()];
        
	for (int i = 0; i < roditelj.length; i++)
		roditelj[i] = -1; 

	boolean[] isPretrazen = new boolean[cvorovi.size()];

	dfs(v, roditelj, pretrazeni, isPretrazen);

	return new Stablo(v, roditelj, pretrazeni);
    }

	//pomocni metod dfs	
    private void dfs(int v, int[] roditelj, List<Integer> pretrazeni, boolean[] isPosecen) 
    {
	pretrazeni.add(v);
	isPosecen[v] = true; 

	for (int i : susedi.get(v)) 
        {
            if (!isPosecen[i]) 
            {
		roditelj[i] = v; 
		dfs(i, roditelj, pretrazeni, isPosecen);
            }
	}
    }

    @Override		
    public Stablo bfs(int v) 
    {
	List<Integer> pretrazeni = new ArrayList<Integer>();
	int[] roditelj = new int[cvorovi.size()];
	for (int i = 0; i < roditelj.length; i++)
		roditelj[i] = -1; 

	LinkedList<Integer> red = new LinkedList<Integer>(); 
																			// queue
	boolean[] isPosecen = new boolean[cvorovi.size()];
	red.offer(v); 
	isPosecen[v] = true; 

	while (!red.isEmpty()) 
        {
            int u = red.poll(); 
            pretrazeni.add(u); 
            for (int w : susedi.get(u)) 
            {
		if (!isPosecen[w]) 
                {
                    red.offer(w); 
                    roditelj[w] = u; 
                    isPosecen[w] = true;
		}
            }
	}
	return new Stablo(v, roditelj, pretrazeni);
    }

public class Stablo 
{
    public int koren; 
    public int[] roditelj; 
    public List<Integer> pretrazeni;			 

    public Stablo(int koren, int[] roditelj, List<Integer> pretrazeni) 
    {
	this.koren = koren;
	this.roditelj = roditelj;
	this.pretrazeni = pretrazeni;
    }
			
    public int getKoren() 
    {
	return koren;
    }

    public int getRoditelj(int v) 
    {
	return roditelj[v];
    }

    public List<Integer> getPretrazeni() 
    {
	return pretrazeni;
    }

    public int getBrojPronadjenihCvorova() 
    {
	return pretrazeni.size();
    }
    
}
}


