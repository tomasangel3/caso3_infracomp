import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el algortimo que desea usar(SHA-256/SHA-512): ");
        String algorithm = scanner.nextLine();

        System.out.print("Ingresa la cadena de entrada: ");
        String input = scanner.nextLine();

        System.out.print("Ingresa el numero de ceros: ");
        int ceros = scanner.nextInt();

        System.out.print("Ingresa el numero de threads (1/2) : ");
        int numThreads = scanner.nextInt();

        long empiezaTiempo = System.currentTimeMillis();

        // Crear y empezar Threads
        Thread[] threads = new Thread[numThreads];
        Miner[] miners = new Miner[numThreads];
        for (int i = 0; i < numThreads; i++) {
            miners[i] = new Miner(input, ceros, i, numThreads, algorithm);
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
