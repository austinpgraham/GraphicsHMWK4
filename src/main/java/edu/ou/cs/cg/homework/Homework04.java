//******************************************************************************
// Copyright (C) 2016 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Sun Apr  8 12:09:16 2016 by Austin Graham
//******************************************************************************
// Major Modification History:
//
// 201802408 [graham]:	Original file.
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package edu.ou.cs.cg.homework;

import java.util.ArrayList;
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
 * The <CODE>Homework04</CODE> class.<P>
 *
 * @author  Austin Graham
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

	// Collection of possible polygon containers
	static PolygonCollection collection;

	// Initial collection of pointer polygons
	static PolygonCollection bouncers;

	// List of all pointer polygons
	static ArrayList<PolygonCollection> pointers;

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		/* Initialize GL */
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

		// Radius of containers
		float radius =1.0f;

		// Initial radius of pointers
		float pointerRadius = 0.04f;

		// Create the initial set of pointer polygons
		// and container polygons
		collection = new PolygonCollection();
		bouncers = new PolygonCollection();
		bouncers.addPolygon(new Polygon(new Point(0.0f, 0.0f), pointerRadius));
		bouncers.addPolygon(new Polygon(4, new Point(0.0f, 0.0f), pointerRadius, 45f));
		bouncers.addPolygon(new Polygon(8, new Point(0.0f, 0.0f), pointerRadius, 0f));
		bouncers.addPolygon(new DistortedPointerPolygon(5, new Point(0.0f, 0.0f), pointerRadius, 90f));
		collection.addPolygon(new Polygon(4, new Point(0.0f, 0.0f), radius + 0.3f, 45f));
		collection.addPolygon(new Polygon(6, new Point(0.0f,0.0f), radius, 0f));
		collection.addPolygon(new Polygon(32, new Point(0.0f, 0.0f), radius, 0f));
		collection.addPolygon(new DistortedContainerPolygon(10, new Point(0.0f, 0.0f), radius - 0.1f, 54f));

		// Add the initial pointer set
		pointers = new ArrayList<PolygonCollection>();
		pointers.add(bouncers);

		// Add key listener
		EventKeyListener keyList = new EventKeyListener(collection, pointers);
		canvas.addKeyListener(keyList);

		// Add mouse listener
		EventMouseListener mouseList = new EventMouseListener(pointers);
		canvas.addMouseListener(mouseList);

		// Start animator
		FPSAnimator		animator = new FPSAnimator(canvas, 60);
		animator.start();
	}

	//**********************************************************************
	// Override Methods (GLEventListener)
	//**********************************************************************

	public void		init(GLAutoDrawable drawable)
	{
		// Get width and height
		w = drawable.getWidth();
		h = drawable.getHeight();
		
		// Unused text renderer
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
		// For every pointer polygon collection
		for(PolygonCollection pc: pointers)
		{
			// Grab the drawn polygon and update new
			// coordinates
			Polygon pointer = pc.getFocusedPolygon();
			pointer.update();
			// Detect possible collisions
			Vector col_vec = collection.getFocusedPolygon().collision(pointer);
			Vector vel_vec = pointer.center.getPointVector();
			// If collision vector found, calculate the 
			// reflected velocity and set for that pointer
			if(col_vec != null)
			{
				Vector normal = col_vec.getNormal();
				Vector reflected = vel_vec.reflected(normal);
				pointer.setVelocity(reflected);
			}
		}
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		// Draw the focused polygon for every 
		// polygon collection
		for(PolygonCollection pc: pointers)
		{
			pc.getFocusedPolygon().draw(gl);
		}

		// Draw the chosen container
		collection.getFocusedPolygon().draw(gl);
	}
}

//******************************************************************************
