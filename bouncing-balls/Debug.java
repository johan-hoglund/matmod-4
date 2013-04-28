class Debug
{

	public static void main(String[] arg)
	{
		Coordinate c1 = new Coordinate(1, 0);
		Coordinate c2 = new Coordinate(1, -1);
		Coordinate c3 = new Coordinate(0, -1);
		Coordinate c4 = new Coordinate(-1, -1);
		Coordinate c5 = new Coordinate(-1, 0);
		Coordinate c6 = new Coordinate(-1, 1);
		Coordinate c7 = new Coordinate(0, 1);
		Coordinate c8 = new Coordinate(1, 1);
		Coordinate c9 = new Coordinate(-1.9688, 0.3515);

		System.out.println("x:1, y:0 " + c1);
		System.out.println("x:1, y:-1 " + c2);
		System.out.println("x:0, y:-1 " + c3);
		System.out.println("x:-1, y:-1 " + c4);
		System.out.println("x:-1, y:0 " + c5);
		System.out.println("x:-1, y:1 " + c6);
		System.out.println("x:0, y:1 " + c7);
		System.out.println("x:1, y:1 " + c8);
		System.out.println("x:-1.9688, y:0.3515 " + c9);
	}
}
