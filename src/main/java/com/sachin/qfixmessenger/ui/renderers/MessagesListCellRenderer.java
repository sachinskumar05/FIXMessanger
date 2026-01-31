package com.sachin.qfixmessenger.ui.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import com.sachin.fix.model.Message;
import com.sachin.fix.model.MessageCategory;

public class MessagesListCellRenderer extends DefaultListCellRenderer
{
	
	 @java.io.Serial
	 private static final long serialVersionUID = -8761695467958549991L;
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
				index, isSelected, cellHasFocus);

		Message message = (Message) value;
		if (message.getCategory() != null)
		{
			if (message.getCategory().equals(MessageCategory.admin))
			{
				label.setForeground(Color.GRAY);
			} else
			{
				label.setForeground(Color.BLUE);
			}
		} else
		{
			label.setText("<html><b><font color = '#669900'>" + label.getText()
					+ "</font><b></html>");
		}

		if (isSelected)
		{
			label.setBackground(Color.LIGHT_GRAY);
			label.setToolTipText("Double-click to look-up in FIXwiki");
		}

		return label;
	}
}



