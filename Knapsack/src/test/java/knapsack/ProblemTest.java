package knapsack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProblemTest {

    @Test
    void whenAtLeastOneItemFits_thenSolutionContainsItems() {
        Problem problem = new Problem(5, 123, 1, 10);
        Result result = problem.solve(10);

        assertAll(
                () -> assertNotEquals(0, result.itemsInBackpack.size(),
                        "Rozwiązanie powinno zawierać przedmioty, gdy przynajmniej jeden się mieści"),
                () -> assertFalse(result.itemsInBackpack.isEmpty(),
                        "Lista przedmiotów nie powinna być pusta"),
                () -> assertTrue(result.totalWeight > 0 && result.totalWeight <= 10,
                        "Całkowita waga powinna być dodatnia i nie przekraczać pojemności")
        );

    }

    @Test
    void whenNoItemFits_thenSolutionIsEmpty() {
        Problem problem = new Problem(3, 456, 20, 30);
        Result result = problem.solve(10);

        assertTrue(result.itemsInBackpack.isEmpty(),
                "Solution should be empty when no items fit");
        assertEquals(0, result.totalWeight,
                "Total weight should be 0");
        assertEquals(0, result.totalValue,
                "Total value should be 0");
    }

    @Test
    void itemValuesAndWeightsAreWithinBounds() {
        int lowerBound = 1;
        int upperBound = 10;
        Problem problem = new Problem(10, 789, lowerBound, upperBound);

        for (Problem.Item item : problem.getItemsList()) {
            assertTrue(item.value >= lowerBound && item.value <= upperBound,
                    "Item value should be within bounds");
            assertTrue(item.weight >= lowerBound && item.weight <= upperBound,
                    "Item weight should be within bounds");
        }
    }

    @Test
    void solutionIsCorrectForSpecificInstance() {
        Problem problem = new Problem(5, 42, 1, 10);
        Result result = problem.solve(20);

        assertAll("Sprawdzenie wartości i wagi rozwiązania",
                () -> assertNotEquals(0, result.totalValue, "Wartość powinna być dodatnia"),
                () -> assertTrue(result.totalValue > 0, "Wartość powinna być dodatnia"),
                () -> assertFalse(result.totalWeight > 20, "Waga nie powinna przekraczać pojemności"),
                () -> assertTrue(result.totalWeight <= 20, "Waga nie powinna przekraczać pojemności")
        );

        double lastRatio = Double.MAX_VALUE;
        for (Problem.Item item : result.itemsInBackpack) {
            double currentRatio = item.getRatio();
            assertTrue(currentRatio <= lastRatio,
                    "Items should be sorted by ratio descending");
            lastRatio = currentRatio;
        }
    }
}