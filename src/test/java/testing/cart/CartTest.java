package testing.cart;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testing.meal.Meal;
import testing.order.Order;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test cases for Cart")
class CartTest {

    @Test
    @Disabled
    @DisplayName("Card is able to process 1000 orders in 100ms")
    void simulateLargeOrder() {

        //given
        Cart card = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(100), card::simulateLargeOrder);
    }

    @Test
    void cardShouldBeNotEmptyAfterAddingOrderToCart() {

        //given
        Order order = new Order();
        Cart card = new Cart();

        //when
        card.addOrderToCart(order);

        //then
        assertThat(card.getOrder(), anyOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));
        assertThat(card.getOrder(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertAll("This is a group of assertions for cart",
                () -> assertThat(card.getOrder(), notNullValue()),
                () -> assertThat(card.getOrder(), hasSize(1)),
                () -> assertThat(card.getOrder(), is(not(empty()))),
                () -> assertThat(card.getOrder(), is(not(emptyCollectionOf(Order.class)))),
                () -> {
                    List<Meal> mealList = card.getOrder().get(0).getMeals();
                    assertThat(mealList, empty());
                }


        );
    }
}