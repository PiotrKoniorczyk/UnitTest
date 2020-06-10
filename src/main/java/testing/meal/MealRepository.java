package testing.meal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MealRepository {

    List<Meal> meals = new ArrayList<>();


    public void add(Meal meal) {
        meals.add(meal);
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public void delete(Meal meal) {
        meals.remove(meal);

    }

    public List<Meal> findByNames(String mealName, boolean exactMatch) {

        List<Meal> result;
        if (exactMatch) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .collect(Collectors.toList());

        } else {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .collect(Collectors.toList());

        }
        return result;
    }

    public List<Meal> findByPrice(int price, PriceSerchingType SerchType) {

        List<Meal> result;

        switch (SerchType) {
            case LOWER:
                result = meals.stream().filter(meal -> meal.getPrice() < price)
                        .collect(Collectors.toList());
                break;
            case EQUAL:
                result = meals.stream().filter(meal -> meal.getPrice() == price)
                        .collect(Collectors.toList());
                break;
            case HIGHER:
                result = meals.stream().filter(meal -> meal.getPrice() > price)
                        .collect(Collectors.toList());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + SerchType);
        }
        return result;
    }

    public List<Meal> findByNamesAndPrice(String mealName, boolean exactMatch, int price, PriceSerchingType SerchType) {

        List<Meal> firstFilter;
        List<Meal> result = Arrays.asList();


        switch (SerchType) {
            case LOWER:
                firstFilter = meals.stream().filter(meal -> meal.getPrice() < price)
                        .collect(Collectors.toList());
                break;
            case EQUAL:
                firstFilter = meals.stream().filter(meal -> meal.getPrice() == price)
                        .collect(Collectors.toList());
                break;
            case HIGHER:
                firstFilter = meals.stream().filter(meal -> meal.getPrice() > price)
                        .collect(Collectors.toList());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + SerchType);
        }

        for (int i = 0; i < firstFilter.size(); i++) {
            if(exactMatch) {
                result = firstFilter.stream().filter(filter -> filter
                        .getName().equals(mealName))
                        .collect(Collectors.toList());
            }else
                result = firstFilter.stream().filter(firstFilter2 -> firstFilter2
                        .getName().startsWith(mealName))
                        .collect(Collectors.toList());
        }
        return result;
    }
}
