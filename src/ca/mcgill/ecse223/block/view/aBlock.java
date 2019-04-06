package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.JPanel;


import javax.swing.*;
import java.awt.*;

public class aBlock extends Thread {
	
	private int Xi;
	private int Yi;
	private int BLOCKSIZE;
	private int red;
	private int green;
	private int blue;
	
	private JPanel ablock;
	
	public aBlock(int Xi, int Yi, int BLOCKSIZE, int red, int green, int blue) {
			super();
			this.Xi = Xi;
			this.Yi = Yi;
			this.BLOCKSIZE = BLOCKSIZE;
			this.BLOCKSIZE = BLOCKSIZE;

			ablock = new JPanel();
			ablock.setSize(BLOCKSIZE, BLOCKSIZE);
			ablock.setBackground(new Color(red, green, blue));
	}
	
}
