import java.util.Random;

public class Utility {    
    public static String generateRandomString(int length) {
        String caracteres = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = caracteres.charAt(random.nextInt(caracteres.length()));
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static boolean hasDesiredLeadingZeros(byte[] hash, int zeros) {
        for (int i = 0; i < zeros / 2; i++) {
            if (hash[i] != 0) {
                return false;
            }
        }
        int remainingZeros = zeros % 2;
        if (remainingZeros == 0) {
            return true;
        } else {
            return (hash[zeros / 2] & 0xF0) == 0;
        }
    }
}
