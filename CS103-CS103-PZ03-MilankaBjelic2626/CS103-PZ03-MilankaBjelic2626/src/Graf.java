
import java.util.List;

/*
Interfejs koji sadzri spisak funkcija za manipulisanje grafom
*/
public interface Graf<V> 
{
    public int getSize();

    public List<V> getCvorovi();

    public V getCvor(int index);

    public int getIndeks(V v);

    public List<Integer> getSusedi(int index);

    public int getStepenCvora(int v);

    public void clear();

    public void dodajCvor(V vertex);

    public void dodajGranu(int u, int v);

    public ApstraktniGraf<V>.Stablo dfs(int v);

    public ApstraktniGraf<V>.Stablo bfs(int v);
}
