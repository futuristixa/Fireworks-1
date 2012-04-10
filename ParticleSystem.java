// ----------------------------------------------------------
// Program Name:		ParticleSystem.java
// Course:				CS1302 T-R NIGHT
// Student Name:		David Rodgers
// Assignment Number:	Project 6
// Due Date:			November 29, 2007
// Location:			Southern Polytechnic State University
// ----------------------------------------------------------
// DEFINES A SYSTEM OF PARTICLES
// ----------------------------------------------------------

package fireworks;

import java.applet.Applet;
import java.util.Vector;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;

public class ParticleSystem
{
	Random ran = new Random();		// Init random
	Vector particles;				// Data structure to hold particles
	Applet app;						// Define Applet
	FloatP st = new FloatP(-1000f, -1000f);
	float gravity = Constants.PARTICLE_GRAVITY;

	int numParticles;				// Total # of particles in system
	int maxParticles = 3000;		// Max # of random particles
	int minParticles =  200;		// Min # of random particles

	Image image;
	Graphics offscreen;

	// ----------------------------------------------------------
	// CONSTRUCTOR
	// ----------------------------------------------------------
	public ParticleSystem(Applet a)
	{
		app = a;
		init2(st);
	}

	// ----------------------------------------------------------
	// INITIALIZE PARTICLE SYSTEM 1
	// ----------------------------------------------------------
	public void init(FloatP pos)
	{
		int r, g, b;

		// Place explosion in top center
		float randPointX = Math.abs((ran.nextInt(app.getBounds().width)));
		float randPointY = Math.abs(ran.nextInt(176) + 20);

		// Place explosion in top center of screen
		FloatP startPoint = new FloatP(pos.x, pos.y);
		
		// Instantiate particle array
		particles = new Vector();

		// Initializes a random # of particles
		numParticles = ran.nextInt(maxParticles - minParticles) + minParticles;

		// Add particles
		for(int counter = 0; counter < numParticles; counter++)
		{
			particles.addElement(new Particle());
		}

		// Console: Which Particle System, Output Coords, # of Particles
		System.out.println ("PSys:1 - Particle Count: " + particles.size() + " - " +
							"Coords: X(" + randPointX + "), " + "Y(" + randPointY + ")");

		// Assign random color value to each particle 0-255. + 75 used to keep particles from being too dark.
		r = ran.nextInt(156) + 75;
		g = ran.nextInt(156) + 75;
		b = ran.nextInt(156) + 75;

		// Choose a random colored particle system, define angle, speed
		// and other elements for each particle in system.
		for(int counter = 0; counter < particles.size(); counter++)
		{
			float speed = ran.nextFloat()*2;

			// Thanks to David Ozouf for the idea of using PI to produce a more rounded look to the explosions
			float angle = (float)(ran.nextFloat()*(2*Math.PI));

			((Particle)particles.elementAt(counter)).init
					// Lifetime, Fade Speed, Start Position
					(15 + ran.nextInt()%10, 0.1f, startPoint,
							// X Velocity
							new FloatP((float)(speed*Math.cos(angle)),
									// Y Velocity
									(float)(speed*Math.sin(angle))), r, g, b);
		}
	}
	
	// ----------------------------------------------------------
	// INITIALIZE PARTICLE SYSTEM 2
	// ----------------------------------------------------------
	public void init2(FloatP pos)
	{
		int r, g, b, r2, g2, b2, choice;

		// Instantiate particle array
		particles = new Vector();

		// Initializes a random # of particles
		int numParticles = ran.nextInt(maxParticles - minParticles) + minParticles;

		// Add particles
		for(int counter = 0; counter < numParticles; counter++)
		{
			particles.addElement(new Particle());
		}

		// Console: Which System, Output Coords
		System.out.println ("PSys:2 - Particle Count: " + particles.size() + " - " +
							"Coords: X(" + pos.x + "), " + "Y(" + pos.y + ")");
/*
		.g.drawString("welcome to java",10,10);
		g.setColor(Color.blue);
		Font f = new Font("Impact",Font.PLAIN, 30);
		g.setFont(f);
		g.drawString("Hello World...",50,50);
*/
		// Define random starting point for explosion
		FloatP startPoint = new FloatP(pos.x, pos.y);

		// Sys1: Assign random color value to each particle 0-255. + 75 used to keep particles from being too dark.
		r = ran.nextInt(156) + 75;
		g = ran.nextInt(156) + 75;
		b = ran.nextInt(156) + 75;

		// Sys2: Assign random color value to each particle 0-255. + 75 used to keep particles from being too dark.
		r2 = ran.nextInt(156) + 75;
		g2 = ran.nextInt(156) + 75;
		b2 = ran.nextInt(156) + 75;

		// Choose a random colored particle system, define angle, speed
		// and other elements for each particle in system.
		// Choose a random colored particle system, define angle, speed
		// and other elements for each particle in system.
		for(int counter = 0; counter < particles.size(); counter++)
		{
			choice = ran.nextInt(2);

			if(choice == 1)
			{
				float speed = ran.nextFloat()*2;
				// Thanks to David Ozouf for the idea of using PI to produce a more rounded look to the explosions
				float angle = (float)(ran.nextFloat()*(2*Math.PI));

				((Particle)particles.elementAt(counter)).init
						// Lifetime, Fadespeed, Start Point
						(15 + ran.nextInt()%10, 0.1f, startPoint,
							// X Velocity
							new FloatP((float)(speed*Math.cos(angle)),
								// Y Velocity
								(float)(speed*Math.sin(angle))), r, g, b);
			}
			else
			{
				float speed = ran.nextFloat()*2;
				// Thanks to David Ozouf for the idea of using PI to produce a more rounded look to the explosions
				float angle = (float)(ran.nextFloat()*(2*Math.PI));

				((Particle)particles.elementAt(counter)).init
						// Lifetime, Fadespeed, Start Point
						(15 + ran.nextInt()%10, 0.1f, startPoint,
							// X Velocity
							new FloatP((float)(speed*Math.cos(angle)),
								// Y Velocity
								(float)(speed*Math.sin(angle))), r2, g2, b2);
			}
		}
	}
		
	// ----------------------------------------------------------
	// INITIALIZE PARTICLE SYSTEM 3
	// ----------------------------------------------------------
	public void init3(FloatP pos)
	{

		int r, g, b, r2, g2, b2, r3, g3, b3, choice;

		// Instantiate particle array
		particles = new Vector();
		
		// Initializes a random # of particles
		numParticles = ran.nextInt(maxParticles - minParticles) + minParticles;

		// Add particles
		for(int counter = 0; counter < numParticles; counter++)
		{
			particles.addElement(new Particle());
		}

		// Console: Which System, Output Coords
		System.out.println ("PSys:3 - Particle Count: " + particles.size() + " - " +
							"Coords: X(" + pos.x + "), " + "Y(" + pos.y + ")");


		// Define random starting point for explosion
		FloatP startPoint = new FloatP(pos.x, pos.y);

		// Sys1: Assign random color value to each particle 0-255. + 75 used to keep particles from being too dark.
		r = ran.nextInt(156) + 75;
		g = ran.nextInt(156) + 75;
		b = ran.nextInt(156) + 75;

		// Sys2: Assign random color value to each particle 0-255. + 75 used to keep particles from being too dark.
		r2 = ran.nextInt(156) + 75;
		g2 = ran.nextInt(156) + 75;
		b2 = ran.nextInt(156) + 75;

		// Sys3: Assign random color value to each particle 0-255. + 75 used to keep particles from being too dark.
		r3 = ran.nextInt(156) + 75;
		g3 = ran.nextInt(156) + 75;
		b3 = ran.nextInt(156) + 75;

		// Choose a random colored particle system, define angle, speed
		// and other elements for each particle in system.
		for(int counter = 0; counter < particles.size(); counter++)
		{
			choice = ran.nextInt(3);

			if(choice == 1)
			{
				float speed = ran.nextFloat()*2;
				// Thanks to David Ozouf for the idea of using PI to produce a more rounded look to the explosions
				float angle = (float)(ran.nextFloat()*(2*Math.PI));
				((Particle)particles.elementAt(counter)).init
						// Lifetime, Fadespeed, Start Point
						(15 + ran.nextInt()%10, 0.1f, startPoint,
							// X Velocity
							new FloatP((float)(speed*Math.cos(angle)),
								 // Y Velocity
								(float)(speed*Math.sin(angle))), r, g, b);
			}
			else if(choice == 2)
			{
				float speed = ran.nextFloat()*2;
				// Thanks to David Ozouf for the idea of using PI to produce a more rounded look to the explosions
				float angle = (float)(ran.nextFloat()*(2*Math.PI));

				((Particle)particles.elementAt(counter)).init
						// Lifetime, Fadespeed, Start Point
						(15 + ran.nextInt()%10, 0.1f, startPoint,
							// X Velocity
							new FloatP((float)(speed*Math.cos(angle)),
								// Y Velocity
								(float)(speed*Math.sin(angle))), r2, g2, b2);
			}
			else
			{
				float speed = ran.nextFloat()*2;
				// Thanks to David Ozouf for the idea of using PI to produce a more rounded look to the explosions
				float angle = (float)(ran.nextFloat()*(2*Math.PI));

				((Particle)particles.elementAt(counter)).init
						// Lifetime, Fadespeed, Start Point
						(15 + ran.nextInt()%10, 0.1f, startPoint,
							// X Velocity
							new FloatP((float)(speed*Math.cos(angle)),
								// Y Velocity
								(float)(speed*Math.sin(angle))), r3, g3, b3);
			}
		}
	}
		
	// ----------------------------------------------------------
	// UPDATE EACH PARTICLE
	// ----------------------------------------------------------
	public void update()
	{
		// System.out.println("DEBUG: Updating Particles");
		for(int counter = 0; counter < particles.size(); counter++)
		{
			((Particle)particles.elementAt(counter)).update();
		}

		// Add gravity to particles
		for(int counter = 0; counter < particles.size(); counter++)
		{
			((Particle)particles.elementAt(counter)).addVelocity(new FloatP(0, gravity));
		}

		// Simulate burn out
		if (((Particle)particles.elementAt(ran.nextInt(particles.size()))).position.y > 300)
		{
			fireworkPattern(0);
		}
	}

	// ----------------------------------------------------------
	// CHOOSE A RANDOM COLOR BURNOUT PATTERN
	// ----------------------------------------------------------
	// WHITE	- R:255	 G:255   B:255
	// YELLOW   - R:255	 G:255   B:0
	// RED	  - R:255	 G:0	 B:0
	// BLUE	 - R:0	   G:0	 B:255
	// PINK	 - R:255	 G:0	 B:255
	// GREEN	- R:0	   G:255   B:0
	// ORANGE   - R:255	 G:90	B:0
	// BLACK	- R:0	   G:0	 B:0
	// ----------------------------------------------------------
	public void fireworkPattern (int pattern)
	{
		if (pattern == 0)
		{
			// RED, ORANGE, GRAY
			for (int sparkAmt = 0; sparkAmt < 150; sparkAmt++)
			{
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = 255;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 90;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 0;

				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = sparkAmt;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 0;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 0;
			}

			for (int sparkAmt = 255; sparkAmt > 0; sparkAmt--)
			{
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = sparkAmt;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 100;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 100;

				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = sparkAmt;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 20;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 20;
			}
		}

		if (pattern == 1)
		{
			// PURPLE, BLUE, RED
			for (int sparkAmt = 0; sparkAmt < 255; sparkAmt++)
			{
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = 255;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 0;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 0;

				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = sparkAmt;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = sparkAmt;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = sparkAmt;
			}

			for (int sparkAmt = 255; sparkAmt > 0; sparkAmt--)
			{
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = sparkAmt;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 100;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 100;

				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = sparkAmt;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 0;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 255;
			}
		}

		if (pattern == 2)
		{
			// GREEN, BLUE
			for (int sparkAmt = 0; sparkAmt < 150; sparkAmt++)
			{
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = 0;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 255;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 0;

				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = 0;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 0;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = sparkAmt;
			}

			for (int sparkAmt = 255; sparkAmt > 0; sparkAmt--)
			{
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = 100;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = sparkAmt;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 255;

				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).redAmnt) = 20;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).greenAmnt) = 20;
				(((Particle)particles.elementAt(ran.nextInt(particles.size()))).blueAmnt) = 20;
			}
		}
	}	

	// ----------------------------------------------------------
	// DRAW THE PARTICLES
	// ----------------------------------------------------------
	public void draw(Graphics g)
	{
		//System.out.println("DEBUG: Drawing Particles");
		for(int counter = 0; counter < particles.size(); counter++)
		{
			((Particle)particles.elementAt(counter)).draw(g);

		}
	}

	// ----------------------------------------------------------
	// CHECK STATE OF PARTICLES
	// ----------------------------------------------------------
	public boolean isAlive()
	{
		for(int counter = 0; counter < particles.size(); counter++)
		{
			if (((Particle)particles.elementAt(counter)).isAlive())
			{
				return true;
			}
		}
		return false;
	}
}