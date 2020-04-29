package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class FractalExplorer {

    private int displaySize;
    private JImageDisplay image;
    private JButton resetButton;
    private FractalGenerator generator;
    private Rectangle2D.Double range;

	public static void main(String[] args) {
		FractalExplorer explorer = new FractalExplorer(800);
		explorer.createAndShowGUI();
		explorer.drawFractal();
	}

	public FractalExplorer(int size) {
		displaySize = size;
		range = new Rectangle2D.Double();
		generator = new Mandelbrot();
		generator.getInitialRange(range);
	}

    private void enableUI(boolean val) {
		resetButton.setEnabled(val);
    }

	public void createAndShowGUI() {
		JFrame frame  = new JFrame("Fractal Explorer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout( new BorderLayout());

		FractalHandler handler = new FractalHandler();

		// Image
		image = new JImageDisplay(displaySize, displaySize);
		frame.getContentPane().add(image, BorderLayout.CENTER);

		// Button
		JPanel buttonsPanel = new JPanel();

		// Reset
		resetButton = new JButton("Reset Display");
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(handler);
		buttonsPanel.add(resetButton);

		frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

		frame.getContentPane().addMouseListener(new MouseHandler());

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void drawFractal() {

		enableUI(false);

		for (int y = 0; y < displaySize; y++) {

			double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height,
					displaySize, y);

			for (int x = 0; x < displaySize; x++) {

				double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
						displaySize, x);
				int numIters;
				int rgbColor = 0;
				float hue;

				numIters = generator.numIterations(xCoord, yCoord);
				if (numIters >= 0) {
					hue = 0.7f + numIters / 200f;
					rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
				}

				image.drawPixel(x, y, rgbColor);

				image.repaint(0, 0, y, displaySize, 1);
			}
		}

		image.repaint();

		enableUI(true);
	}

    private class FractalHandler implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {

			range = new Rectangle2D.Double();
			generator.getInitialRange(range);

			drawFractal();
		}
    }

    private class MouseHandler extends MouseAdapter {
	
		public void mouseClicked(MouseEvent e) {

			double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
					displaySize, e.getX());

			double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height,
					displaySize, e.getY());

			generator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

			drawFractal();
		}
    }
}
