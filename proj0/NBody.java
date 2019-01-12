public class NBody {
    private static String starfield = "images/starfield.jpg";

    public static double readRadius(String filename) {
        In in = new In(filename);
        int numPlanet = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numPlanet = in.readInt();
        double radiusUniverse = in.readDouble();
        Planet[] p_array = new Planet[numPlanet];
        for (int i = 0; i < numPlanet; i++) {
            double px = in.readDouble();
            double py = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String name = in.readString();
            p_array[i] = new Planet(px, py, vx, vy, m, name);
        }
        return p_array;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int numPlanet = planets.length;

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, starfield);
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.show();
        StdDraw.pause(1000);

        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T) {
            double[] xForces = new double[numPlanet];
            double[] yForces = new double[numPlanet];
            for (int i = 0; i < numPlanet; i++) {
                Planet p = planets[i];
                xForces[i] = p.calcNetForceExertedByX(planets);
                yForces[i] = p.calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < numPlanet; i++) {
                Planet p = planets[i];
                p.update(dt, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, starfield);
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
