public class CompareHelper {

    private byte[] target;

    CompareHelper(byte[] target) {
        if (target.length > 32)
            this.target = new byte[32];
        else
            this.target = new byte[target.length];

        int i = target.length;
        int j = this.target.length;

        while (i > 0 && j > 0) {
            this.target[j - 1] = target[i - 1];
            i--;
            j--;
        }
    }


    boolean compare(byte[] digest) {
        boolean flag = true;
        if (digest.length < target.length)
            flag = false;
        int offset = digest.length - target.length;
        for (int i = 0; i < target.length; i++)
            flag &= (digest[i + offset] == target[i]);
        return flag;
    }


}
