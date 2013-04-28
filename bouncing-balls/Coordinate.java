public class Coordinate
{
	private double x,y;
	public Coordinate(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double x()
	{
		return this.x;
	}

	public double y()
	{
		return this.y;
	}

	public void setX(double x)
	{
		this.x = x;
	}


	public void setY(double y)
	{
		this.y = y;
	}

	public Coordinate clone()
	{
		return new Coordinate(this.x, this.y);
	}

	public double angleTo(Coordinate other)
	{
		Coordinate diff = new Coordinate(other.x - this.x, this.y - other.y);
		return diff.angle();
	}

	public double absolute()
	{
		if(this.x != 0 || this.y != 0)
		{
			return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		}
		return 0;
	}

	public void rotate(double deg)
	{
		if(deg == 0)
		{
			return;
		}

//		System.out.println("\nRotating " + deg + " degrees");
//		System.out.println(this);
		double abs = this.absolute();

		// This uglyness stems from the idea of having negative signs on 
		// one side of the unit circle... 
		double newDegree = this.angle() + deg;
//		System.out.println("New degree candidate: " + newDegree);
		if(newDegree < -1*Math.PI)
		{
			newDegree += Math.PI*2;
		}
		if(newDegree > Math.PI)
		{
			newDegree -= Math.PI*2;
		}

//		System.out.println("Target rotation: " + newDegree + ", sinus for angle: " + Math.sin(newDegree));

		this.y = Math.sin(newDegree)*abs;
		this.x = Math.cos(newDegree)*abs;
		
//		System.out.println(this);
//		System.out.println("Rotation done \n");
	}

	public String toString()
	{
		return "x: " + this.x + ", y: " + this.y + ", angle: " + this.angle() + ", abs: " + this.absolute();
	}

	public double angle()
	{
		if(this.absolute() == 0)
		{
			return 0;
		}
		else if(x < 0 && y < 0)
		{
			return Math.asin(-1*this.y / this.absolute()) - Math.PI;
		}
		else if(x < 0 && y == 0)
		{
			return -1*Math.PI;
		}
		else if(x < 0 && y > 0)
		{
			return Math.PI - Math.asin(this.y / this.absolute());
		}
		else
		{
			return Math.asin(this.y / this.absolute());
		}
	}

	public double distance(Coordinate other)
	{
		return Math.sqrt(Math.pow(this.x - other.x, 2)+Math.pow(this.y-other.y,2));
	}


}
