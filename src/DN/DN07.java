package DN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Planet {

    private String name;
    private int r;

    public Planet(String name, int radius) {
        this.name = name;
        this.r = radius;
    }

    public String getName() {
        return this.name;
    }

    public double povrsina() {
        return 4 * Math.PI * Math.pow(this.r, 2);
    }

}

public class DN07 {

    static Planet[] readPlanets(String planetDoc) {
        try {
            Scanner planetsDoc = new Scanner(new File(planetDoc), "UTF-8");

            Planet[] planets = new Planet[8];

            int numOfPlanets = 0;
            while (planetsDoc.hasNextLine()) {
                String[] planetInfo = planetsDoc.nextLine().split(":");

                planets[numOfPlanets++] = new Planet(planetInfo[0], Integer.parseInt(planetInfo[1]));
            }
            planetsDoc.close();

            return planets;
        } catch (Exception e) {
            return new Planet[0];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Planet[] planets = readPlanets(args[0]);

        String[] planetsKnown = args[1].split("\\+");
        double planetsSurface = 0;

        for (int j = 0; j < planetsKnown.length; j++) {
            String currentPlanetName = planetsKnown[j].toLowerCase();

            for (int i = 0; i < planets.length; i++) {
                Planet planet = planets[i];

                if (planet.getName().equalsIgnoreCase(currentPlanetName)) {
                    planetsSurface += planet.povrsina();
                }
            }
        }

        System.out.printf("Povrsina planetov \"%s\" je %3.0f milijonov km2", args[1], planetsSurface / 1000000);

    }
}
