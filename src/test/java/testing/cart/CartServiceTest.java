package testing.cart;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import testing.order.Order;
import testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


class CartServiceTest {

    @Test
    void processCartShouldSendToPrepare(){
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart((cart));

        //then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);


        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()) .sendToPrepare(cart);

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(resultCart.getOrder(), hasSize(1));
        assertThat(resultCart.getOrder().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING) );
    }

    @Test
    void processCartShouldNotSendToPrepare(){
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCart = cartService.processCart((cart));

        //then

        verify(cartHandler,never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(resultCart.getOrder(), hasSize(1));
        assertThat(resultCart.getOrder().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED) );
    }

    @Test
    void processCartShouldNotSendToPrepareWithArgumentMatchers(){
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any())).willReturn(false);


        //when
        Cart resultCart = cartService.processCart((cart));

        //then

        verify(cartHandler,never()).sendToPrepare(any());
        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(resultCart.getOrder(), hasSize(1));
        assertThat(resultCart.getOrder().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED) );
    }

    @Test
    void canHandleCartShouldReturnMultipleCalues(){
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);

        given(cartHandler.canHandleCart(any())).willReturn(true, false, false,true);


        //when

        //then

        assertThat(cartHandler.canHandleCart(cart),equalTo(true));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(true));
    }


    @Test
    void procesCartShouldSendToPrepareWitchLambda(){
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(argThat(c->c.getOrder().size() > 0)))
                .willReturn(true);

        //when
        Cart resultCart = cartService.processCart((cart));

        //then
        then(cartHandler).should().sendToPrepare(cart);

        assertThat(resultCart.getOrder(), hasSize(1));
        assertThat(resultCart.getOrder().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING) );
    }

    @Test
    void canHandleCardShouldThrowException(){
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart))
                .willThrow(IllegalStateException.class);

        //when
        //then

        assertThrows(IllegalStateException.class, ()-> cartService
                .processCart(cart)); 

    }
}