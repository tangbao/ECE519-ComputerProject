
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {

        int len = Integer.parseInt(args[0]);

        int N = Integer.parseInt(args[1]);

        long s = System.nanoTime();

        Random random = new Random();
        byte[] target = new byte[len];

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Result>> results = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            random.nextBytes(target);
            results.add(executorService.submit(new Solution(target)));
        }
        executorService.shutdown();

        for (Future<Result> result: results) {
            try {
                System.out.println(result.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


        System.out.println("time: "+(System.nanoTime()-s));


    }
}




