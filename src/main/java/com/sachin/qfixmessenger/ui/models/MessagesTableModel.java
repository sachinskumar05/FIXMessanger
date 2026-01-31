package com.sachin.qfixmessenger.ui.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.SessionID;
import quickfix.field.MsgType;

import com.sachin.qfixmessenger.quickfix.FixMessageListener;
import com.sachin.qfixmessenger.quickfix.util.FixUtil;
import com.sachin.qfixmessenger.ui.models.data.MessagesTableModelData;

/**
 * Represents a table for model for messages
 * 

 */
public class MessagesTableModel extends AbstractTableModel implements
		FixMessageListener
{
	private static final Logger logger = LoggerFactory
			.getLogger(MessagesTableModel.class);

	private static final long serialVersionUID = 3045456639720725016L;

	private final List<MessagesTableModelData> tableData = new ArrayList<MessagesTableModelData>();

	/**
	 * Adds a message to the table
	 * 
	 * @param rowData
	 *            a message data
	 */
	public void addRow(MessagesTableModelData rowData)
	{
		int row = tableData.size();
		tableData.add(rowData);
		fireTableRowsInserted(row, row);
	}

	/**
	 * Clears the messages table
	 */
	public void clear()
	{
		tableData.clear();
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int c)
	{
		Object object = getValueAt(0, c);
		if (object != null)
		{
			return object.getClass();
		}

		return null;
	}

	@Override
	public int getColumnCount()
	{
		return MessagesTableModelData.COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int col)
	{
		return MessagesTableModelData.COLUMN_NAMES[col];
	}

	public MessagesTableModelData getData(int row)
	{
		return tableData.get(row);
	}

	@Override
	public int getRowCount()
	{
		return tableData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		if (!tableData.isEmpty())
		{
			MessagesTableModelData rowData = tableData.get(rowIndex);

			return rowData
					.getColumData(MessagesTableModelData.COLUMN_NAMES[columnIndex]);
		}

		return null;
	}

	@Override
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}

	@Override
	public void onMessage(String direction, Message message, SessionID sessionId)
	{
		try
		{
			MsgType msgType = (MsgType) message.getHeader().getField(
					new MsgType());
			MessagesTableModelData data = new MessagesTableModelData(
					new Date(), direction, FixUtil.getSessionName(sessionId),
					message.toString(), msgType.getValue());
			addRow(data);
		} catch (FieldNotFound ex)
		{
			logger.error("An exception occured!", ex);
		}
	}
}
