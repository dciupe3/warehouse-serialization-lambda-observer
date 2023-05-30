package model;

import business.Order;

import java.awt.event.WindowAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class User implements Serializable, Observer {
    private String username;
    private String password;
    private static int id_static;
    private int id;
    private String typeUser;
    private int nrOrders;

    public ArrayList<Order> orders = new ArrayList<>();

    public User(){
        id++;
        typeUser = "Admin";

    }

    public User(String username, String password, String tipUser){
        this.username = username;
        this.password = password;
        this.typeUser = tipUser;
        this.id = id_static++;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTypeUser(String typeUser){
        this.typeUser = typeUser;
    }

    public int getId(){
        return id;
    }

    public String getTypeUser(){
        return typeUser;
    }

    public void setNrOrders(int n){
        this.nrOrders = n;
    }

    public int getNrOrders(){
        return nrOrders;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setId_static(int nr){
        id_static = nr;
    }

    @Override
    public void update(Observable o, Object arg) {

        if(typeUser.equals("employee")) {
            System.out.println("!!!!order adaugat " + username + " " + ((Order)arg).getUser().username + " " + ((Order)arg).getPrice());
            orders.add((Order) arg);
        }
    }
}
