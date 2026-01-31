package com.sachin.qfixmessenger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import com.sachin.fix.xml.MessageType;
import com.sachin.fix.xml.ProjectType;
import com.sachin.qfixmessenger.ui.editors.ProjectTreeCellEditor;
import com.sachin.qfixmessenger.ui.listeners.ProjectTreeMouseListener;
import com.sachin.qfixmessenger.ui.models.ProjectTreeModel;
import com.sachin.qfixmessenger.ui.renderers.ProjectTreeCellRenderer;
import com.sachin.qfixmessenger.ui.util.IconBuilder;

/**
 * ProjectDialog
 * 

 */
public class ProjectDialog extends JDialog
{
	
	 @java.io.Serial
	 private static final long serialVersionUID = -1653220967743151936L;
	private final FixMessengerFrame frame;

	private final ProjectType xmlProjectType;

	private JPanel mainPanel;

	private JTree projectTree;

	public ProjectDialog(FixMessengerFrame frame, ProjectType xmlProjectType)
	{
		this.frame = frame;
		this.xmlProjectType = xmlProjectType;
	}

	/**
	 * Launches the frame
	 */
	public void launch()
	{
		initDialog();
		initComponents();
		positionFrame();
		setVisible(true);
	}

	/**
	 * Reloads the project tree
	 */
	public void reload()
	{
		((ProjectTreeModel) projectTree.getModel()).update();
	}

	/**
	 * Updates the project tree that a message has been added
	 * 
	 * @param xmlMessageType
	 *            an XML Message
	 */
	public void updateMessageAdded(MessageType xmlMessageType)
	{
		((ProjectTreeModel) projectTree.getModel())
				.updateMessageAdded(xmlMessageType);
	}

	private void initComponents()
	{
		setLayout(new BorderLayout());

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JScrollPane mainScrollPane = new JScrollPane();
		mainScrollPane.setPreferredSize(new Dimension(300, 400));
		add(mainScrollPane, BorderLayout.CENTER);

		projectTree = new JTree();
		projectTree.setEditable(true);
		projectTree.setModel(new ProjectTreeModel(xmlProjectType));
		projectTree.setCellRenderer(new ProjectTreeCellRenderer(frame));
		projectTree.setCellEditor(new ProjectTreeCellEditor(projectTree));
		projectTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		projectTree.addMouseListener(new ProjectTreeMouseListener(frame,
				projectTree));
		projectTree.expandRow(1);

		mainScrollPane.getViewport().add(projectTree);

		pack();
	}

	private void initDialog()
	{
		setIconImage(IconBuilder.build(frame.getMessenger().getConfig(),
				IconBuilder.APP_ICON).getImage());
		String title = "Project View";
		if (frame.getMessenger().getConfig().isInitiator())
		{
			title = title + " (Initiator)";
		} else
		{
			title = title + " (Acceptor)";
		}
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/*
	 * Position the frame next to the main frame
	 */
	private void positionFrame()
	{
		int width = getSize().width;
		int height = frame.getSize().height;
		int x = frame.getLocation().x - 310;
		int y = frame.getLocation().y;
		setSize(width, height);
		setLocation((x > 0) ? x : 0, y);
	}
}



