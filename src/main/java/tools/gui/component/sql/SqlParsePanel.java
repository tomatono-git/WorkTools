/**
 *
 */
package tools.gui.component.sql;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import tools.gui.events.sql.SqlParsePanelEvents;
import tools.util.SqlLogUtils;

/**
 *
 *
 */
public class SqlParsePanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextArea sqlTextArea;
	private JTextArea logTextArea;

	/**
	 * Create the panel.
	 */
	public SqlParsePanel() {

		SqlParsePanelEvents events = new SqlParsePanelEvents(this);

		JButton clearButton = new JButton("\u30AF\u30EA\u30A2");
		events.addClearEvent(clearButton);

		JButton parseButton = new JButton("\u89E3\u6790");
		events.addParseSqlEvent(parseButton);

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		JButton sqlExeButton = new JButton("SQL\u5B9F\u884C");
		events.addSqlExeEvent(sqlExeButton);

		JScrollPane scrollPane_2 = new JScrollPane();

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(table);

		sqlTextArea = new JTextArea();
		scrollPane_1.setViewportView(sqlTextArea);

		logTextArea = new JTextArea();
		scrollPane.setViewportView(logTextArea);
		if (SqlLogUtils.existsLogFile()) {
			String log = SqlLogUtils.loadLogFile();
			logTextArea.setText(log);
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(176)
							.addComponent(clearButton, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addGap(53)
							.addComponent(parseButton, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addGap(212))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(242)
							.addComponent(sqlExeButton, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
							.addGap(234))
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE))
					.addGap(72))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(clearButton)
						.addComponent(parseButton))
					.addGap(6)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(sqlExeButton)
					.addGap(6)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(85, Short.MAX_VALUE))
		);

		setLayout(groupLayout);
	}
}
