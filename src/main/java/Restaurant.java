import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen(LocalTime accessedTime) {
        //return true;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        //LocalTime currentHour = LocalTime.now(ZoneId.systemDefault());
        //System.out.println(currentHour);
        if (accessedTime.isAfter(openingTime) && accessedTime.isBefore(closingTime)) {
            return true;
        } else {
            return false;
        }
    }


    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        //return null;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
        return menu;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

    public int calculateTotalOrder(List<String> listOfItems){
        int totalPrice = 0;
        if(listOfItems !=null && !listOfItems.isEmpty()){
        Iterator itemIterator = listOfItems.iterator();
        while (itemIterator.hasNext()){
            String requestedItemName = (String) itemIterator.next();
            Item item = findItemByName(requestedItemName);
            if(item!=null){
                totalPrice = totalPrice + item.getPrice();
            }else{
             System.out.println("Requested Item not found in Menu :"+requestedItemName);
            }
        }

        }else{
         System.out.println("Requested items must not be empty");
        return 0;
        }
        return totalPrice;
    }
}
