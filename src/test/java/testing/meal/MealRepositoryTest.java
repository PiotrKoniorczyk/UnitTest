package testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MealRepositoryTest {

    private final MealRepository mealRepository = new MealRepository();

    @BeforeEach
    void cleanUp() {
        mealRepository.getAllMeals().clear();
    }

    @Test
    void shouldBeAbleToAddMealToRepository() {
        //give
        Meal meal = new Meal(10, "Pizza");
        //when
        mealRepository.add(meal);

        //then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository() {
        //give
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);
        //when
        mealRepository.delete(meal);
        //then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));

    }

    @Test
    void shouldBeAbleFindMealByExactName() {
        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pi");
        mealRepository.add(meal1);
        mealRepository.add(meal2);
        //when
        List<Meal> result = mealRepository.findByNames("Pizza", true);
        //then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getName(), equalTo("Pizza"));
    }

    @Test
    void shouldBeAbleToFindMealByStartingLetters() {
        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pi");
        mealRepository.add(meal1);
        mealRepository.add(meal2);
        //when
        List<Meal> result = mealRepository.findByNames("P", false);
        //then
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getName(), equalTo("Pizza"));

    }

    @Test
    void shouldBeAbleFindMealByPrice() {
        //given
        Meal meal1 = new Meal(5, "Pizza");
        Meal meal2 = new Meal(10, "Kebab");
        Meal meal3 = new Meal(15, "Burger");
        mealRepository.add(meal1);
        mealRepository.add(meal2);
        mealRepository.add(meal3);
        //when
        List<Meal> result1 = mealRepository.findByPrice(10, PriceSerchingType.LOWER);
        List<Meal> result2 = mealRepository.findByPrice(10, PriceSerchingType.EQUAL);
        List<Meal> result3 = mealRepository.findByPrice(10, PriceSerchingType.HIGHER);
        //then
        assertThat(result1.size(), is(1));
        assertThat(result2.size(), is(1));
        assertThat(result3.size(), is(1));

        assertThat(result1.get(0).getName(), equalTo("Pizza"));
        assertThat(result2.get(0).getName(), equalTo("Kebab"));
        assertThat(result3.get(0).getName(), equalTo("Burger"));
    }

    @Test
    void shouldBeAbleFindMealByNameAndPrice() {
        //given
        Meal meal1 = new Meal(5, "Pizza");
        Meal meal2 = new Meal(10, "Kebab");
        Meal meal3 = new Meal(15, "Burger");
        Meal meal4 = new Meal(15, "Burrito");
        mealRepository.add(meal1);
        mealRepository.add(meal2);
        mealRepository.add(meal3);
        mealRepository.add(meal4);
        //when
        List<Meal> result1 = mealRepository.findByNamesAndPrice("B",false,10, PriceSerchingType.HIGHER);
        List<Meal> result2 = mealRepository.findByNamesAndPrice("Keb",false,10, PriceSerchingType.EQUAL);
        List<Meal> result3 = mealRepository.findByNamesAndPrice("Pizza",true,10, PriceSerchingType.HIGHER);
        List<Meal> result4 = mealRepository.findByNamesAndPrice("Pizza",true,10, PriceSerchingType.LOWER);
        List<Meal> result5 = mealRepository.findByNamesAndPrice("",false,16, PriceSerchingType.LOWER);
        List<Meal> result6 = mealRepository.findByNamesAndPrice("",false,16, PriceSerchingType.HIGHER);


        //then
        assertThat(result1.size(), is(2));
        assertThat(result1.get(0).getName(), equalTo("Burger"));
        assertThat(result1.get(1).getName(), equalTo("Burrito"));
        assertThat(result2.size(), is(1));
        assertThat(result2.get(0).getName(), equalTo("Kebab"));
        assertThat(result3.size(), is(not(1)));
        assertThat(result4.size(), is(1));
        assertThat(result4.get(0).getName(), equalTo("Pizza"));
        assertThat(result5.size(), is(4));
        assertThat(result6.size(), is(0));


    }


}
