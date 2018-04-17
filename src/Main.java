import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SolvePuzzle implements Runnable{

    private int[] B = {4};
    private int numOfP = 1; // for each b in B, generate 100 different P
    private int numOfGetM = 1; // for each P, try to get M 100 times
    private MessageDigest digest;

    SolvePuzzle(){
    }

    SolvePuzzle(int[] B, int numOfP, int numOfGetM){
        this.B = B;
        this.numOfP = numOfP;
        this.numOfGetM = numOfGetM;
    }

    public void run(){
        try{
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            System.exit(1);
        }
        double[] ave_trial_of_size_B = new double[B.length]; //store the ave trials of solving puzzles with size B

        for (int m=0; m<B.length;m++) {
            int b = B[m];
            double trial_of_size_B = 0.0;
            double[] ave_trial_of_each_P = new double[numOfP]; //store the ave trials of solving puzzle P
            for (int i = 0; i < numOfP; i++) {
                byte[] P = generateP(b);
                String puzzle = bytesToHex(P);
                System.out.println("The puzzle is " + puzzle);
                double trial_to_solve_P = 0.0;
                for (int j = 0; j < numOfGetM; j++) {
                    int cnt = 0;
                    while (true) {
                        boolean find = true;
                        String M = new String(generateP(56));
                        cnt++;
                        byte[] hash = digest.digest(M.getBytes(StandardCharsets.UTF_8));
                        for (int k = 0; k < b; k++) {
                            if (P[k] != hash[32 - b + k]) {
                                find = false;
                                break;
                            }
                        }
                        if (find) {
                            System.out.println("The hash of M is " + bytesToHex(hash));
                            break;
                        }
                    }
                    trial_to_solve_P += cnt;
                }
                trial_to_solve_P /= (numOfGetM * 1.0);
                System.out.println("The average time to solve puzzle " + puzzle + " is: " + trial_to_solve_P + "\n");
                ave_trial_of_each_P[i] = trial_to_solve_P;
                trial_of_size_B += trial_to_solve_P;
            }
            trial_of_size_B /= (numOfP * 1.0);
            ave_trial_of_size_B[m] = trial_of_size_B;
            System.out.println("The average time to solve puzzle of size " + b + " is: " + trial_of_size_B + "\n");
        }
    }

    private static byte[] generateP(int B){
        byte[] r = new byte[B];
        for(int i=0; i<B; i++){
            int[] b = new int[8];
            for(int j=0;j<8;j++){
                if(Math.random() > 0.5){
                    b[j] = 1;
                }else{
                    b[j] = 0;
                }
                r[i] += b[j] << j;
            }
        }
        return r;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

public class Main {
    private final static int MAX_THREAD = 8;
    public static void main(String[] args)  {
        for(int i=0; i<MAX_THREAD; i++){
            new Thread(new SolvePuzzle()).run();
        }
    }


}
