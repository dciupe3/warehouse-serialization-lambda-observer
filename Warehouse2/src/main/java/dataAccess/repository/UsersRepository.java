package dataAccess.repository;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository implements Repository {
    private ArrayList<User> users;

    public UsersRepository(){
        users = new ArrayList<User>();
    }

    public UsersRepository(ArrayList<User> users){
        this.users = users;
    }

    @Override
    public void add(Object obj) {
        User u = (User) obj;
        System.out.println(u.getUsername());
        users.add(u);
    }

    @Override
    public void remove(Object obj) {
        users.remove((User)obj);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    public void setUsers(ArrayList<User> users){
       this.users = users;
    }


}
