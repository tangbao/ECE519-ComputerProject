import java.util.Random;

public class Tmp {
    public static void main(String[] args) {
        Random random = new Random();
        byte[] target = new byte[2];

        for (int i = 0; i < 10; i++) {
            random.nextBytes(target);
            System.out.println(bytesToHexString(target));
        }

    }


    static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        for (byte b: bytes)
            stringBuilder.append(String.format("%02x", b));
        return stringBuilder.toString();
    }

}
