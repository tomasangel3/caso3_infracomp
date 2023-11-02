import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


class Miner implements Runnable {
    private String input;
    private int zeros;
    private int threadId;
    private int numThreads;

    public Miner(String input, int zeros, int threadId, int numThreads) {
        this.input = input;
        this.zeros = zeros;
        this.threadId = threadId;
        this.numThreads = numThreads;
    }

    @Override
    public void run() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            while (true) {
                String v = generateRandomString(7); // Genera un string aleatorio 'v'
                String data = input + v;
                byte[] hash = md.digest(data.getBytes());

                if (hasDesiredLeadingZeros(hash, zeros)) {
                    System.out.println("Thread " + threadId + " encontro una solucion:");
                    System.out.println("Input: " + input);
                    System.out.println("v: " + v);
                    System.out.println("Hash: " + bytesToHex(hash));
                    break;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
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

    // Include the same generateRandomString, calculateSHA256, and hasDesiredLeadingZeros functions as before


// bytesToHex function to convert hash to a hexadecimal string
public static String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
            hexString.append('0');
        }
        hexString.append(hex);
    }
    return hexString.toString();
}
}
