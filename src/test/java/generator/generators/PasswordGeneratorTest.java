package generator.generators;

import base.Main;
import org.junit.jupiter.api.Test;
import ui.windows.WindowDesign;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    String specialCharStr = "§\"!$%/&()=?";
    WindowDesign design = Main.buildDesign();

    @Test
    void shouldReturnZeroCharPass() {
        var generator = new PasswordGenerator(design, 0);
        String value = generator.generate();

        assertEquals(0, value.length());
        assertFalse(containsChars(specialCharStr, value));
    }

    @Test
    void shouldReturnEightyCharPass() {
        var generator = new PasswordGenerator(design, 80);
        String value = generator.generate();

        assertEquals(80, value.length());
        assertFalse(containsChars(specialCharStr, value));
    }

    @Test
    void shouldReturnTwoHundredCharPass() {
        var generator = new PasswordGenerator(design, 200);
        String value = generator.generate();

        assertEquals(200, value.length());
        assertFalse(containsChars(specialCharStr, value));
    }

    @Test
    void shouldReturnZeroCharPassWithSpecialChar() {
        var generator = new PasswordGenerator(design, 0);
        String value = generator.generate();

        assertEquals(0, value.length());
        assertFalse(containsChars(specialCharStr, value));
    }

    @Test
    void shouldReturnEightyCharPassWithSpecialChar() {
        var generator = new PasswordGenerator(design, 80);
        String value = generator.generate();

        assertEquals(80, value.length());
    }

    @Test
    void shouldReturnTwoHundredCharPassWithSpecialChar() {
        var generator = new PasswordGenerator(design, 200);
        String value = generator.generate();
        assertEquals(200, value.length());
    }

    boolean containsChars(String sequence, String target) {
        for(char seqChar : sequence.toCharArray()) {
            for(char tarChar : target.toCharArray()) {
                if(tarChar == seqChar) return true;
            }
        }
        return false;
    }
}