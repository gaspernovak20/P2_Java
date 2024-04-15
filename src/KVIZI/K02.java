package KVIZI;

public class K02{
    static int vsotaStevk(String str){
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('0' <= c && c <= '9')
                sum += c - '0';
        }
        return sum;
    }

    static boolean preveriRep(String a, String b){
        return a.toLowerCase().contains(b.toLowerCase()) || b.toLowerCase().contains(a.toLowerCase());
    }

    static int[] range(int a, int b, int c) {

        int tableSize = (b - 1 - a) / c + 1;

        int[] table = new int[tableSize];

        for (int i = 0; i < tableSize; i++) {
            int value = a + i * c;
            table[i] = value;
        }

        return table;
    }

    static void rotiraj(int[] tabela, int k){

        int [] store = new int[k] ;

        for(int i =0; i<k; i++){
            int s = tabela[0];
            for(int j=1; j< tabela.length; j++) {
                tabela[j-1] = tabela[j];
            }
            tabela[tabela.length-1]=s;
        }
    }

    static int[] duplikati(int[] tabela) {

        int[] cleanTable = new int[tabela.length];

        int idx = 0;

        for (int i = 0; i < tabela.length; i++) {
            int currentElm = tabela[i];
            boolean isDublicate = false;

            for (int j = 0; j < idx; j++) {
                if (cleanTable[j] == currentElm) {
                    isDublicate = true;
                }
            }

            if (!isDublicate) {
                cleanTable[idx] = currentElm;
                idx++;
            }
        }

        int[] fixedTabel = new int[idx];

        for (int i = 0; i < idx; i++) {
            fixedTabel[i] = cleanTable[i];
        }

        return fixedTabel;
    }

    String binarnoSestej(String s, String b) {
        int sLen = s.length(), bLen = b.length();
        int difference = 0;

        if (sLen < bLen) {
            difference = bLen - sLen;
            String whiteSpace = "0".repeat(difference);
            s = whiteSpace + s;
        } else if (sLen > bLen) {
            difference = sLen - bLen;
            String whiteSpace = "0".repeat(difference);
            b = whiteSpace + b;
        }


        int sChar, bChar;
        int carry = 0;
        int value = 0;

        StringBuilder result = new StringBuilder();

        for (int idx = s.length() - 1; idx >= 0; idx--) {
            sChar = s.charAt(idx) - '0';
            bChar = b.charAt(idx) - '0';

            value = sChar + bChar + carry;

            switch (value) {
                case 3:
                    carry = 1;
                    value = 1;
                    break;
                case 2:
                    carry = 1;
                    value = 0;
                    break;
                case 1:
                    carry = 0;
                    value = 1;
                    break;
            }

            char c = (char) (value + '0');

            result.insert(0, c);
        }

        if (carry == 1) {
            result.insert(0, 1);
        }

        return result.toString();
    }

    public static void main(String[] args) {

    }
}