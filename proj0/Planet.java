public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        double m1 = this.mass;
        double m2 = p.mass;
        return (G*m1*m2)/(r*r);
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return F*dx/r;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return F*dy/r;
    }

    public double calcNetForceExertedByX(Planet[] p_array) {
        double netForceX = 0;
        for (Planet p : p_array) {
            if (!this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] p_array) {
        double netForceY = 0;
        for (Planet p : p_array) {
            if (!this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double Fx, double Fy) {
        double ax = Fx/this.mass;
        double ay = Fy/this.mass;
        double vx = this.xxVel + dt*ax;
        double vy = this.yyVel + dt*ay;
        double px = this.xxPos + dt*vx;
        double py = this.yyPos + dt*vy;
        this.xxPos = px;
        this.yyPos = py;
        this.xxVel = vx;
        this.yyVel = vy;
    }

    public void draw() {
        double px = this.xxPos;
        double py = this.yyPos;
        String name = this.imgFileName;
        StdDraw.picture(px, py, "images/"+name);
    }
}
