package testing.cart;

public interface CartHandler {

    boolean canHandleCart(Cart cart);
    void sendToPrepare(Cart cart);

    default boolean isDeliveryFree(Cart cart){
        return cart.getOrder().size() > 2;
    }

}
