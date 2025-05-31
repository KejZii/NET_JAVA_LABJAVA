package knapsack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Problem {

    public static class Item {
        public int value;
        public int weight;
        public int id;

        public Item(int id, int value, int weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
        }

        public float getRatio() {
            return (float) value / weight;
        }
    }

    private final List<Item> itemsList = new ArrayList<>();

    public Problem(int n, int seed, int lowerBound, int upperBound) {
        Random random = new Random(seed);
        for (int i = 0; i < n; i++) {
            int value = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int weight = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            itemsList.add(new Item(i, value, weight));
        }
    }

    public List<Item> getItemsList() {
        return new ArrayList<>(itemsList); // Zwraca kopię listy
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (Item item : itemsList) {
            returnString.append(String.format("No: %d v: %d w: %d%n", item.id, item.value, item.weight));
        }
        return returnString.toString();
    }

    public Result solve(int capacity) {
        Result result = new Result();
        List<Item> sortedItems = new ArrayList<>(itemsList);
        sortedItems.sort(Comparator.comparingDouble(Item::getRatio).reversed());

        for (Item item : sortedItems) {
            while (item.weight <= capacity) {
                result.itemsInBackpack.add(item);
                result.totalValue += item.value;
                result.totalWeight += item.weight;
                capacity -= item.weight;
            }
        }

        return result;
    }

}
