package testing;

import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice  = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);
//        assertThat(discountedPrice, equalTo(28));                 //hamcreast

        assertThat(discountedPrice).isEqualTo(28);                  //assertj
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        //then
        assertSame(meal1,meal2);
//        assertThat(meal1, sameInstance(meal2));                    //hamcreast

        assertThat(meal1).isSameAs(meal2);                           //assertj
    }

    @Test
    void referencesToDifferentObjectShouldNotBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);
        //then
        assertNotSame(meal1,meal2);
//        assertThat(meal1, not(sameInstance(meal2)));                    //hamcreast

        assertThat(meal1).isNotSameAs(meal2);                             //assertj
    }

    @Test
    void toMealsShouldBeEqualWhenPriceAndNameAreTheSame(){
        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");

        //then
        assertEquals(meal1,meal2, "Checking if two meals are equal");
//        assertThat(meal1, equalTo(meal2));                              //hamcreast

        assertThat(meal1).isEqualTo(meal2);                               //assertj
    }
}