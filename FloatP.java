// ----------------------------------------------------------
// Program Name:		FloatP.java
// Course:				CS1302 T-R NIGHT
// Student Name:		David Rodgers
// Assignment Number:	Project 6
// Due Date:			November 29, 2007
// Location:			Southern Polytechnic State University
// ----------------------------------------------------------
// DEFINES A COORDINATE SYSTEM
// ----------------------------------------------------------

package fireworks;

public class FloatP
{
	public float x, y;

	public FloatP()
	{
		x = 0;
		y = 0;
	}

	public FloatP(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public void addVelocity(FloatP p)
	{
		x += p.x;
		y += p.y;
	}

	public int getX() { return (int)x; }
	public int getY() { return (int)y; }
}