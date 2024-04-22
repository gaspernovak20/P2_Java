package DN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Planet {

    public String name;
    public int r;

    public Planet(String name, int radius) {
        this.name = name;
        this.r = radius;
    }

    public double povrsina() {
        return 4 * Math.PI * Math.pow(this.r, 2);
    }

}

public class DN07 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner planetsDoc = new Scanner(new File(args[0]));

        Planet[] planets = new Planet[8];
        String[] planetInfo = new String[2];

        int numOfPlanets = 0;
        while (planetsDoc.hasNextLine()) {
            planetInfo = planetsDoc.nextLine().split(":");

            planets[numOfPlanets] = new Planet(planetInfo[0], Integer.parseInt(planetInfo[1]));
            numOfPlanets++;
        }

        String[] planetsKnown = args[1].split("\\+");
        double planetsSurface = 0;

        for (int j = 0; j < planetsKnown.length; j++) {
            String currentPlanetName = planetsKnown[j].toLowerCase();

            for (int i = 0; i < numOfPlanets; i++) {
                Planet planet = planets[i];

                if (planet.name.toLowerCase().equals(currentPlanetName)) {
                    planetsSurface += planet.povrsina();
                }
            }
        }

        System.out.printf("Povrsina planetov \"%s\" je %3.0f milijonov km2", args[1], planetsSurface / 1000000);

        planetsDoc.close();
    }
}
