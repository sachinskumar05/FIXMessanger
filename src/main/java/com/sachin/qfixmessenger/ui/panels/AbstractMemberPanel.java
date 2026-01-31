package com.sachin.qfixmessenger.ui.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sachin.fix.model.Member;
import com.sachin.qfixmessenger.ui.FixMessengerFrame;

public abstract class AbstractMemberPanel<M extends Member, Q, X> extends
		JPanel implements MemberPanel<M, Q, X>
{
	private static final long serialVersionUID = 6904389112190383945L;

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractMemberPanel.class);

	private FixMessengerFrame frame;

	private M member;

	public AbstractMemberPanel(FixMessengerFrame frame, M member)
	{
		this.frame = frame;
		this.member = member;
		setOpaque(true);
	}

	@Override
	public M getMember()
	{
		return member;
	}

	protected FixMessengerFrame getFrame()
	{
		return frame;
	}

	private void goToWikiPage() throws IOException
	{
		String url = frame.getMessenger().getConfig().getFixWikiUrl()
				+ getMember().getName();
		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
	}

	protected static class LinkMouseAdapter extends MouseAdapter
	{
		private AbstractMemberPanel<?, ?, ?> memberPanel;

		public LinkMouseAdapter(AbstractMemberPanel<?, ?, ?> memberPanel)
		{
			this.memberPanel = memberPanel;
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 2)
			{
				try
				{
					memberPanel.goToWikiPage();
				} catch (IOException ex)
				{
					logger.error("An exception occured!", ex);
					JOptionPane.showMessageDialog(
							memberPanel,
							"An exception occured:\n"
									+ Arrays.toString(ex.getStackTrace()),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
