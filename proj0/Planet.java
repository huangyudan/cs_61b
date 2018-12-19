public class Planet{
	public static double g = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, 
				  double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}	

	public double calcDistance(Planet p){
		double delta_x = xxPos - p.xxPos;
		double delta_y = yyPos - p.yyPos;
		return Math.sqrt(delta_x * delta_x + delta_y * delta_y);
	}

	public double calcForceExertedBy(Planet p){
		double distance = calcDistance(p);
		double force = g * p.mass * mass / (distance * distance);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double force = calcForceExertedBy(p);
		double distance = calcDistance(p);
		double delta_x = p.xxPos - xxPos;
		double force_x = force * delta_x / distance;
		return force_x;
	}

	public double calcForceExertedByY(Planet p){
		double force = calcForceExertedBy(p);
		double distance = calcDistance(p);
		double delta_y = p.yyPos - yyPos;
		double force_y = force * delta_y / distance;
		return force_y;
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double netforce_x = 0.0;
		for (Planet p : planets) {
			if (this.equals(p)) continue;
			netforce_x += calcForceExertedByX(p);
		}
		return netforce_x;
	}

	public double calcNetForceExertedByY(Planet[] planets){
		double netforce_y = 0.0;
		for (Planet p : planets) {
			if (this.equals(p)) continue;
			netforce_y += calcForceExertedByY(p);
		}
		return netforce_y;
	}

	public void update(double dt,double fx, double fy){
		xxVel = xxVel + fx / mass * dt;
		yyVel = yyVel + fy / mass * dt;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}
}