package DN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.round;

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

    static int[][] zmanjsajSliko(int[][] picture) {

        int pictureWidth = picture[0].length;
        int pictureHeight = picture.length;

        if (pictureWidth < 3 || pictureHeight < 3) {
            return picture;
        }

        int pictureZipWidth = pictureWidth / 2;
        int pictureZipHeight = pictureHeight / 2;

        int[][] pictureZip = new int[pictureZipHeight][pictureZipWidth];

        int sum, pixelZip;

        for (int y = 0; y < pictureHeight / 2 * 2; y += 2) {
            for (int x = 0; x < pictureWidth / 2 * 2; x += 2) {
                sum = picture[y][x] + picture[y][x + 1] + picture[y + 1][x] + picture[y + 1][x + 1];
                pixelZip = sum / 4;
                pictureZip[y / 2][x / 2] = pixelZip;
            }
        }

        return pictureZip;
    }

    static int[][] rotirajSliko(int[][] picture) {

        int pictureWidth = picture[0].length;
        int pictureHeight = picture.length;

        int[][] rotatedPicture = new int[pictureWidth][pictureHeight];

        for (int y = 0; y < pictureHeight; y++) {
            for (int x = 0; x < pictureWidth; x++) {
                rotatedPicture[x][pictureHeight - y - 1] = picture[y][x];
            }
        }

        return rotatedPicture;
    }

    static int poisciMaxVrstico(int[][] picture) {

        int rowIndex = 0;
        int maxBrightnessDifference = 0;

        for (int y = 0; y < picture.length; y++) {
            int lightest = picture[y][0];
            int darkest = picture[y][0];

            for (int x = 0; x < picture[0].length; x++) {
                int currentPixel = picture[y][x];

                if (currentPixel > lightest) {
                    lightest = currentPixel;
                }

                if (currentPixel < darkest) {
                    darkest = currentPixel;
                }
            }

            int brightnessDifference = lightest - darkest;

            if (brightnessDifference > maxBrightnessDifference) {
                maxBrightnessDifference = brightnessDifference;
                rowIndex = y;
            }
        }

        return rowIndex;
    }


    static int[][] zrcaliSliko(int[][] picture) {
        int pictureWidth = picture[0].length;
        int pictureHeight = picture.length;

        int[][] mirroredPicture = new int[pictureHeight][pictureWidth];

        for (int y = 0; y < pictureHeight; y++) {
            for (int x = 0; x < pictureWidth / 2; x++) {
                mirroredPicture[y][pictureWidth - 1 - x] = picture[y][x];
                mirroredPicture[y][x] = picture[y][pictureWidth - 1 - x];
            }
        }

        if (pictureWidth % 2 == 1) {
            for (int y = 0; y < pictureHeight; y++) {
                mirroredPicture[y][pictureWidth / 2] = picture[y][pictureWidth / 2];
            }
        }

        return mirroredPicture;
    }

    static int[][][] preberiBarvnoSliko(String name) {

        int[][][] pictureRGB = new int[0][][];

        try {
            Scanner pictureDoc = new Scanner(new File(name));

            String[] pictureStats = pictureDoc.nextLine().split(" ");

            int pictureWidth = Integer.parseInt(pictureStats[1]);
            int pictureHeight = Integer.parseInt(pictureStats[3]);

            pictureRGB = new int[3][pictureHeight][pictureWidth];

            for (int i = 0; i < pictureWidth * pictureHeight; i++) {
                int pixel = pictureDoc.nextInt();

                int blue = pixel & 1023;
                int green = (pixel >> 10) & 1023;
                int red = (pixel >> 20) & 1023;

                pictureRGB[0][i / pictureWidth][i % pictureWidth] = red;
                pictureRGB[1][i / pictureWidth][i % pictureWidth] = green;
                pictureRGB[2][i / pictureWidth][i % pictureWidth] = blue;

//                System.out.printf("%d %d %d\n", red, green, blue);
            }
        } catch (FileNotFoundException e) {
            System.out.print("Napaka: datoteka " + name + " ne obstaja.\n");
            System.exit(0);
        }
        return pictureRGB;
    }

    static void izpisiBarvnoSliko(int[][][] picture) {

        int pictureHeight = picture[0].length;
        int pictureWidth = picture[0][0].length;

        System.out.printf("velikost slike: %d x %d\n", pictureWidth, pictureHeight);

        for (int y = 0; y < pictureHeight; y++) {
            for (int x = 0; x < pictureWidth; x++) {
                System.out.printf("(%4d,%4d,%4d) ", picture[0][y][x], picture[1][y][x], picture[2][y][x]);
            }
            System.out.println();
        }
    }

    static int[][] pretvoriVSivinsko(int[][][] picture) {

        int pictureHeight = picture[0].length;
        int pictureWidth = picture[0][0].length;

        int[][] grayPicture = new int[pictureHeight][pictureWidth];

        for (int y = 0; y < pictureHeight; y++) {
            for (int x = 0; x < pictureWidth; x++) {
                int pixelSum = picture[0][y][x] + picture[1][y][x] + picture[2][y][x];
                int pixelAvg = pixelSum / 3;
                int pixelValue = pixelAvg * 255 / 1023;
                grayPicture[y][x] = pixelValue;
            }
        }

        return grayPicture;
    }

    static void preberiVseInIzpisi(String[] pictureNames) {

        String[][] picsBrightnessSorted = new String[pictureNames.length][2];

        int currentlyStored = 0;

        for (String picName : pictureNames) {
//            System.out.println(picName);
            int[][] picture = preberiSliko(picName);
            int pictureBrightness = (int) Math.round(svetlostSlike(picture));

            int place = 0;

//            Storing pic in correct order
            for (int i = 0; i <= currentlyStored; i++) {

//                On bottom
                if (i == currentlyStored) {
                    place = i;
                    break;
                }

                String currentPicName = picsBrightnessSorted[i][0];
                int currentPicBright = Integer.parseInt(picsBrightnessSorted[i][1]);

//                Sorted by brightness
                if (currentPicBright < pictureBrightness) {
                    place = i;
                    break;
                } else if (currentPicBright == pictureBrightness) {
//                    Ordered by name
                    if (currentPicName.toLowerCase().compareTo(picName.toLowerCase()) > 0) {
                        place = i;
                        break;
                    }
                }
            }

            if (currentlyStored != 0) {
                for (int i = currentlyStored; i > place; i--) {
                    picsBrightnessSorted[i][0] = picsBrightnessSorted[i - 1][0];
                    picsBrightnessSorted[i][1] = picsBrightnessSorted[i - 1][1];
                }
            }

            picsBrightnessSorted[place][0] = picName;
            picsBrightnessSorted[place][1] = Integer.toString(pictureBrightness);

            currentlyStored++;
        }

        for (String[] picStats : picsBrightnessSorted) {
            System.out.printf("%s (%d)\n", picStats[0], Integer.parseInt(picStats[1]));
        }
    }

    static int[][] konvolucijaJedroCustom(int[][] picture, double[][] core) {
        int pictureHeight = picture.length;
        int pictureWidth = picture[0].length;

        int[][] konovolucijaSlike = new int[pictureHeight - 2][pictureWidth - 2];

        int prevColumVal, currentColumVal, nextColumVal, newPixelVal;

        for (int y = 1; y < pictureHeight - 1; y++) {
            for (int x = 1; x < pictureWidth - 1; x++) {
                prevColumVal = (int) (Math.round(picture[y - 1][x - 1] * core[0][0]) + Math.round(picture[y][x - 1] * core[1][0]) + Math.round(picture[y + 1][x - 1] * core[2][0]))
                ;
                currentColumVal = (int) (Math.round(picture[y - 1][x] * core[0][1]) + Math.round(picture[y][x] * core[1][1]) + Math.round(picture[y + 1][x] * core[2][1]))
                ;
                nextColumVal = (int) (Math.round(picture[y - 1][x + 1] * core[0][2]) + Math.round(picture[y][x + 1] * core[1][2]) + Math.round(picture[y + 1][x + 1] * core[2][2]))
                ;

                newPixelVal = prevColumVal + currentColumVal + nextColumVal;

                konovolucijaSlike[y - 1][x - 1] = newPixelVal;
            }
        }

        return konovolucijaSlike;
    }

    static int[][] konvolucijaJedro(int[][] picture) {
        double[][] core = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};

        return konvolucijaJedroCustom(picture, core);
    }

    static int[][] imageEnlarge(int[][] picture) {
        int pictureHeight = picture.length;
        int pictureWidth = picture[0].length;

        int[][] enlargePicture = new int[(pictureHeight + 2)][(pictureWidth + 2)];


//        Adding extra border
//        Top
        for (int x = 0; x < pictureWidth; x++) {
            enlargePicture[0][x + 1] = picture[0][x];
        }

//        Right
        for (int y = 0; y < pictureHeight; y++) {
            enlargePicture[y + 1][pictureWidth + 1] = picture[y][pictureWidth - 1];
        }

//        Bottom
        for (int x = 0; x < pictureWidth; x++) {
            enlargePicture[pictureHeight + 1][x + 1] = picture[pictureHeight - 1][x];
        }

//      Left
        for (int y = 0; y < pictureHeight; y++) {
            enlargePicture[y + 1][0] = picture[y][0];
        }

//        Corners
        enlargePicture[0][0] = picture[0][0];
        enlargePicture[0][pictureWidth + 1] = picture[0][pictureWidth - 1];
        enlargePicture[pictureHeight + 1][pictureWidth + 1] = picture[pictureHeight - 1][pictureWidth - 1];
        enlargePicture[pictureHeight + 1][0] = picture[pictureHeight - 1][0];

//        Core
        for (int y = 0; y < pictureHeight; y++) {
            for (int x = 0; x < pictureWidth; x++) {
                enlargePicture[y + 1][x + 1] = picture[y][x];
            }
        }

        return enlargePicture;
    }

    static int[][] konvolucijaGlajenje(int[][] picture) {
        double[][] core = {{(1.0 / 16), (1.0 / 8), (1.0 / 16)}, {(1.0 / 8), (1.0 / 4), (1.0 / 8)}, {(1.0 / 16), (1.0 / 8), (1.0 / 16)}};
//      double[][] core = {{0.16, 0.8, 0.16}, {0.8, 0.4, 0.8}, {0.16, 0.8, 0.16}};
//      double[][] core = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};

        int[][] enlargePicture = imageEnlarge(picture);

        return konvolucijaJedroCustom(enlargePicture, core);
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

        if (Objects.equals(args[0], "zmanjsaj")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);
            int[][] pictureZip = zmanjsajSliko(picture);
            izpisiSliko(pictureZip);
        }

        if (Objects.equals(args[0], "rotiraj")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);
            int[][] rotatedPicture = rotirajSliko(picture);
            izpisiSliko(rotatedPicture);
        }

        if (Objects.equals(args[0], "zrcali")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);
            int[][] mirroredPicture = zrcaliSliko(picture);
            izpisiSliko(mirroredPicture);
        }

        if (Objects.equals(args[0], "vrstica")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);
            int rowIndex = poisciMaxVrstico(picture);
            System.out.printf("Max razlika svetlo - temno je v %d. vrstici.", rowIndex + 1);
        }

        if (Objects.equals(args[0], "barvna")) {
            String pictureName = args[1];
            int[][][] picture = preberiBarvnoSliko(pictureName);
            izpisiBarvnoSliko(picture);
        }

        if (Objects.equals(args[0], "sivinska")) {
            String pictureName = args[1];
            int[][][] picture = preberiBarvnoSliko(pictureName);
            int[][] grayPicture = pretvoriVSivinsko(picture);
            izpisiSliko(grayPicture);
        }

        if (Objects.equals(args[0], "uredi")) {
            int numOfPics = args.length - 1;
            String[] pictureNames = new String[numOfPics];

            for (int i = 1; i < numOfPics + 1; i++) {
                pictureNames[i - 1] = args[i];
            }
            preberiVseInIzpisi(pictureNames);
        }

        if (Objects.equals(args[0], "jedro")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);
            int[][] konovolucijaPicture = konvolucijaJedro(picture);
            izpisiSliko(konovolucijaPicture);
        }

        if (Objects.equals(args[0], "glajenje")) {
            String pictureName = args[1];
            int[][] picture = preberiSliko(pictureName);
            int[][] konovolucijaPicture = konvolucijaGlajenje(picture);
            izpisiSliko(konovolucijaPicture);
        }


    }
}
