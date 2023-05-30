package business;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem{

    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    public void addProduct(MenuItem item){
        menuItems.add(item);
        setCalories(getCalories() + item.getCalories());
        setFats(getFats() + item.getFats());
        setSodium(getSodium() + item.getSodium());
        setProteins(getProteins() + item.getProteins());
        setPrice(getPrice() + item.getPrice());
        setRating( (getRating() + item.getRating()) / 2) ;
    }

    public void removeProduct(MenuItem item){
        menuItems.remove(item);
        setCalories(getCalories() - item.getCalories());
        setFats(getFats() - item.getFats());
        setSodium(getSodium() - item.getSodium());
        setProteins(getProteins() - item.getProteins());
        setPrice(getPrice() - item.getPrice());
        setRating( (getRating() - item.getRating()) / 2) ; //asa ii?
    }

    public ArrayList<MenuItem> getMenuItems(){
        return this.getMenuItems();
    }

    @Override
    public int computePrice() {
        super.computePrice();
        return getPrice();
    }
}
