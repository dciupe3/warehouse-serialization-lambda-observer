package business;

import model.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private Date date;
    private User user;
    private int id;
    private int price;


    public Order(User user){
        this.user = user;
        this.date = new Date();
        this.id = Objects.hash(user, date);
    }

    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        Order order = (Order) o;
        return id == order.id && date.equals(order.date) && user.equals(order.user);
    }

    public int hashCode(){
        return this.id;
    }

    public User getUser(){
        return user;
    }

    public void setPrice(int p) {
        this.price = p;
    }

    public int getPrice(){
        return price;
    }

    public Date getDate() {
        return date;
    }
}
