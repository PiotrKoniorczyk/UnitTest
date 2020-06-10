package testing;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MealRepositoryTest {

    private MealRepository mealRepository = new MealRepository();

    @Test
    void shouldBeAbleToAddMealToRepository(){
        //give
        Meal meal = new Meal(10,"Pizza");
        //when
        mealRepository.add(meal);

        //then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository(){
        //give
        Meal meal = new Meal(10,"Pizza");
        mealRepository.add(meal);
        //when
        mealRepository.delete(meal);
        //then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));

    }

}
