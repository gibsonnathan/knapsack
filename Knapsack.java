/*******************************************************************************
*
* Nathan Gibson
* Greedy Knapsack, fractional and 0/1  
*
*******************************************************************************/
public class Knapsack{

    int numberOfItems;
    double[] values;
    double[] weights;
    double[] take;

    public Knapsack(int numberOfItems, double[] values, double[] weights){
        this.numberOfItems = numberOfItems;
        this.values = values;
        this.weights = weights;
        this.take = new double[numberOfItems];
    }

    /*
        Orders the knapsack by value to weight ratio
    */
    public void knapsackOrder(){
        for (int i = 0; i < numberOfItems; i++) {
            for (int j = 1; j < (numberOfItems - i); j++) {
                double x=values[j - 1] / weights[j - 1];
                double y=values[j] / weights[j];
                if (x <=y) {
                    double temp = values[j - 1];
                    values[j - 1] = values[j];
                    values[j] = temp;

                    double temp1 = weights[j - 1];
                    weights[j - 1] = weights[j];
                    weights[j] = temp1;
                }
            }
        }
    }

    /*
        Prints the statistics of the knapsack
    */
    public void printKnapsack(){
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "item", "weight", "value", "v/w", "take");
        for (int i = 0; i < numberOfItems; i++) {
            System.out.printf("%-10d %-10.2f %-10.2f %-10.2f %-10.2f\n", i, + weights[i], values[i], (values[i] / weights[i]), take[i]);
        }
    
    }

    /*
        Discrete version of the solution that only takes an
        item if it can fit the full item
    */
    public void greedyKnapsackD(int m){
        knapsackOrder();
        int j;
        for (j = 0; j < numberOfItems; j++) {
            take[j] = 0;
        }
        double total = m;
        for (j = 0; j < numberOfItems; j++) {
            if (weights[j] <= total) {
                take[j] = 1.00;
                total = total - weights[j];
            }
        }
    }

    /*
        Fractional version of the solution that takes a part
        of the item if it cannot fit the entire item
    */
    public void greedyKnapsackC(int m){
        knapsackOrder();
        int j;
        for (j = 0; j < numberOfItems; j++) {
            take[j] = 0;
        }
        double total = m;
        for (j = 0; j < numberOfItems; j++) {
            if (weights[j] <= total) {
                take[j] = 1.00;
                total = total - weights[j];
            } else {
                break;
            }
        }
        if (j < numberOfItems) {
            take[j] = (double)(total / weights[j]);
        } 
    }

    /*
        Tester method 
    */
    public static void main(String[]args){

        Knapsack k = new Knapsack(4, new double[]{1, 2, 3, 4}, new double[]{5,6,7,8});
        k.greedyKnapsackD(6);
        k.printKnapsack();
        k.greedyKnapsackC(6);
        k.printKnapsack();
    }
}