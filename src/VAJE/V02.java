package VAJE;

public class V02 {

    static long fakultetaL(int n) {
//        1.iterativno
//        long nF = 1;
//
//        for (int i = 2; i <= n; i++) {
//            nF *= i;
//        }
//
//        return nF;

//      2.rekurzivno
        if (n <= 1) {
            return 1;
        }
        return n * fakultetaL(n - 1);
    }

    static long stirlingL(int n) {
        return Math.round(Math.sqrt(2 * Math.PI * n) * Math.pow(n / Math.E, n));
    }

    static double strlingDeviation(long factorial, long strling) {
        long difference = factorial - strling;
        double deviation = (double) (difference * 100) / factorial;
        return deviation;
    }

    public static void main(String[] args) {
        System.out.print("  n              n!            Stirling(n)      napaka (%)\n");
        System.out.print("----------------------------------------------------------\n");

        for (int i = 1; i <= 20; i++) {
            long factorial = fakultetaL(i);
            long strling = stirlingL(i);
            double deviation = strlingDeviation(factorial, strling);
            System.out.printf("%3d %20d %20d %11.7f\n", i, factorial, strling, deviation);
        }

    }
}
