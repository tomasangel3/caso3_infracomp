public class Main {
    public static void main(String[] args) {
        String input = "Your transaction data"; // Replace with your actual transaction data
        int zeros = 5; // Change the number of zeros as needed
        int numThreads = 2; // Change the number of threads as needed

        long startTime = System.currentTimeMillis();

        // Create and start threads
        Thread[] threads = new Thread[numThreads];
        Miner[] miners = new Miner[numThreads];
        for (int i = 0; i < numThreads; i++) {
            miners[i] = new Miner(input, zeros, i, numThreads);
            threads[i] = new Thread(miners[i]);
            threads[i].start();
        }

        // Wait for threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("Mining completed in " + (endTime - startTime) + " milliseconds");
    }
}
