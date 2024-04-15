package KVIZI;

public class K01 {

    public static void java() {
        System.out.println("   J    a   v     v  a ");
        System.out.println("   J   a a   v   v  a a");
        System.out.println("J  J  aaaaa   V V  aaaaa");
        System.out.println(" JJ  a     a   V  a     a");
    }

    public static void kalkulator(int a, int b) {

        if (b == 0) {
            System.out.println("Napaka: deljenje z 0");
        } else {
            System.out.println(a + " + " + b + " = " + (a + b));
            System.out.println(a + " - " + b + " = " + (a - b));
            System.out.println(a + " x " + b + " = " + (a * b));
            System.out.println(a + " / " + b + " = " + (a / b));
            System.out.println(a + " % " + b + " = " + (a % b));
        }

    }


//    public static void nicli (int a, int b , int c){
//

    //    }
    public static void krog(double r, int d) {
        if (r <= 0) {
            System.out.println("Napaka: negativen polmer");
        } else if (d <= 0) {
            System.out.println("Napaka: negativen d");
        } else {
            System.out.printf("Obseg kroga s polmerom r=%.2f je %." + d + "f%n", r, (2 * Math.PI * r));
            System.out.printf("Ploscina kroga s polmerom r=%.2f je %." + d + "f%n", r, (Math.PI * r * r));
        }
    }

    public static String pretvoriSekunde(int s) {
        if (s < 0) {
            return "Število sekund ne more biti negativno";
        }

        int min = s / 60;
        int h = min / 60;
        min %= 60;
        s %= 60;

        return String.format("%02d:%02d:%02d", h, min, s);
    }

    public static void javaJavaJava(int n) {
        if (n < 0) {
            System.out.println("Napaka: negativen n");
        }

        for (int j = 0; j < n; j++) {
            System.out.print("     J    a   v     v  a   ");
        }
        System.out.println();
        for (int j = 0; j < n; j++) {
            System.out.print("     J   a a   v   v  a a  ");
        }
        System.out.println();
        for (int j = 0; j < n; j++) {
            System.out.print("  J  J  aaaaa   V V  aaaaa ");
        }
        System.out.println();
        for (int j = 0; j < n; j++) {
            System.out.print("   JJ  a     a   V  a     a");
        }
        System.out.println();
    }

    public static boolean jeFibonaccijevo(int n) {
        int y = 1;
        int x = 1;

        while (x <= n) {
            if (x == n) {
                return true;
            }
            int store = x;
            x += y;
            y = store;
        }

        return false;
    }

    public static boolean jePrastevilo(int n) {
        boolean jePra = n > 1;

        int i = 2;
        while (jePra && i < n) {
            if (n % i == 0) {
                jePra = false;
            }
            i++;
        }

        return jePra;
    }

    public static void vDesetisko(int n){
        int n8 = n;
        int n10 = 0;

        int i = 1;

        while (n > 0){
            if (n % 10 < 8){
                n10 += n % 10 * (int) Math.pow(8, i - 1);
            }else {
                System.out.printf("Število %d ni število v osmiškem sistemu (števka %d)\n", n8, (n % 10));
                return;
            }

            n /= 10;
            i++;
        }

        System.out.printf("%d(8) = %d(10)\n", n8, n10);
    }

    public static String pretvoriVDesetisko(String n , int b){
        int n10;
        try {
            n10 = Integer.parseInt(n, b);
            return String.format("%s(%d)=%d(10)", n, b, n10);
        }catch (Exception e){
            System.out.println(e);
            return  String.format("Napaka pri pretvorbi sistema - števka %d\n", b);
        }
    }

    public static void main(String[] args) {
        java();
        kalkulator(1, 1);
        krog(3.2, 4);
        pretvoriSekunde(65);
        javaJavaJava(3);
        vDesetisko(129);
        System.out.println(pretvoriVDesetisko("GBBA", 16));
    }
}
