class SubarrayMeanCalculator {
    private final long[] prefixSum;

    public SubarrayMeanCalculator(int[] numbers) {
        int size = numbers.length;
        prefixSum = new long[size + 1];

        for (int i = 1; i <= size; i++) {
            prefixSum[i] = prefixSum[i - 1] + numbers[i - 1];
        }
    }

    public long getFloorMean(int leftIndex, int rightIndex) {
        long sum = prefixSum[rightIndex] - prefixSum[leftIndex - 1];
        return sum / (rightIndex - leftIndex + 1);
    }
}