package testing.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import testing.order.Order;
import testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

//@MockitoSettings(strictness = Strictness.STRICT_STUBS)
//@ExtendWith(MockitoExtension.class)
class CartServiceTest {

//    @InjectMocks
//    private CartService cartService;
//    @Mock
//    private CartHandler cartHandler;
//    @Captor
//    private ArgumentCaptor<Cart> argumentCaptor;

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
    void processCartShouldSendToPrepareWitchLambda(){
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

    @Test
    void processCartShouldSendToPrepareWitchArgumentCapture(){
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart((cart));

        //then

        //verify(cartHandler).sendToPrepare(argumentCaptor.capture());
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture());

        assertThat(argumentCaptor.getAllValues().get(0).getOrder().size(), equalTo(1));

        assertThat(resultCart.getOrder(), hasSize(1));
        assertThat(resultCart.getOrder().get(0).getOrderStatus(),
                equalTo(OrderStatus.PREPARING) );
    }

    @Test
    void shouldDoNothingWhenProcessCart() {
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

//        doNothing().when(cartHandler).sendToPrepare(cart);
        willDoNothing().given(cartHandler).sendToPrepare(cart);
        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart);


        //when
        Cart resultCart = cartService.processCart((cart));

        //then
        then(cartHandler).should().sendToPrepare(cart);

        assertThat(resultCart.getOrder(), hasSize(1));
        assertThat(resultCart.getOrder().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void shouldAnswerWhenProcessCart(){
        //give
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        doAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            return true;
        }).when(cartHandler).canHandleCart(cart);

        when(cartHandler.canHandleCart(cart)).then(i ->{
            Cart argumentCart = i.getArgument(0);
            argumentCart.clearCart();
            return true;
        });

        willAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            return true;
        }).given(cartHandler).canHandleCart(cart);

        willAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            return true;
        }).given(cartHandler).canHandleCart(cart);

        //when
        Cart resultCart = cartService.processCart((cart));

        //then
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrder().size(), equalTo(0));
    }

    @Test
    void deliveryShouldBeFree(){
        //given
        Cart cart = new Cart();
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());

        CartHandler cartHandler = mock(CartHandler.class);
        doCallRealMethod().when(cartHandler).isDeliveryFree(cart);
        //given(cartHandler.isDeliveryFree(cart)).willCallRealMethod();
        //when
        boolean isDeliveryFree = cartHandler.isDeliveryFree(cart);

        //then
        assertTrue(isDeliveryFree);

    }

}