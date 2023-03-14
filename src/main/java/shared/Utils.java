package shared;

public class Utils {

    /**
     * Generates a Alphabet and returns it as a Char Array
     * @param capital Defines if the Letters int the returned char Array should be Capital
     * @return Alphabet as Char Array
     * */
    public static char[] generateAlphabet(boolean capital) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return capital ? alphabet.toUpperCase().toCharArray() : alphabet.toCharArray();
    }

}
