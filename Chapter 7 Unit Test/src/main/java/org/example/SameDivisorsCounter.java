package org.example;

public class SameDivisorsCounter {

    public static int countSameDivisorPairs(int limit) {
        int count = 0;
        for (int i = 1; i < limit; i++) {
            if (countDivisors(i) == countDivisors(i + 1)) {
                count++;
            }
        }
        return count;
    }

    private static int countDivisors(int number) {
        int count = 0;
        int sqrt = (int) Math.sqrt(number);
        for (int i = 1; i <= sqrt; i++) {
            if (number % i == 0) {
                count += 2;
                if (i * i == number) {
                    count--;
                }
            }
        }
        return count;
    }
}