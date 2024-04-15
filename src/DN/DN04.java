package DN;

public class DN04 {

    public static void main(String[] args) {
        try {
            String arg = args[0];
            int arg_len = arg.length();

            for (int i = 0; i < arg_len / 8; i++) {
                int char_hesh = 0;
                for (int j = 0; j < 8; j++) {
                    int bin = arg.charAt(i * 8 + j) - '0';
                    char_hesh += bin * (int) Math.pow(2, 8 - j - 1);
                }
                char c = (char) char_hesh;
                System.out.print(c);
            }
        } catch (Exception e) {
            System.out.print("No argument provided:" + e);
        }


    }

}
