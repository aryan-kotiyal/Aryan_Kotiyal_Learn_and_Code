import org.example.SameDivisorsCounter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SameDivisorsCounterTest {

    @Test
    void testSmallRange() {
        assertEquals(1, SameDivisorsCounter.countSameDivisorPairs(10));
    }

    @Test
    void testNoMatch() {
        assertEquals(0, SameDivisorsCounter.countSameDivisorPairs(1));
    }

    @Test
    void testSingleMatchAtEnd() {
        assertEquals(1, SameDivisorsCounter.countSameDivisorPairs(14));
    }

    @Test
    void testMultipleMatches() {
        assertEquals(4, SameDivisorsCounter.countSameDivisorPairs(30));
    }

    @Test
    void testLargeInputPerformance() {
        assertDoesNotThrow(() -> SameDivisorsCounter.countSameDivisorPairs(10000));
    }
}
