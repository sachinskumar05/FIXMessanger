package com.sachin.qfixmessenger.ui.layers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLayer;
import javax.swing.plaf.LayerUI;

import com.sachin.qfixmessenger.ui.FixMessengerFrame;
import com.sachin.qfixmessenger.ui.util.IconBuilder;

public class FieldValidationLayerUI extends LayerUI<JFormattedTextField>
{
	private static final long serialVersionUID = 8928847899297723141L;

	private FixMessengerFrame frame;

	public FieldValidationLayerUI(FixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		super.paint(g, c);

		JLayer<?> jlayer = (JLayer<?>) c;

		JFormattedTextField jFormattedTextField = (JFormattedTextField) jlayer
				.getView();
		if (jFormattedTextField.getFormatterFactory() != null
				&& !jFormattedTextField.isEditValid())
		{
			Graphics2D g2 = (Graphics2D) g.create();

			// Paint a red X
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			int w = c.getWidth();
			int h = c.getHeight();
			// int pad = 4;
			int s = 10;
			int pad = 10;
			int x = w - pad - s;
			int y = (h - s) / 2;
			ImageIcon icon = IconBuilder
					.build(frame.getMessenger().getConfig(),
							IconBuilder.INVALID_FIELD);
			g2.drawImage(icon.getImage(), x, y, null);
			g2.dispose();
		}
	}
}
