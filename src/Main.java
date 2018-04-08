import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
public class Main {

    private final static int[] B = {1};
    private final static int numOfP = 100; // for each b in B, generate 100 different P
    private final static int numOfGetM = 100; // for each P, try to get M 100 times

    public static void main(String[] args) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        double[] ave_time_of_size_B = new double[B.length]; //store the ave time of solving puzzles with size B

        for (int m=0; m<B.length;m++) {
            int b = B[m];
            double time_to_solve_size_B = 0.0;
            double[] ave_time_of_each_P = new double[numOfP]; //store the ave time of solving puzzle P
            for (int i = 0; i < numOfP; i++) {
                byte[] P = generateP(b);
                String puzzle = bytesToHex(P);
                System.out.println("The puzzle is " + puzzle);
                double time_to_solve_P = 0.0;
                for (int j = 0; j < numOfGetM; j++) {
                    long start_time = System.currentTimeMillis();
                    long all_time = 0;
                    while (true) {
                        boolean find = true;
                        String M = new String(generateP(16));
                        byte[] hash = digest.digest(M.getBytes(StandardCharsets.UTF_8));
                        for (int k = 0; k < b; k++) {
                            if (P[k] != hash[32 - b + k]) {
                                find = false;
                                break;
                            }
                        }
                        if (find) {
                            long end_time = System.currentTimeMillis();
                            all_time += end_time - start_time;
                            System.out.println("The hash of M is " + bytesToHex(hash));
                            break;
                        }
                    }
                    double all_time_s = all_time / 1000.0;
                    time_to_solve_P += all_time_s;
                }
                time_to_solve_P /= (numOfGetM * 1.0);
                System.out.println("The average time to solve puzzle " + puzzle + " is: " + time_to_solve_P + " s\n");
                ave_time_of_each_P[i] = time_to_solve_P;
                time_to_solve_size_B += time_to_solve_P;
            }
            time_to_solve_size_B /= (numOfP * 1.0);
            ave_time_of_size_B[m] = time_to_solve_size_B;
            System.out.println("The average time to solve puzzle of size " + b + " is: " + time_to_solve_size_B + " s\n");
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
                r[i] += b[j] * (int)Math.pow(2, j);
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
