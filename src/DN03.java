import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class DN03 {
    public static void main(String[] args) {
        try {
            Scanner doc = new Scanner(new File(args[0]));
            StringBuilder password = new StringBuilder();

            int docLen = Integer.parseInt(doc.nextLine());
            String[] docFile = new String[docLen];

            for (int i = 0; i < docLen; i++) {
                docFile[i] = doc.nextLine();
            }

            int rndSeed = Integer.parseInt(args[2]);

            Random rndLine = new Random(rndSeed);
            Random rndChar = new Random(rndSeed);

            for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                String currentStr = docFile[rndLine.nextInt(docLen)];

                int strLen = currentStr.length();

                password.append(currentStr.charAt(rndLine.nextInt(strLen)));

            }

            System.out.println(password);

        } catch (Exception e) {
            System.out.print("NI podanih argumentov" + e);
        }
    }
}

