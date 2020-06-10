package testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MealRepositoryTest {

    private MealRepository mealRepository = new MealRepository();

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
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);
        //when
        List<Meal> result = mealRepository.findByPrice(10);
        //then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getPrice(), is(10));


    }

}
