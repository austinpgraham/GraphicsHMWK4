//******************************************************************************
// Copyright (C) 2016 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Feb  9 20:33:16 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160209 [weaver]:	Original file.
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package edu.ou.cs.cg.homework;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

//******************************************************************************

/**
 * The <CODE>Homework02</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Homework04
	implements GLEventListener
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	public static final GLU		GLU = new GLU();
	public static final GLUT	GLUT = new GLUT();
	public static final Random	RANDOM = new Random();

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	public int				w;			// Canvas width
	public int				h;			// Canvas height
	private TextRenderer	renderer;

	static Point centralPoint;
	static PolygonCollection collection;

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		GLProfile		profile = GLProfile.getDefault();
		GLCapabilities	capabilities = new GLCapabilities(profile);
		GLCanvas		canvas = new GLCanvas(capabilities);
		JFrame			frame = new JFrame("Homework04");

		frame.setBounds(50, 50, 600, 600);
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

		Homework04 root = new Homework04();
		canvas.addGLEventListener(root);

		float radius =1.0f;

		collection = new PolygonCollection();
		collection.addPolygon(Homework04.getShape(4, new Point(0.0f, 0.0f), radius, 45f));
		collection.addPolygon(Homework04.getShape(6, new Point(0.0f,0.0f), radius, 0f));
		collection.addPolygon(Homework04.getShape(32, new Point(0.0f, 0.0f), radius, 0f));
		Polygon focusedPolygon = collection.getFocusedPolygon();
		centralPoint = new Point(focusedPolygon.center.x, focusedPolygon.center.y);

		EventKeyListener keyList = new EventKeyListener(centralPoint, collection);
		canvas.addKeyListener(keyList);

		FPSAnimator		animator = new FPSAnimator(canvas, 60);

		animator.start();
	}

	//**********************************************************************
	// Override Methods (GLEventListener)
	//**********************************************************************

	public void		init(GLAutoDrawable drawable)
	{
		w = drawable.getWidth();
		h = drawable.getHeight();
		
		renderer = new TextRenderer(new Font("Serif", Font.PLAIN, 18),
									true, true);
	}

	public void		dispose(GLAutoDrawable drawable)
	{
		renderer = null;
	}

	public void		display(GLAutoDrawable drawable)
	{
		update();
		render(drawable);
	}

	public void		reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
	{
		this.w = w;
		this.h = h;
	}

	//**********************************************************************
	// Private Methods (Rendering)
	//**********************************************************************

	private void	update()
	{
		centralPoint.update();
		Vector vel_vec = centralPoint.getPointVector();
		Vector col_vec = collection.getFocusedPolygon().collision(vel_vec);
		if(col_vec != null)
		{
			Vector normal = col_vec.getNormal();
			Vector reflected = vel_vec.reflected(normal);
			centralPoint.setVelocity(reflected);
		}
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		centralPoint.draw(gl);
		collection.getFocusedPolygon().draw(gl);
	}

	public static Polygon getShape(int numPoints, Point center, float radius, float startAngle)
	{
		final float FULL_CIRC = 360f;
		final float RADIUS = radius;
		float skipDegree = FULL_CIRC / numPoints;
		Point[] points = new Point[numPoints];
		int count = 0;
		for(float i = startAngle; i < FULL_CIRC + startAngle; i+= skipDegree)
		{
			double x =  center.getFloatX() + Math.cos(Math.toRadians(i))*RADIUS;
			double y = center.getFloatY() + Math.sin(Math.toRadians(i))*RADIUS;
			Point p = new Point((float)x, (float)y);
			points[count] = p;
			count ++;
		}
		return new Polygon(center, points);
	}
}

//******************************************************************************
