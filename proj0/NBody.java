public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int rows = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int rows = in.readInt();
		double radis = in.readDouble();
		Planet[] planets = new Planet[rows];
		for (int i = 0; i < rows ; i++ ) {
			double xx_pos = in.readDouble();
			double yy_pos = in.readDouble();
			double xx_vel = in.readDouble();
			double yy_vel = in.readDouble();
			double mass = in.readDouble();
			String image = in.readString();
			Planet p = new Planet(xx_pos,yy_pos,xx_vel,yy_vel,mass,image);
			planets[i] = p;
		}
		return planets;
	}

	public static void main(String[] args) {
		if (args.length != 3){
			System.out.println("arguments length is not correct!");
		}
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		String imageToDraw = "images/starfield.jpg";
		double time = 0;
		while(time != T){
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0,0,imageToDraw);
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            /** need to calculate the netforce all once rather than immediately update one
             * cause the x,y changes may change the later netforce calculation of other planets*/
            for (int i = 0;i<planets.length;i++) {
                Planet p = planets[i];
                xForces[i] = p.calcNetForceExertedByX(planets);
                yForces[i] = p.calcNetForceExertedByY(planets);
            }

            for (int i = 0;i<planets.length;i++) {
                Planet p = planets[i];
                p.update(dt,xForces[i],yForces[i]);
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
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