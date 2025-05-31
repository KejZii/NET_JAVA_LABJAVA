package knapsack;

import java.util.ArrayList;
import java.util.List;

public class Result {
    public List<Problem.Item> itemsInBackpack = new ArrayList<>();
    public int totalValue;
    public int totalWeight;
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("---\n");
        for(Problem.Item item : itemsInBackpack)
        {
            if (item != null) {
                returnString.append(String.format("No: %d v: %d w: %d%n",
                        item.id, item.value, item.weight));
            }
        }
        returnString.append(String.format("Weight: %d%nValue: %d", totalWeight, totalValue));
        return returnString.toString();
    }
}
