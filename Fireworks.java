// ----------------------------------------------------------
// Program Name:		Fireworks.java
// Course:				CS1302 T-R NIGHT
// Student Name:		David Rodgers
// Assignment Number:	Project 6
// Due Date:			November 29, 2007
// Location:			Southern Polytechnic State University
// ----------------------------------------------------------
// UTILIZES A BASIC PARTICLE SYSTEM TO SIMULATE FIREWORKS
// ----------------------------------------------------------

package fireworks;

import java.applet.Applet; // Runs as an applet
import java.awt.AWTEvent;  // Advanced Windowing Toolkit
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.awt.Font;
import java.awt.Color;

public class Fireworks extends Applet implements Runnable
{
	static String version = Constants.VERSION_NUM;
	static Frame frame;
	volatile Thread animation;

	boolean isStandalone = false;
	int scrnWidth, scrnHeight, FChoice;
	int refreshRate = Constants.REFRESH_RATE;

	Image image;
	Graphics offscreen;
	ParticleSystem pSys;
	FireworksSystem fSys;
	Random ran = new Random();

	// ----------------------------------------------------------
	// INITIALIZES THE APPLET
	// ----------------------------------------------------------
	public void init()
	{
		try
		{
			// Initialize components
			compInit();

			// Creates a particle system
			pSys = new ParticleSystem(this);

			// Creates a firework
			fSys = new FireworksSystem(this, 1);
		}

		catch(Exception excep)
		{
			//System.out.println(">> FAILURE in Function(init) <<");
			//excep.printStackTrace();
		}
	}
	
	// ----------------------------------------------------------
	// INITIALIZES APPLET COMPONENTS
	// ----------------------------------------------------------
	private void compInit()
	{
		// Initialize Applet Parameters
		setBackground(Color.black);							// Screen background
		scrnWidth = getBounds().width;						// Screen X dimension
		scrnHeight = getBounds().height;					// Screen Y dimension
		image = createImage(scrnWidth, scrnHeight);		 // Offscreen buffer
		offscreen = image.getGraphics();					// Assign offscreen buffer contents

		// Show Introduction
		System.out.println ("Java Fireworks :: A simple particle system renderer :: SPSU Fall 2007");
		System.out.println ("Coded by :: David Rodgers :: drodger2@spsu.edu");
		System.out.println ("Screen Dimension (" + scrnWidth + "x" + scrnHeight + ") :: Version: " + version + "\n"); 
	}
	
	// ----------------------------------------------------------
	// UPDATE APPLET
	// ----------------------------------------------------------
	public void update(Graphics g)
	{
		try
		{
			draw(g);  // Draw
		}

		catch (Exception excep)
		{
			//System.out.println(">> FAILURE in Function(update) <<");
			//excep.printStackTrace();
		}
	}

	// ----------------------------------------------------------
	// START APPLET
	// ----------------------------------------------------------
	public void start()
	{
		animation = new Thread(this);   // Create A Thread
		animation.start();			  // Start Thread
	}
	
	// ----------------------------------------------------------
	// RUN APPLET
	// ----------------------------------------------------------
	public void run()
	{
		while(true)
		{
			repaint();				  // Redraw frame
			pSys.update();			  // Update particle system
			fSys.update();			  // Update firwork system

			if(fSys.isAlive() != null)
			{
				if(FChoice == 1)
				{ pSys.init(fSys.isAlive()); }		// Use particle system 1

				else if (FChoice == 2)
				{ pSys.init2(fSys.isAlive()); }	   // Use particle system 2

				else
				{ pSys.init3(fSys.isAlive()); }	   // Use particle system 3

				// Alternate random and centered explosions based on 3 types defined in ParticleSystem.java
				// For a 1, 2, 3 sequence replace "ran.nextInt(3);" with "= 1;"
				FChoice += 1;
				if (FChoice > 3) { FChoice = ran.nextInt(3); }

				// Create a another firework
				fSys.init();
			}

			try
			{
				Thread.sleep(refreshRate);  // Wait
			} 

			catch(Exception excep)
			{
				//System.out.println(">> FAILURE in Function(Run) <<");
				//excep.printStackTrace();
			}
		}
	}

	// ----------------------------------------------------------
	// MAIN
	// ----------------------------------------------------------
	public static void main (String[] args)
	{
		Fireworks app = new Fireworks();
		app.isStandalone = true;

		frame = new Frame()
		{
			// Process window event
			protected void processWindowEvent(WindowEvent e)
			{
				super.processWindowEvent(e);

				if(e.getID() == WindowEvent.WINDOW_CLOSING)
				{
					System.exit(0);
				}
			}

			// Set display frame title
			public synchronized void setTitle(String title)
			{
				super.setTitle(title);
				enableEvents(AWTEvent.WINDOW_EVENT_MASK);
			}
		};

		// Configure display frame (for app version)
		frame.setTitle("Fireworks " + version + " :: David Rodgers :: SPSU CS1302 :: Fall 2007");
		frame.add(app, BorderLayout.CENTER);
		frame.setVisible(true);

		// Set size of display frame
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		//frame.setSize(1024, 768); // hardcode resolution (app version)
		frame.setSize(res);


		// Initialize applet
		app.init();
		app.start();
		app.getAppletInfo();
		System.out.println(">> Fireworks " + version + " Start <<");

		// Center Applet In Frame
		frame.setLocation((res.width - frame.getSize().width) / 2, (res.height - frame.getSize().height) / 2);
	}
	
	// ----------------------------------------------------------
	// DRAW THE APPLET IN THE FRAME
	// ----------------------------------------------------------
	public void draw(Graphics g)
	{
			offscreen.setColor(Color.black);				// Set background color (Comment for random)
			offscreen.fillRect(0,0, scrnWidth, scrnHeight); // Clear screen buffer (Comment for pixel trails)
			pSys.draw(offscreen);						   // Draw particle system in offscreen buffer
			fSys.draw(offscreen);						   // Draw fireworks system in offscreen buffer

			// On-Screen Info
			offscreen.setColor(Color.darkGray);
			offscreen.drawString ("Java Fireworks " + version + " :: A simple particle system renderer :: SPSU Fall 2007", 5,15);
			offscreen.drawString ("Coded by David Rodgers :: drodger2@spsu.edu", 5, 30);
			offscreen.drawString ("Resolution (" + scrnWidth + "x" + scrnHeight + ") :: Particle Count (" + pSys.numParticles + ")", 5, 45);
			
			g.drawImage(image, 0, 0, this);				 // Draw buffer to screen
	 }
}