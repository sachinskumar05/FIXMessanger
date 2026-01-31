package com.sachin.qfixmessenger.ui.editors;

import java.util.EventObject;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;

public class ProjectTreeCellEditor extends DefaultTreeCellEditor
{
	public ProjectTreeCellEditor(JTree tree)
	{
		super(tree, null);
	}

	@Override
	public boolean isCellEditable(EventObject event)
	{
		if (super.isCellEditable(event))
		{
			Object value = tree.getLastSelectedPathComponent();
			if (value instanceof String)
			{
				return true;
			}
		}

		return false;
	}
}
