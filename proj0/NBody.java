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
		String imageToDraw = "starfiled.jpg";
		StdDraw.setScale(-100, 100);
		StdDraw.clear();
		StdDraw.picture(0, 75, imageToDraw);
		StdDraw.picture(-75, -75, imageToDraw);
		StdDraw.picture(75, -75, imageToDraw);

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		StdDraw.pause(2000);	


}