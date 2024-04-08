import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class DN05 {

    static int[][] preberiSliko(String name) {
//        Part-time picture declaration
        int[][] picture = new int[0][];

        try {
            Scanner pictureDoc = new Scanner(new File(name));

            try {
                String[] pictureStats = pictureDoc.nextLine().split(" ");

//                Checking the photo format
                if (!Objects.equals(pictureStats[0], "P2:") || !Objects.equals(pictureStats[2], "x")) {
                    System.out.print("Napaka: datoteka " + name + "ni v formatu P2.\n");
                    System.exit(0);
                }

                try {
                    int width = Integer.parseInt(pictureStats[1]);
                    int height = Integer.parseInt(pictureStats[3]);

//                    Checking if size of picture is valid (not zero or neg.)
                    if (width <= 0 || height <= 0) {
                        System.out.print("Napaka: datoteka " + name + " ni v formatu P2 (velikost slike je 0 ali negativna).\n");
                        System.exit(0);
                    }

//                    Declaring size of picture
                    picture = new int[height][width];

                    for (int i = 0; i < height * width; i++) {
                        try {
//                           Reading picture values
                            int num = pictureDoc.nextInt();

//                           Check if pixel value in range 0-255
                            if (0 > num || num > 255) {
                                System.out.print("Napaka: datoteka " + name + " vsebuje podatke izven obsega 0 do 255.");
                                System.exit(0);
                            }

                            picture[i / width][i % width] = num;

                        } catch (NoSuchElementException e) {
//                            Exception enough not data about the picture
                            System.out.print("Napaka: datoteka " + name + " vsebuje premalo podatkov.");
                            System.exit(0);
                        }
                    }


                } catch (NumberFormatException e) {
//                    Exception if width or height is no num. or ...
                    System.out.print("Napaka: datoteka " + name + " ni v formatu P2 (velikost slike ni pravilna).\n");
                    System.exit(0);
                }


            } catch (NoSuchElementException e) {
//               Exception if file is empty
                System.out.print("Napaka: Datoteka " + name + " je prazna.\n");
                System.exit(0);
            }

            pictureDoc.close();
        } catch (FileNotFoundException e) {
//           Exception if file doesn't exist
            System.out.print("Napaka: datoteka " + name + " ne obstaja.\n");
            System.exit(0);
        }

        return picture;
    }

    static void izpisiSliko(int[][] picture) {
        System.out.printf("velikost slike: %d x %d\n", picture[0].length, picture.length);
        for (int[] row : picture) {
            for (int x = 0; x < picture[0].length; x++) {
                System.out.printf("%3d ", row[x]);
            }
            System.out.println();
        }
    }

    static void izpisiHistogram(int[][] picture) {
        int[] histogramValues = new int[256];

        for (int y = 0; y < picture.length; y++) {
            for (int x = 0; x < picture[0].length; x++) {
                histogramValues[picture[y][x]]++;
            }
        }

        System.out.println("sivina : # pojavitev");
        for (int i = 0; i <= 255; i++) {
            if (histogramValues[i] != 0) {
                System.out.printf("%5d :   %d\n", i, histogramValues[i]);
            }
        }
    }

    static double svetlostSlike(int[][] picture) {
        int sum = 0;
        int picWidth = picture[0].length;
        int pivHeight = picture.length;

        for (int y = 0; y < pivHeight; y++) {
            for (int x = 0; x < picWidth; x++) {
                sum += picture[y][x];
            }
        }
        double avg = (double) sum / (picWidth * pivHeight);

        return avg;
    }

    public static void main(String[] args) {
        if (Objects.equals(args[0], "izpisi")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);
            izpisiSliko(picture);
        }

        if (Objects.equals(args[0], "histogram")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);
            izpisiHistogram(picture);
        }

        if (Objects.equals(args[0], "svetlost")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);

            System.out.printf("Srednja vrednost sivine na sliki%s je: %.2f", args[1], svetlostSlike(picture));
        }
    }
}
