public class Result {
    public byte[] target;
    public byte[] message;
    public byte[] digest;
    public long counter;
    public long time;

    public Result(byte[] target, byte[] message, byte[] digest, long counter, long time) {
        this.target = target;
        this.message = message;
        this.digest = digest;
        this.counter = counter;
        this.time = time;
    }

//    @Override
//    public String toString() {
//        return "Result{" +
//                "target=" + bytesToHexString(target) +
//                ", message=" + bytesToHexString(message) +
//                ", digest=" + bytesToHexString(digest) +
//                ", counter=" + counter +
//                '}';
//    }


    @Override
    public String toString() {
        return bytesToHexString(target) + ", " + bytesToHexString(message) + ", " + bytesToHexString(digest) + ", "
                + counter + ", " + time;
    }

    static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        for (byte b : bytes)
            stringBuilder.append(String.format("%02x", b));
        return stringBuilder.toString();
    }

}
