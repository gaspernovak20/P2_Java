package DN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Tekmovalec {
    String drzava;
    String tekmovalec;
    String song;

    public Tekmovalec(String drzava, String tekmovalec, String song) {
        this.drzava = drzava;
        this.tekmovalec = tekmovalec;
        this.song = song;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setdrzava(String drzava) {
        this.drzava = drzava;
    }

    public String gettekmovalec() {
        return tekmovalec;
    }

    public void settekmovalec(String tekmovalec) {
        this.tekmovalec = tekmovalec;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

//    public String toString() {
//        return String.format("(<%s>) <%s> - %s", this.drzava, this.tekmovalec, this.song);
//    }


    @Override
    public String toString() {
        return "(" + drzava + ") " + tekmovalec + " - " + song;
    }
}

class Glas {
    String fromdrzava;
    String todrzava;
    int points;

    public Glas(String fromdrzava, String todrzava, int points) {
        this.points = points;
        this.todrzava = todrzava;
        this.fromdrzava = fromdrzava;
    }

    public String getFromdrzava() {
        return fromdrzava;
    }

    public void setFromdrzava(String fromdrzava) {
        this.fromdrzava = fromdrzava;
    }

    public String getTodrzava() {
        return todrzava;
    }

    public void setTodrzava(String todrzava) {
        this.todrzava = todrzava;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return fromdrzava + " --" + points + "t-> " + todrzava;
    }

    public void izpisiTekmovalce() {
    }
}

class Tekmovanje {
    ArrayList<Tekmovalec> seznamTekmovalcev;
    ArrayList<Glas> seznamGlasov;
    Kriterij kriterij = new OsnovniKriterij();

    public Tekmovanje(ArrayList<Tekmovalec> seznamTekmovalcev, ArrayList<Glas> seznamGlasov) {
        this.seznamGlasov = seznamGlasov;
        this.seznamTekmovalcev = seznamTekmovalcev;
    }

    public ArrayList<Tekmovalec> getSeznamTekmovalcev() {
        return seznamTekmovalcev;
    }

    public void setSeznamTekmovalcev(ArrayList<Tekmovalec> seznamTekmovalcev) {
        this.seznamTekmovalcev = seznamTekmovalcev;
    }

    public ArrayList<Glas> getSeznamGlasov() {
        return seznamGlasov;
    }

    public void setSeznamGlasov(ArrayList<Glas> seznamGlasov) {
        this.seznamGlasov = seznamGlasov;
    }

    public Kriterij getKriterij() {
        return kriterij;
    }

    public void setKriterij(Kriterij kriterij) {
        this.kriterij = kriterij;
    }

    public static Tekmovanje izDatotek(String datCompetitors, String datVotes) {

        try {
            Scanner scComp = new Scanner(new File(datCompetitors));
            Scanner scVotes = new Scanner(new File(datVotes));

            ArrayList<Tekmovalec> competitors = new ArrayList<>();
            ArrayList<Glas> votes = new ArrayList<>();

//            if (scComp.hasNextLine()) {
//                scComp.nextLine();
//            } else {
//                return null;
//            }
            scComp.nextLine();
            while (scComp.hasNext()) {
                String[] info = scComp.nextLine().split(";");
                competitors.add(new Tekmovalec(info[1], info[2], info[3]));
            }

//            if (scVotes.hasNextLine()) {
//                scVotes.nextLine();
//            } else {
//                return null;
//            }

            scVotes.nextLine();
            while (scVotes.hasNext()) {
                String[] info = scVotes.nextLine().split(";");
                votes.add(new Glas(info[2], info[3], Integer.parseInt(info[4])));
            }

            scVotes.close();
            scVotes.close();

            return new Tekmovanje(competitors, votes);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void izpisiTekmovalce() {

        if (seznamTekmovalcev.isEmpty()) {
            System.out.println("Seznam tekmovalcev je prazen.");
            return;
        }

        System.out.println("Seznam tekmovalcev:");
        for (Tekmovalec competitor : this.seznamTekmovalcev) {
            System.out.println(competitor);
        }
    }

    public void izpisiGlasove() {

        if (seznamGlasov.isEmpty()) {
            System.out.println("Seznam glasov je prazen.");
            return;
        }

        System.out.println("Seznam glasov:");
        for (Glas vote : this.seznamGlasov) {
            System.out.println(vote);
        }
    }

    public int steviloTock(String drzava) {
        return kriterij.steviloTock(this, drzava);
    }

    public void izpisiTocke() {
        System.out.println("Seznam tekmovalcev in njihovih tock:");
        for (Tekmovalec competition : this.seznamTekmovalcev) {
            System.out.printf("(%s) %s - %s: %dt\n", competition.drzava, competition.tekmovalec, competition.song, steviloTock(competition.drzava));
        }
    }

}

interface Kriterij {
    int steviloTock(Tekmovanje tekmovanje, String drzava);
}

class OsnovniKriterij implements Kriterij {

    @Override
    public int steviloTock(Tekmovanje tekmovanje, String drzava) {
        int sumVotes = 0;
        for (Glas vote : tekmovanje.seznamGlasov) {
            if (vote.getTodrzava().equals(drzava)) {
                sumVotes += vote.getPoints();
            }
        }
        return sumVotes;
    }
}

public class DN09 {
    public static void main(String[] args) {
        if (args.length < 3) {

        } else if (args[0].equals("izpisiTekmovanje")) {
            Tekmovanje t = Tekmovanje.izDatotek(args[1], args[2]);
            assert t != null;
            t.izpisiTekmovalce();
            System.out.println();
            t.izpisiGlasove();
        } else if (args[0].equals("izpisiTocke")) {
            Tekmovanje t = Tekmovanje.izDatotek(args[1], args[2]);
            assert t != null;
            t.izpisiTocke();
        }

    }
}
