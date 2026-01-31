package com.sachin.qfixmessenger.ui.util;

import javax.swing.ImageIcon;

import com.sachin.qfixmessenger.config.QFixMessengerConfig;

public final class IconBuilder
{
	public static String APP_ICON = "app.png";

	public static String ADD_ICON = "add.png";
	public static String SEND_ICON = "send.png";
	public static String DESTROY_ICON = "destroy.png";

	public static String NEW_ICON = "new.png";
	public static String OPEN_ICON = "open.png";
	public static String SAVE_ICON = "save.png";
	public static String CLOSE_ICON = "close.png";
	public static String IMPORT_ICON = "import.png";

	public static String EXPORT_ICON = "export.png";
	public static String EXIT_ICON = "exit.png";

	public static String LOGON_ICON = "logon.png";

	public static String LOGOFF_ICON = "logoff.png";
	public static String RESET_ICON = "reset.png";
	public static String HELP_ICON = "help.png";

	public static String WINDOW_ICON = "window.png";

	public static String LOAD_ICON = "load.png";

	public static String DELETE_ICON = "delete.png";
	public static String SEND_SMALL_ICON = "send-small.png";
	public static String SEND_ALL_ICON = "send-all.png";
	public static String COLLAPSE_ICON = "collapse.png";
	public static String EXPAND_ICON = "expand.png";
	public static String PROJECT_ICON = "project.png";

	public static String PROJECT_OPEN_ICON = "project-open.png";
	public static String MESSAGES_ICON = "messages.png";
	public static String MESSAGES_OPEN_ICON = "messages-open.png";
	public static String MESSAGE_ICON = "message.png";
	public static String MESSAGE_OPEN_ICON = "message-open.png";
	public static String SESSION_ICON = "session.png";
	public static String COMPONENT_ICON = "component.png";
	public static String GROUP_ICON = "group.png";
	public static String GROUPS_ICON = "groups.png";
	public static String FIELD_ICON = "field.png";
	public static String TEXT_ICON = "text.png";

	public static String CLEAR_ALL_ICON = "clear-all.png";
	public static String INVALID_FIELD = "invalid-field.png";

	public static ImageIcon build(QFixMessengerConfig config, String path)
	{
		String res = String.format("%s/%s", config.getIconsLocation(), path);
		return new ImageIcon(IconBuilder.class.getResource(res));
	}
}
