// ----------------------------------------------------------
// Program Name:		FireworksSystem.java
// Course:				CS1302 T-R NIGHT
// Student Name:		David Rodgers
// Assignment Number:	Project 6
// Due Date:			November 29, 2007
// Location:			Southern Polytechnic State University
// ----------------------------------------------------------
// UTILIZES A BASIC PARTICLE SYSTEM TO SIMULATE FIREWORKS
// ----------------------------------------------------------
package fireworks;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.Vector;
import java.util.Random;

public class FireworksSystem
{
	Random ran = new Random();						// Init random
	Vector fireworks;							   // Data structure to hold particles
	Applet app;									 // Define Applet
	int numfireworks;							   // Total # of particles in system
	float gravity = Constants.FIREWORKS_GRAVITY;	// Firework Gravity

	// ----------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------
	public FireworksSystem(Applet a, int i)
	{
		app = a;
		numfireworks = i;
		init();
	}

	// ----------------------------------------------------------
	// INITIALIZE FIREWORKS SYSTEM
	// ----------------------------------------------------------
	public void init()
	{
		int r, g, b;

		// Place starting point for firework in center
		float randPointX = Math.abs(ran.nextInt(301) + 300);

		// Place firework's starting position
		FloatP startPoint = new FloatP(randPointX, 768);

		// Instantiate fireworks array
		fireworks = new Vector();

		// Add fireworks
		for(int counter = 0; counter < numfireworks; counter++)
		{
			fireworks.addElement(new Firework());
		}

		// Console: Which System, Output Coords, # of Particles
		//System.out.println ("Fsys: - Particles: " + fireworks.size() + " - Coords: " + startPoint.x +"  "+ startPoint.y);

		// Assign color value to each firework
		r = 255;
		g = 255;
		b = 255;

		// Choose a random colored particle system, define angle, speed
		// and other elements for each particle in system.
		for(int counter = 0; counter < fireworks.size(); counter++)
		{
			float speed = Math.abs(ran.nextInt(2) + 7);
			float angle = (float)(ran.nextFloat() * (Math.PI / 6) + (Math.PI /2.4));
			
			((Firework)fireworks.elementAt(counter)).init
					// Lifetime, Fadespeed, Start Point
					(15, 0.1f, startPoint,
							// X value for velocity
							new FloatP((float)(speed*Math.cos(angle)),
									// Y value for velocity (must be negative to fly upwards)
									- (float)(speed*Math.sin(angle))), r, g, b);
		}
	}

	// ----------------------------------------------------------
	// UPDATE EACH FIREWORK
	// ----------------------------------------------------------
	public void update()
	{
		// System.out.println("DEBUG: Updating Particles");
		for(int counter = 0; counter < fireworks.size(); counter++)
		{
			((Firework)fireworks.elementAt(counter)).update();
		}

		// Add gravity to particles
		for(int counter = 0; counter < fireworks.size(); counter++)
		{
			((Firework)fireworks.elementAt(counter)).addVelocity(new FloatP(0, gravity));
		}
	}

	// ----------------------------------------------------------
	// DRAW THE FIREWORKS
	// ----------------------------------------------------------
	public void draw(Graphics g)
	{
		//System.out.println("DEBUG: Drawing Particles");
		for(int counter=0; counter < fireworks.size(); counter++)
		{
			((Firework)fireworks.elementAt(counter)).draw(g);
		}
	}

	// ----------------------------------------------------------
	// CHECK STATE OF FIREWORKS
	// ----------------------------------------------------------
	public FloatP isAlive()
	{
		for(int counter = 0; counter < fireworks.size(); counter++)
		{
			// If firework is not alive, send coords for explosion
			if (! ((Firework)fireworks.elementAt(counter)).isAlive() )
			{
				return ((Firework)fireworks.elementAt(counter)).position;
			}
		}
		return null;
	}
}