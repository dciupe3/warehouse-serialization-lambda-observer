package business;

import dataAccess.repository.UsersRepository;
import model.User;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.CollationElementIterator;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private ArrayList<MenuItem> menuItems;
    private Map<Order, ArrayList<MenuItem>> orders = new LinkedHashMap<>();

    public ArrayList<User> users = new ArrayList<>();

    public File file = new File("file.txt");
    public PrintWriter writer = null;
    int nr;

    public DeliveryService(){
        this.menuItems = new ArrayList<>();

    }

    public void addObservable(){
        for(User e : users){
            addObserver(e);
        }
    }


    public void setOrderToEmployee(Order o){
        setChanged();
        notifyObservers(o);
    }

    public void addMenuItemToMenu(MenuItem item){
        this.menuItems.add(item);
    }

    public void deleteMenuItemFromMenu(MenuItem item){
        menuItems.remove(item);
    }

    public Map<Order, ArrayList<MenuItem>> getOrders(){
        return orders;
    }

    public void setOrders(Map<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    public void addNewOrder(ArrayList<MenuItem>items, User user){
        System.out.println("New Order Created");
        int totalPrice = 0;

        ArrayList<MenuItem> productsInOrder = new ArrayList<>();

        for(MenuItem item : items){
            //se verifica daca sunt composite products?
            for (MenuItem i : menuItems){
                if(item.equals(i)){
                    totalPrice += i.getPrice();
                    //System.out.println(i.getContorOrder());
                    i.incrementContor();
                    productsInOrder.add(i);
                }
            }
        }

        openFile(user.getUsername());

        Order order = new Order(user);
        order.getUser().setNrOrders(order.getUser().getNrOrders() + 1);
        //orders.put(order, items);
        order.setPrice(totalPrice);
        orders.put(order, productsInOrder);

        this.setOrderToEmployee(order);

        writer.println("User with username : " + user.getUsername() + " ordered the following products : ");
        for(MenuItem item : orders.get(order)){
            //totalPrice += item.getPrice();
            writer.println(item.getTitle() + "    - " + item.getPrice() + "$");

            //aici pot sa iau si composed products
        }
        writer.println("The total price of the order is : " + totalPrice + "$");

        writer.close();
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<MenuItem> getMenuItems(){
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems){
        this.menuItems = menuItems;
    }

    public void createMenuFromString(List<List<String>> listOfList){

        for(List<String> line : listOfList){
            BaseProduct baseProduct = new BaseProduct();
            baseProduct.setTitle(line.get(0));
            baseProduct.setRating(Double.parseDouble(line.get(1)));
            baseProduct.setCalories(Integer.parseInt(line.get(2)));
            baseProduct.setProteins(Integer.parseInt(line.get(3)));
            baseProduct.setFats(Integer.parseInt(line.get(4)));
            baseProduct.setSodium(Integer.parseInt(line.get(5)));
            baseProduct.setPrice(Integer.parseInt(line.get(6)));

            menuItems.add(baseProduct);
        }
    }

    public void removeAllProducts(){
        this.menuItems.clear();
    }

    public MenuItem createProductFromString(List<String> list){

        BaseProduct baseProduct = new BaseProduct();
        baseProduct.setTitle(list.get(0));
        baseProduct.setRating(Double.parseDouble(list.get(1)));
        baseProduct.setCalories(Integer.parseInt(list.get(2)));
        baseProduct.setProteins(Integer.parseInt(list.get(3)));
        baseProduct.setFats(Integer.parseInt(list.get(4)));
        baseProduct.setSodium(Integer.parseInt(list.get(5)));
        baseProduct.setPrice(Integer.parseInt(list.get(6)));

        return baseProduct;
    }

    public void modifyProduct(int index, List<String> list){
        if(!list.get(0).equals(""))
            menuItems.get(index).setTitle(list.get(0));
        if(!list.get(1).equals(""))
            menuItems.get(index).setRating(Double.parseDouble(list.get(1)));
        if(!list.get(2).equals(""))
            menuItems.get(index).setCalories(Integer.parseInt(list.get(2)));
        if(!list.get(3).equals(""))
            menuItems.get(index).setProteins(Integer.parseInt(list.get(3)));
        if(!list.get(4).equals(""))
            menuItems.get(index).setFats(Integer.parseInt(list.get(4)));
        if(!list.get(5).equals(""))
            menuItems.get(index).setSodium(Integer.parseInt(list.get(5)));
        if(!list.get(6).equals(""))
            menuItems.get(index).setPrice(Integer.parseInt(list.get(6)));
    }

    public void removeProduct(int index){
        menuItems.remove(index);
    }

    public MenuItem createCompositeProduct(ArrayList<Integer> index, String title){
        CompositeProduct compositeProduct = new CompositeProduct();
        compositeProduct.setTitle(title);
        for(Integer i : index){
            compositeProduct.addProduct(menuItems.get(i));
        }

        return compositeProduct;
    }

    //return the filteredList
    public ArrayList<MenuItem> getFilteredList(String title, ArrayList<Integer> caloriesRange, ArrayList<Integer> proteinRange, ArrayList<Integer> fatRange, ArrayList<Integer> sodiumRange, ArrayList<Double> ratingRange, ArrayList<Integer> priceRange){

        ArrayList<MenuItem> fMenuItems = (ArrayList<MenuItem>) menuItems
                .stream()
                .filter(c -> c.getCalories() >= caloriesRange.get(0) && c.getCalories() <= caloriesRange.get(1))
                .filter(c -> c.getProteins() >= proteinRange.get(0) && c.getProteins() <= proteinRange.get(1))
                .filter(c -> c.getFats() >= fatRange.get(0) && c.getFats() <= fatRange.get(1))
                .filter(c -> c.getSodium() >= sodiumRange.get(0) && c.getSodium() <= sodiumRange.get(1))
                .filter(c -> c.getRating() >= ratingRange.get(0) && c.getRating() <= ratingRange.get(1))
                .filter(c -> c.getPrice() >= priceRange.get(0) && c.getPrice() <= priceRange.get(1))
                .filter(c -> c.getTitle().contains(title))
                .collect(Collectors.toSet());
                //.collect(Collectors.toList());

       return fMenuItems;
    }

    public void openFile(String name){
        try {
            nr++;
            file = new File("file" + name + nr + ".txt");
            System.out.println(file.getName());
            writer = new PrintWriter(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<MenuItem> getSelectedProductsFromList(ArrayList<MenuItem> items, ArrayList<Integer> idx){
        ArrayList<MenuItem> toReturn = new ArrayList<>();

        for(Integer i : idx){
            toReturn.add(items.get(i));
        }

        return toReturn;
    }

    //REPORT

    public ArrayList<MenuItem> getReport2Products(int nr){
        ArrayList<MenuItem> productsList = new ArrayList<>();

        productsList = (ArrayList<MenuItem>) menuItems
                .stream()
                .filter(c -> c.getContorOrder() > nr)
                .collect(Collectors.toList());

        return productsList;
    }

    public List<List<String>> getReport3Data(ArrayList<Integer> nr){
        List<List<String>> listOflists = new ArrayList<>();
        Set<List<String>> set = new LinkedHashSet<>();

        ArrayList<Order> orders_ = (ArrayList<Order>) orders.keySet()
                .stream()
                .filter(o -> o.getPrice() > nr.get(1) && o.getUser().getNrOrders() > nr.get(0))
                .collect(Collectors.toList());

        //lambda??


        for(Order o : orders_){
            List<String> l = new ArrayList<>();
            l.add(o.getUser().getUsername());
            l.add(Integer.toString(o.getUser().getNrOrders()));

            set.add(l);
        }

        for(List<String> l : set){
            listOflists.add(l);
        }
        return listOflists;
    }

    public List<List<String>> getReport1Data(ArrayList<Integer> start, ArrayList<Integer> finish) {
        List<List<String>> listOflists = new ArrayList<>();

        ArrayList<Order> orders_ = (ArrayList<Order>) orders.keySet()
                .stream()
                //.filter(o -> o.getDate().getHours() >= start.get(0) && o.getDate().getMinutes() >= start.get(1) && o.getDate().getHours() <= finish.get(0) && o.getDate().getMinutes() <= finish.get(1))

                .filter(o -> (o.getDate().getHours() > start.get(0) && o.getDate().getHours() < finish.get(0))
                              || ( (o.getDate().getHours() == start.get(0) && o.getDate().getHours() < finish.get(0)) && (o.getDate().getMinutes() >= start.get(1) ))
                        || ( (o.getDate().getHours() > start.get(0) && o.getDate().getHours() == finish.get(0)) && (o.getDate().getMinutes() <= finish.get(1) ))
                        || ( (o.getDate().getHours() == start.get(0) && o.getDate().getHours() == finish.get(0)) && (o.getDate().getMinutes() >= start.get(1) && o.getDate().getMinutes() <= finish.get(1) ))
                )

                //.filter(o -> ( (o.getDate().getHours() == start.get(0) && o.getDate().getHours() < finish.get(0)) && (o.getDate().getMinutes() >= start.get(1) )))
                //.filter(o -> ( (o.getDate().getHours() > start.get(0) && o.getDate().getHours() == finish.get(0)) && (o.getDate().getMinutes() <= finish.get(1) )))
                //.filter(o -> ( (o.getDate().getHours() == start.get(0) && o.getDate().getHours() == finish.get(0)) && (o.getDate().getMinutes() >= start.get(1) && o.getDate().getMinutes() <= finish.get(1) )))

                .collect(Collectors.toList());

        for(Order o : orders_){
            List<String> l = new ArrayList<>();
            l.add(Integer.toString(o.hashCode()));
            String s = new String(o.getDate().getHours() + ":" + o.getDate().getMinutes());
            l.add(s);
            l.add(o.getUser().getUsername());
            l.add(Integer.toString(orders.get(o).size()));
            l.add(Integer.toString(o.getPrice()));

            listOflists.add(l);
        }


        return listOflists;
    }

    public List<List<String>> getReport4Data(String str){
        List<List<String>> listOflists = new ArrayList<>();
        ArrayList<MenuItem> productsList = new ArrayList<>();

        ArrayList<Order> orders_ = (ArrayList<Order>) orders.keySet()
                .stream()
                //.filter(o -> o.getDate().toString().contains(str))
                .filter(o -> {
                    String[] ss = o.getDate().toString().split(" ");
                    String sss = ss[0] + " " + ss[1] + " " + ss[2] + " " + ss[5];
                   // System.out.println(sss);
                    return sss.equals(str);
                })
                .collect(Collectors.toList());

        ArrayList<MenuItem> items = new ArrayList<>();

        for(Order o : orders_){
            System.out.println(o.getDate().toString());
            for(MenuItem  i : orders.get(o)){
                items.add(i);
            }
        }

        ArrayList<MenuItem> items2 = new ArrayList<>(items);

        for(MenuItem i : items){
            //i.setContorOrder(0);
            for(MenuItem i2 : items.subList(items.indexOf(i), items.size())){
                if(i.getTitle().equals(i2.getTitle()) && items.indexOf(i) != items.indexOf(i2)){
                    items2.remove(i2);
                   // items2.get(items2.indexOf(i)).incrementContor();
                }
            }
        }

        items = items2;

            for(MenuItem i : items){
            List<String> l = new ArrayList<>();

            l.add(i.getTitle());
            l.add(Double.toString(i.getRating()));
            l.add(Integer.toString(i.getPrice()));
            l.add(Integer.toString(i.getContorOrder()));

            listOflists.add(l);
        }

        return listOflists;
    }
}
