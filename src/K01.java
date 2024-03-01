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
            System.out.printf("Obseg kroga s polmerom r=%f je %.3f%n", r, (2 * Math.PI * r));
            System.out.printf("Ploscina kroga s polmerom r=%f je %.3f%n", r, (Math.PI * r * r));
        }
    }

    public static void main(String[] args) {
        java();
        kalkulator(1, 1);
        krog(3.2, 4);
    }
}
