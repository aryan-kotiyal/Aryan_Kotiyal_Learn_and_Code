import java.util.Scanner;

public class FloorMeanSubarray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements and number of queries: ");
        int arraySize = scanner.nextInt();
        int queryCount = scanner.nextInt();

        int[] numbers = new int[arraySize];
        System.out.println("Enter array elements: ");
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = scanner.nextInt();
        }

        SubarrayMeanCalculator calculator = new SubarrayMeanCalculator(numbers);

        System.out.println("Enter queries: ");
        for (int i = 0; i < queryCount; i++) {
            int leftIndex = scanner.nextInt();
            int rightIndex = scanner.nextInt();
            System.out.println(calculator.getFloorMean(leftIndex, rightIndex));
        }
        scanner.close();
    }
}

