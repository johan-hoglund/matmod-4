import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;


public class Ball
{
	private Coordinate speed, nextSpeed, pos;

	private double r = 50;
	private double m = 10;
	private double gravitation = 0.05;
	private JFrame frame;
	private Color color = Color.blue;

	public Ball(JFrame frame, double mass, double radius, double xcord, double ycord, double xspeed, double yspeed, Color color)
	{
		this.speed = new Coordinate(xspeed, yspeed);
		this.nextSpeed = this.speed.clone();
		this.pos = new Coordinate(xcord, ycord);
		this.frame = frame;
		this.m = (4/3)*Math.PI*Math.pow(radius, 3);
		this.r = radius;
		this.color = color;
	}

	public double getXResistance()
	{
		return 1;
	}

	public double getYResistance()
	{
		return 1;
	}

	private void checkCollission(Ball other)
	{
		if(this != other)
		{
			if(this.pos.distance(other.pos) < this.r + other.r)
			{
/*
				System.out.println("\n\nCOLLISSION between " + this.color + " and " + other.color);
				
				System.out.println("My position: " + this.pos);
				System.out.println("My speed: " + this.speed);
				System.out.println("Other position: " + other.pos);
				System.out.println("Other speed: " + other.speed);
*/

				double collissionAngle = this.pos.angleTo(other.pos);
				
//				System.out.println("Collission angle: " + collissionAngle);

				Coordinate myRotated = this.speed.clone();
				myRotated.rotate(collissionAngle);

				Coordinate otherRotated = other.speed.clone();
				otherRotated.rotate(collissionAngle);
				
//				System.out.println("My speed after rotation " + myRotated);
//				System.out.println("Other speed after rotation " + otherRotated);
				
				double inertia = this.m * myRotated.x() + other.m * otherRotated.x();

				double r = -1*(otherRotated.x() -myRotated.x());
				
//				System.out.println("Collission intertia: " + inertia + ", relative speeds: " + r);


				myRotated.setX((inertia - other.m * r) / (this.m + other.m));

//				System.out.println("My new rotated speed " + myRotated);
				myRotated.rotate(-1*collissionAngle);

//				System.out.println("My new final speed " + myRotated);
				this.nextSpeed = myRotated;
/*
				try
				{
					Thread.sleep(1000*0);
				}
				catch (Exception e)
				{
				}
*/
			}
		}
	}

	public void paint(Graphics2D g2d)
	{
		g2d.setColor(this.color);
		g2d.fillOval((int) Math.round(this.pos.x()-this.r), (int) Math.round(this.pos.y()-this.r), (int) Math.round(2*this.r), (int) Math.round(2*this.r));
	}

	public void prepareTick()
	{
		this.nextSpeed.setY(this.nextSpeed.y()+this.gravitation);
		this.nextSpeed.setY(this.nextSpeed.y()*this.getYResistance());
		this.nextSpeed.setX(this.nextSpeed.x()*this.getXResistance());
		
		for(Ball b : Simulator.balls)
		{
			this.checkCollission(b);
		}

		if(this.pos.x()-this.r < 0)
		{
			this.nextSpeed.setX(this.speed.x()*-1);
		}
		if(this.pos.y()-this.r < 0)
		{
			this.nextSpeed.setY(this.speed.y()*-1);
		}
		if(this.pos.x()+this.r > this.frame.getWidth())
		{
			this.nextSpeed.setX(this.speed.x()*-1);
		}
		if(this.pos.y()+this.r > this.frame.getHeight())
		{
			this.nextSpeed.setY(this.speed.y()*-1);
		}


	}

	public void tick()
	{
		this.speed = this.nextSpeed.clone();

		this.pos.setX(this.pos.x() + this.speed.x());
		this.pos.setY(this.pos.y() + this.speed.y());
	}
}
