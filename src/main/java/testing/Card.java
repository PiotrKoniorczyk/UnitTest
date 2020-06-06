package testing;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private List<Order> order = new ArrayList<>();

    void addOrderToCart(Order order){
        this.order.add(order);
    }

    public List<Order> getOrder() {
        return order;
    }

    void clearCart(){
        this.order.clear();
    }

    void simulateLargeOrder(){
        for (int i = 0; i < 1000; i++) {
            Meal meal = new Meal(i%10, "Hamburger" + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size: "+order.size());
        clearCart();
    }

}
