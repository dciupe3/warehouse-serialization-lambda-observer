package dataAccess;

import business.DeliveryService;
import business.MenuItem;
import business.Order;
import dataAccess.repository.UsersRepository;
import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class Serializator {

    private String fileName = new String("users.txt");
    UsersRepository usersRepository;
    private DeliveryService deliveryService;

    private String filenameMenuItems = new String("menu.txt");
    private String fileNameOrders = new String("orders.txt");

    public Serializator(){
        usersRepository = new UsersRepository();
        deliveryService = new DeliveryService();
    }

    public Serializator(UsersRepository usersRepository, DeliveryService deliveryService){
        this.usersRepository = usersRepository;
        this.deliveryService = deliveryService;
    }

    public void doUsersSerialization(){
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(usersRepository.getAll());
            out.close();
            file.close();
            System.out.println("Useri serializati");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doUserDeserialization(){

        try {

            //Path path = Files.createTempFile("users", ".txt");

            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);


            usersRepository.setUsers((ArrayList<User>) in.readObject());

            in.close();
            file.close();
            System.out.println("Useri deserializati");

        } catch (IOException e) {
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (User u : usersRepository.getAll()) {
            System.out.println(u.getId() + " " + u.getTypeUser() + " " + u.getUsername() + " " + u.getPassword());
        }
    }

    public void doMenuSerialization(){
        try {
            FileOutputStream file = new FileOutputStream(filenameMenuItems);
            ObjectOutputStream out = new ObjectOutputStream(file);


            out.writeObject(deliveryService.getMenuItems());
            out.close();
            file.close();
            System.out.println("Meniu serializat");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doMenuDeserialization(){

        File f = new File(filenameMenuItems);
        if(!f.exists()){
            System.out.println("Fisierul nu exista");
            deliveryService.setMenuItems(new ArrayList<>());
            return;
        }

        try {

            //Path path = Files.createTempFile("menu", ".txt");

            FileInputStream file = new FileInputStream(filenameMenuItems);
            ObjectInputStream in = new ObjectInputStream(file);

            deliveryService.setMenuItems((ArrayList<MenuItem>) in.readObject());

            in.close();
            file.close();
            System.out.println("Meniu deserializat");

        } catch (IOException e) {
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doOrdersSerialization(){
        try {
            FileOutputStream file = new FileOutputStream(fileNameOrders);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(deliveryService.getOrders());
            out.close();
            file.close();
            System.out.println("Order serializat");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doOrdersDeserialization(){
        File f = new File(fileNameOrders);
        if(!f.exists()){
            System.out.println("Fisierul order nu exista");
            return;
        }

        try {

            FileInputStream file = new FileInputStream(fileNameOrders);
            ObjectInputStream in = new ObjectInputStream(file);

            deliveryService.setOrders((Map<Order, ArrayList<MenuItem>>) in.readObject());

            in.close();
            file.close();
            System.out.println("Order deserializat");

        } catch (IOException e) {
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }
}
