// ----------------------------------------------------------
// Program Name:		Firework.java
// Course:				CS1302 T-R NIGHT
// Student Name:		David Rodgers
// Assignment Number:	Project 6
// Due Date:			November 29, 2007
// Location:			Southern Polytechnic State University
// ----------------------------------------------------------
// DEFINES A PARTICLE
// ----------------------------------------------------------

package fireworks;

import java.awt.*;
import java.util.Random;

public class Firework
{
	Random ran = new Random();
	Color color;
	int redAmnt, greenAmnt, blueAmnt;
	float rAmnt, gAmnt, bAmnt;

	boolean alive;
	float lifeSpan, currentLife;
	float fadeSpeed;
	ParticleSystem pSys;

	public FloatP position = new FloatP();
	FloatP velocity;
	int pSize = Constants.FIREWORKS_SIZE;


	public Firework()
	{}

	public Firework(float lS, float fS, FloatP pos, FloatP vel, int r, int g, int b)
	{
		init(lS, fS, pos, vel, r, g, b);
	}

	public void init(float lS, float fS, FloatP pos, FloatP vel, int r, int g, int b)
	{
		alive = true;
		lifeSpan = lS;
		currentLife = lS;
		fadeSpeed = fS;

		position.x = pos.x;
		position.y = pos.y;
		velocity = vel;

		redAmnt = r;
		greenAmnt = g;
		blueAmnt = b;
	}

	public void update()
	{
		if(!alive) return;

		rAmnt = redAmnt	 * (currentLife / lifeSpan);
		gAmnt = greenAmnt   * (currentLife / lifeSpan);
		bAmnt = blueAmnt	* (currentLife / lifeSpan);

		currentLife -= fadeSpeed;

		if(currentLife < 0)
		{
			alive = false;
		}

		// If the firework is headed off screen upwards, to the left, or to the right then
		// kill the firework
		if(position.x >= 924 || position.x <= 100 || position.y <=100)
		{
			alive = false;
		}

		color = new Color((int)rAmnt, (int)gAmnt, (int)bAmnt);

		// Adds Gravity
		position.addVelocity(velocity);
	}

	public void draw(Graphics g)
	{
		if(alive)
		{
			g.setColor(color);
			g.fillRect(position.getX(), position.getY(), pSize, pSize);
		}
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void addVelocity(FloatP fP)
	{
		velocity.addVelocity(fP);
	}
}