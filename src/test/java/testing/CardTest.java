package testing;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void simulateLargeOrder() {

        //given
        Card card = new Card();

        //when
        //then
        assertTimeout(Duration.ofMillis(10), card::simulateLargeOrder);
    }
}