package com.sachin.qfixmessenger.ui.util;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class TitledBorderUtil
{
	/**
	 * Formats the title of a given TitledBorder
	 * <p>
	 * Workaround for Java Bug ID: 7022041
	 * </p>
	 * 
	 * @param titledBorder
	 *            a TitledBorder
	 * @return the given TitledBorder with a formatted title
	 */
	public static TitledBorder formatTitle(TitledBorder titledBorder)
	{
		Font titleBorderFont = UIManager.getDefaults().getFont(
				"TitledBorder.font");
		if (titleBorderFont != null)
		{
			titledBorder.setTitleFont(new Font(titleBorderFont.getName(),
					Font.BOLD, titleBorderFont.getSize()));
		}

		return titledBorder;
	}
}
