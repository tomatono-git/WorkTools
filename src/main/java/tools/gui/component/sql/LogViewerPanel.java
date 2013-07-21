/**
 *
 */
package tools.gui.component.sql;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.gui.events.sql.LogViewerPanelEvents;

/**
 *
 *
 */
public class LogViewerPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField sqlLogFileTextField;
	private JTextField debugLogFileTextField;
	private JTextField errorLogFileTextField;

	/**
	 * Create the panel.
	 */
	public LogViewerPanel() {

		LogViewerPanelEvents events = new LogViewerPanelEvents(this);

		sqlLogFileTextField = new JTextField();
		sqlLogFileTextField.setColumns(10);

		debugLogFileTextField = new JTextField();
		debugLogFileTextField.setColumns(10);

		errorLogFileTextField = new JTextField();
		errorLogFileTextField.setColumns(10);

		events.initialize();

		JButton sqlLogButton = new JButton("SQL\u30ED\u30B0\u3092\u958B\u304F");
		events.addOpenLogFileEvent(sqlLogButton, sqlLogFileTextField);

		JButton debugLogButton = new JButton("\u30C8\u30EC\u30FC\u30B9\u30ED\u30B0\u3092\u958B\u304F");
		events.addOpenLogFileEvent(debugLogButton, debugLogFileTextField);

		JButton errorLogButton = new JButton("\u30A8\u30E9\u30FC\u30ED\u30B0\u3092\u958B\u304F");
		events.addOpenLogFileEvent(errorLogButton, errorLogFileTextField);

		JButton logFolderButton = new JButton("\u30ED\u30B0\u30D5\u30A9\u30EB\u30C0\u3092\u958B\u304F");
		events.addOpenLogFolderEvent(logFolderButton, sqlLogFileTextField);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(sqlLogFileTextField, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(sqlLogButton, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(errorLogFileTextField, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(errorLogButton, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addGap(8))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(debugLogFileTextField, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(debugLogButton, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(170)
							.addComponent(logFolderButton, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
							.addGap(162)))
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(sqlLogFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(sqlLogButton))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(errorLogFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(errorLogButton))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(debugLogFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(debugLogButton))
					.addGap(6)
					.addComponent(logFolderButton)
					.addContainerGap(201, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
