package knapsack;

import java.util.Random;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter the number of items: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Enter the seed (leave empty for random): ");
        String seedInput = scanner.nextLine();
        int seed = seedInput.isEmpty() ? random.nextInt(Integer.MAX_VALUE) : Integer.parseInt(seedInput);

        Problem problem = new Problem(n, seed, 1, 10);
        System.out.println(problem.toString());

        System.out.println("Enter total capacity: ");
        int capacity = scanner.nextInt();

        Result result = problem.solve(capacity);
        System.out.println(result.toString());
    }
}
