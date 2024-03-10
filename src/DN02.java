public class DN02 {
    public static void main(String[] args) {
        int argLen = args[0].length();

        int koren = (int) Math.sqrt(argLen + 3);

        int idx = 0;
        for (int i = 0; i < koren; i++) {
            for (int j = 0; j < koren; j++) {
                if (idx < argLen){
                    System.out.print(" " + args[0].charAt(idx) + " ");
                }else {
                    System.out.print(" . ");
                }
                idx++;
            }
            System.out.println();
        }
    }
}
