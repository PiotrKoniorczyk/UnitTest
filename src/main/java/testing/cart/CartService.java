package testing.cart;

import testing.order.OrderStatus;

public class CartService {

    private CartHandler cartHandler;

    public CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    Cart processCart(Cart cart){
        if(cartHandler.canHandleCart(cart)){
            cartHandler.sendToPrepare(cart);
            //cartHandler.sendToPrepare(cart);
            cart.getOrder().forEach(order -> {
                order.changeOrderStatus(OrderStatus.PREPARING);
            });
            return cart;
        }else {
            cart.getOrder().forEach(order -> {
                order.changeOrderStatus(OrderStatus.REJECTED);
            });
            return cart;
        }
    }
}
