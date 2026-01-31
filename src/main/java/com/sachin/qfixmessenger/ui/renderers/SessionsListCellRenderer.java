package com.sachin.qfixmessenger.ui.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import quickfix.Session;

import com.sachin.qfixmessenger.quickfix.util.FixUtil;

public class SessionsListCellRenderer extends DefaultListCellRenderer
{
\t@java.io.Serial
\tprivate static final long serialVersionUID = 3103171726329501169L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
				index, isSelected, cellHasFocus);

		Session session = (Session) value;
		label.setText(FixUtil.getSessionName(session.getSessionID()));

		if (session.isLoggedOn())
		{
			label.setForeground(Color.BLUE);
		} else
		{
			label.setForeground(Color.RED);
		}

		if (isSelected)
		{
			label.setBackground(Color.LIGHT_GRAY);
		}

		return label;
	}
}

