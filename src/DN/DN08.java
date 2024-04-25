package DN;

abstract class Lik {
    abstract public double obseg();
}

class Kvadrat extends Lik {

    private int a;

    public Kvadrat(int a) {
        this.a = a;
    }

    @Override
    public double obseg() {
        return this.a * 4;
    }
}

class Pravokotnik extends Lik {

    private int a, b;

    public Pravokotnik(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double obseg() {
        return this.a * 2 + this.b * 2;
    }
}

class Nkotnik extends Lik {
    private int a, n;

    public Nkotnik(int n, int a) {
        this.n = n;
        this.a = a;
    }

    @Override
    public double obseg() {
        return this.a * this.n;
    }

}

public class DN08 {

    static Lik[] readLiki(String[] newLiki) {

        Lik[] liki = new Lik[newLiki.length];

        int i = 0;
        for (String l : newLiki) {
            String[] likStat = l.split(":");

            switch (likStat[0]) {
                case "kvadrat":
                    liki[i++] = new Kvadrat(Integer.parseInt(likStat[1]));
                    break;
                case "pravokotnik":
                    liki[i++] = new Pravokotnik(Integer.parseInt(likStat[1]), Integer.parseInt(likStat[2]));
                    break;
                case "nkotnik":
                    liki[i++] = new Nkotnik(Integer.parseInt(likStat[1]), Integer.parseInt(likStat[2]));
            }
        }

        return liki;
    }

    static int totalDiameter(Lik[] liki) {
        int tDiameter = 0;
        for (Lik lik : liki) {
            tDiameter += (int) lik.obseg();
        }
        return tDiameter;
    }

    public static void main(String[] args) {

        Lik[] liki = readLiki(args);
        System.out.println(totalDiameter(liki));
    }
}
