package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int proteins;
    private int fats;
    private int sodium;
    private int price;
    private int contorOrder;

    public int computePrice(){
        return price;
    }

    public void incrementContor(){
        this.contorOrder++;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setContorOrder(int count){
        this.contorOrder = count;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getPrice() {
        return price;
    }

    public int getSodium(){
        return sodium;
    }

    public int getContorOrder() {
        return contorOrder;
    }
}
