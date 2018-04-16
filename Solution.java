import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

public class Solution implements Callable<Result> {

    private byte[] target;

    Solution(byte[] target) {
        if (target.length > 32) {
            this.target = new byte[32];
            int offset = target.length - 32;
            for (int i = 0; i < target.length; i++)
                this.target[i] = target[i + offset];
        } else {
            this.target = target.clone();
        }
    }

    Result calculation() throws NoSuchAlgorithmException {
        long s = System.nanoTime();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        Random random = new Random();
        CompareHelper compareHelper = new CompareHelper(target);
        byte[] message = new byte[56];
        byte[] result;
        long counter = 0;

        do {
            random.nextBytes(message);
            synchronized (this) {
                counter++;
            }
            result = messageDigest.digest(message);
        } while (!compareHelper.compare(result));
        long t = System.nanoTime() - s;

//        System.out.println("message: "+bytesToHexString(message));
//        System.out.println("digest: "+bytesToHexString(result));
//        System.out.println("counter: "+counter);

        return new Result(target, message, result, counter, t);

    }


    @Override
    public Result call() throws Exception {
        return calculation();
    }
}
