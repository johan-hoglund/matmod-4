import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;


public class Simulator extends JPanel
{
	public static ArrayList<Ball> balls;

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for(Ball b : Simulator.balls)
		{
			b.paint(g2d);
		}
	}


	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Bouncing balls");
		frame.setSize(250, 250);
		frame.setVisible(true);

		JPanel panel = new Simulator();
		frame.add(panel);

		Simulator.balls = new ArrayList<Ball>();

		Simulator.balls.add(new Ball(frame, 10, 150, 200, 200, 2, -0.7, Color.black));
		Simulator.balls.add(new Ball(frame, 10, 250, 400, 700, 3, -3, Color.red));
		Simulator.balls.add(new Ball(frame, 10, 75, 600, 200, 2, 3, Color.green));
		Simulator.balls.add(new Ball(frame, 10, 100, 800, 600, 5, -0.7, Color.blue));
		Simulator.balls.add(new Ball(frame, 10, 100, 800, 100, 2, -4, Color.orange));


/*
Simulator.balls.add(new Ball(frame, 10, 30, 40, 250, 1, 0, Color.red));
		Simulator.balls.add(new Ball(frame, 5, 20, 500, 70, -1, 0, Color.blue));
		Simulator.balls.add(new Ball(frame, 10, 80, 600, 150, 0.1, 0.7, Color.green));
		Simulator.balls.add(new Ball(frame, 10, 80, 20, 60, 0.1, 0.7, Color.black));
		Simulator.balls.add(new Ball(frame, 10, 20, 40, 20, 1, -0.7, Color.orange));
		Simulator.balls.add(new Ball(frame, 100, 200, 130, 80, 1, -0.7, Color.pink));
		Simulator.balls.add(new Ball(frame, 5, 200, 30, 150, 1, -0.7, Color.yellow));
		Simulator.balls.add(new Ball(frame, 40, 150, 300, 50, 3, 2, Color.cyan));
		Simulator.balls.add(new Ball(frame, 10, 150, 30, 200, 2, -0.7, Color.gray));
		Simulator.balls.add(new Ball(frame, 10, 200, 600, 800, 0, -5, Color.magenta));
		Simulator.balls.add(new Ball(frame, 10, 100, 400, 800, 0, -5, Color.black));
*/


		while(true)
		{
			try
			{
				for(Ball b : Simulator.balls)
				{
					b.prepareTick();
				}
				for(Ball b : Simulator.balls)
				{
					b.tick();
				}

				Thread.sleep(15);
				frame.repaint();
			}
			catch(InterruptedException e)
			{

			}
		}
	}
}
