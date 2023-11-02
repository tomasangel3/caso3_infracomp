public class Main {
    public static void main(String[] args) {
        String input = "Clave"; 
        int ceros = 5; // Cambiar el numero de ceros
        int numThreads = 2; 

        long empiezaTiempo = System.currentTimeMillis();

        // Crear y empezar Threads
        Thread[] threads = new Thread[numThreads];
        Miner[] miners = new Miner[numThreads];
        for (int i = 0; i < numThreads; i++) {
            miners[i] = new Miner(input, ceros, i, numThreads);
            threads[i] = new Thread(miners[i]);
            threads[i].start();
        }

        // Espera a que terminen lod Threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long terminaTiempo = System.currentTimeMillis();
        System.out.println();
        System.out.println("Se encontro el hash en " + (terminaTiempo - empiezaTiempo) + " miliseconds");
    }
}
