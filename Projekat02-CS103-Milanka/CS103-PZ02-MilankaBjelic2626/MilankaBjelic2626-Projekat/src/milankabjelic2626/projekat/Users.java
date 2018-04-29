package milankabjelic2626.projekat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Users 
{
    
    ArrayList<User> users = new ArrayList<User>();

    public Users() 
    {
    
    }
    
    public void addUser(User user)
    {
        users.add(user);
    }
    
    public void addBookToUser(int id, String barCode, Library library)
    {
        findUserByID(id).addBookToUser(library.findByBarCode(barCode));
    }
    
    public void listUserBooks(int id)
    {
        findUserByID(id).listUserBooks();
    }
    
    public User findUserByID(int id)
    {
        ArrayList<User> userFiltered = users.stream()
                .filter(u -> u.getId() == id)
                .collect(Collectors.toCollection(ArrayList::new));

        return userFiltered.get(0);
    }
    
    public void listUsers()
    {
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User next = iterator.next();
            next.printUser();
        }
    }   
}
