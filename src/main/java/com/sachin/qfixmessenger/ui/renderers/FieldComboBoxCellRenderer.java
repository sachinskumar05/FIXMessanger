package com.sachin.qfixmessenger.ui.renderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import com.sachin.fix.model.FieldValue;
import com.sachin.qfixmessenger.util.StringUtil;

public class FieldComboBoxCellRenderer extends DefaultListCellRenderer
{
	private static final long serialVersionUID = 3395233974501496236L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
				index, isSelected, cellHasFocus);

		FieldValue fieldValue = (FieldValue) value;

		if (!StringUtil.isNullOrEmpty(fieldValue.getEnumValue())
				&& !StringUtil.isNullOrEmpty(fieldValue.getDescription()))
		{
			label.setText("<html><font color='gray'>["
					+ fieldValue.getEnumValue() + "]</font> "
					+ fieldValue.getDescription() + "</html>");
		} else
		{
			label.setText("<html><font color='gray'>[NONE]</font>");
		}

		return label;
	}
}
