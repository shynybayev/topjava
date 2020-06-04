package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * -  должны возвращаться только записи между `startTime` и `endTime`
 * -  поле `UserMealWithExcess.excess` должно показывать,
 * превышает ли сумма калорий за весь день значение `calories`
 * <p>
 * Т.е `UserMealWithExcess` - это запись одной еды, но поле `excess` будет одинаково для всех записей за этот день.
 * <p>
 * - Проверьте результат выполнения ДЗ (можно проверить логику в http://topjava.herokuapp.com , список еды)
 * - Оцените Time complexity алгоритма. Если она больше O(N), например O(N*N) или N*log(N), сделайте O(N).
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> userMeals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JUNE, 4, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JUNE, 4, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JUNE, 4, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JUNE, 4, 22, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JUNE, 5, 13, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JUNE, 5, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JUNE, 5, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(userMeals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> listOfUserMeal = new ArrayList<>();
        for (UserMeal userMeal : meals) {
            if (TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                UserMealWithExcess filteredUserMeal =
                        new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), userMeal.getCalories() < caloriesPerDay);
                listOfUserMeal.add(filteredUserMeal);
            }
        }
        return listOfUserMeal;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }

}
