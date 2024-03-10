package VAJE;

public class V01 {

//    static void pravokotnikStevil(int width, int height) {
//        for (int y = 1; y <= height; y++) {
//            for (int x = 1; x <= width; x++) {
//                System.out.print(y);
//            }
//        }
//        System.out.println();
//    }

    static void pravokotnikStevil(int width, int height) {
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                if (x > 9) {
                    System.out.print(x % 10);
                } else {
                    System.out.print(x);
                }
            }
            System.out.println();
        }
    }

    static void pravokotnik(int margin, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int o = 0; o < margin; o++) {
                System.out.print(" ");
            }
            for (int x = 0; x < width; x++) {
                System.out.print("X");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        pravokotnikStevil(12, 3);
        pravokotnik(5,3,3);
    }
}
