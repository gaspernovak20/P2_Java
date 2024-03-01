public class K01 {

    static void java() {
        System.out.println("   J    a   v     v  a ");
        System.out.println("   J   a a   v   v  a a");
        System.out.println("J  J  aaaaa   V V  aaaaa");
        System.out.println(" JJ  a     a   V  a     a");
    }

    static void kalkulator(int a, int b) {

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

    public static void main(String[] args) {
        java();
        kalkulator(1, 1);
    }
}
