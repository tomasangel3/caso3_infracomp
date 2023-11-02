import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

public class BitcoinMining {

    public static void main(String[] args) {
        String C = "your_transaction_data_here"; // Replace with your transaction data
        int leadingZeros = 20; // Change the desired number of leading zeros
        int numThreads = 2; // Number of threads

        AtomicBoolean found = new AtomicBoolean(false);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            final int threadNum = i;
            new Thread(() -> mineBitcoin(C, leadingZeros, found, threadNum)).start();
        }

        while (!found.get()) {
            // Continue waiting until a solution is found
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Time taken: " + elapsedTime + " ms");
    }

    public static void mineBitcoin(String C, int leadingZeros, AtomicBoolean found, int threadNum) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256"); // You can change this to "SHA-512" if needed
            StringBuilder prefixZeros = new StringBuilder();
            for (int i = 0; i < leadingZeros; i++) {
                prefixZeros.append("0");
            }

            for (int v = 0; v <= 9999999; v++) { // Search space for v
                String data = C + v;
                byte[] hash = md.digest(data.getBytes());

                // Convert the byte array to a hexadecimal string
                StringBuilder hexString = new StringBuilder();
                for (byte b : hash) {
                    hexString.append(String.format("%02x", b));
                }

                if (hexString.toString().startsWith(prefixZeros.toString())) {
                    System.out.println("Thread " + threadNum + " found a solution:");
                    System.out.println("C: " + C);
                    System.out.println("v: " + v);
                    System.out.println("Hash: " + hexString);
                    found.set(true);
                    break;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
