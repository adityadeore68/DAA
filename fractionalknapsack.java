#Name : Aditya Deore
#PRN : 123B1F019
    
import java.util.*;

class Item {
    double value, weight;
    boolean divisible;

    Item(double v, double w, boolean d) {
        this.value = v;
        this.weight = w;
        this.divisible = d;
    }
}

public class ReliefBoatKnapsack {

    public static double getMaxValue(List<Item> items, double capacity) {
        items.sort((a, b) -> Double.compare(b.value / b.weight, a.value / a.weight));

        double totalValue = 0.0;
        double currentWeight = 0.0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                if (item.divisible) {
                    double remaining = capacity - currentWeight;
                    totalValue += (item.value / item.weight) * remaining;
                    currentWeight += remaining;
                }
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total weight capacity of the boat (kg): ");
        double W = sc.nextDouble();

        System.out.print("Enter number of relief items: ");
        int n = sc.nextInt();

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for item " + (i + 1) + ":");
            System.out.print("Utility value: ");
            double value = sc.nextDouble();

            System.out.print("Weight (kg): ");
            double weight = sc.nextDouble();

            System.out.print("Is the item divisible? (1 = Yes, 0 = No): ");
            int div = sc.nextInt();

            items.add(new Item(value, weight, div == 1));
        }

        double maxValue = getMaxValue(items, W);
        System.out.printf("\nMaximum total utility value that can be carried: %.2f\n", maxValue);

        sc.close();
    }
}

