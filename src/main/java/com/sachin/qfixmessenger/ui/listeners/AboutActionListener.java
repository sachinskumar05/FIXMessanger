package com.sachin.qfixmessenger.ui.listeners;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sachin.qfixmessenger.ui.FixMessengerFrame;

public class AboutActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory
			.getLogger(AboutActionListener.class);

	private FixMessengerFrame frame;

	public AboutActionListener(FixMessengerFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.0;
		c.ipadx = 2;
		c.ipady = 2;

		JLabel titleLabel = new JLabel("<html><b>FIX Messenger</b></html>");
		JLabel nameLabel = new JLabel("<html><i>by Sachin Kumar</i></html>");
		JLabel emailLabel = new JLabel("<html>sachin.skumar05@gmail.com</html>");
		JLabel webpageLabel = new JLabel(
				"<html><a href=''>FIX-messenger</a></html>");
		webpageLabel.addMouseListener(new LinkMouseAdapter(this, frame
				.getMessenger().getConfig().getHomeUrl()));
		webpageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel licenseLabel = new JLabel("<html><b>License</b></html>");

		JTextArea licenseText = new JTextArea(readLicenseFile(), 15, 60);
		licenseText.setWrapStyleWord(true);
		licenseText.setLineWrap(true);
		licenseText.setEditable(false);

		JScrollPane licenseTextScrollPane = new JScrollPane(licenseText);
		licenseText.setPreferredSize(new Dimension(400, 400));
		licenseTextScrollPane.setBorder(new EtchedBorder());

		c.gridx = 0;
		c.gridy = 0;
		panel.add(titleLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		panel.add(nameLabel, c);

		c.gridx = 0;
		c.gridy = 2;
		panel.add(emailLabel, c);

		c.gridx = 0;
		c.gridy = 3;
		panel.add(webpageLabel, c);

		c.gridx = 0;
		c.gridy = 4;
		panel.add(Box.createRigidArea(new Dimension(50, 10)), c);

		c.gridx = 0;
		c.gridy = 5;
		panel.add(licenseLabel, c);

		c.gridx = 0;
		c.gridy = 6;
		panel.add(licenseTextScrollPane, c);

		JOptionPane.showMessageDialog(frame, panel, "About QuickFIX Messenger",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void goToWikiPage(String url) throws IOException {
		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
	}

	private String readLicenseFile() {
		StringBuilder license = new StringBuilder();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(getClass()
					.getResourceAsStream(
							frame.getMessenger().getConfig()
									.getLicenseLocation())));

			String line = null;
			while ((line = reader.readLine()) != null) {
				license.append(line).append("\n");
			}
		} catch (IOException ex) {
			logger.error("An error occured while reading license file!", ex);
			license.append("An error occured while reading license file!\n");
			license.append("Please refer to log file.");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					logger.error("An error occured"
							+ " while closing license file!", ex);
				}
			}
		}

		return license.toString();
	}

	private static class LinkMouseAdapter extends MouseAdapter {
		private AboutActionListener listener;

		private String url;

		public LinkMouseAdapter(AboutActionListener listener, String url) {
			this.listener = listener;
			this.url = url;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				try {
					listener.goToWikiPage(url);
				} catch (IOException ex) {
					logger.error("An exception occured!", ex);
					JOptionPane.showMessageDialog(
							listener.frame,
							"An exception occured:\n"
									+ Arrays.toString(ex.getStackTrace()),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
