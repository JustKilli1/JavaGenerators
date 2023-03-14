package shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";

    @Test
    void shouldReturnNoCapitalCharArray() {
        String generated = String.valueOf(Utils.generateAlphabet(false));

        assertEquals(alphabet.toLowerCase(), generated);
    }

    @Test
    void shouldReturnCapitalCharArray() {
        String generated = String.valueOf(Utils.generateAlphabet(true));

        assertEquals(alphabet.toUpperCase(), generated);
    }
}