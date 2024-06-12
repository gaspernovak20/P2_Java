package DN;

import java.io.FileInputStream;
import java.io.IOException;

public class DN10 {
    public static void  main(String[] args) {
        if (args.length != 1) {
            System.out.println("Argument ni podan");
            return;
        }

        String fileName = args[0];
        try {
            FileInputStream fis = new FileInputStream(fileName);

            byte[] header = new byte[8];
            fis.read(header);

            while (fis.available() > 0) {

                byte[] lengthBytes = new byte[4];
                fis.read(lengthBytes);
                int length = ((lengthBytes[0] & 0xFF) << 24) | ((lengthBytes[1] & 0xFF) << 16) |
                        ((lengthBytes[2] & 0xFF) << 8) | (lengthBytes[3] & 0xFF);

                byte[] typeBytes = new byte[4];
                fis.read(typeBytes);
                String type = new String(typeBytes, "UTF-8");

                fis.skip(length);
                fis.skip(4);

                System.out.println("Chunk: " + type + ", length: " + length);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
