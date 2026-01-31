package com.sachin.qfixmessenger.ui.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.sachin.qfixmessenger.quickfix.FixMessageListener;
import com.sachin.qfixmessenger.ui.models.MessagesTableModel;
import com.sachin.qfixmessenger.ui.models.data.MessagesTableModelData;

public class MessagesTableCellRender extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = -5031829092122831674L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		JLabel component = (JLabel) super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);

		if (!isSelected)
		{
			int modelRow = table.convertRowIndexToModel(row);
			MessagesTableModel model = (MessagesTableModel) table.getModel();
			MessagesTableModelData data = model.getData(modelRow);

			if (data.getDirection().equals(FixMessageListener.RECV))
			{
				component.setBackground(Color.ORANGE);
			} else
			{
				component.setBackground(Color.GREEN);
			}
		}

		return component;
	}
}
