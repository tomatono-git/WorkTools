/**
 *
 */
package tools.gui.component.sql;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import tools.gui.events.sql.SqlBuildPanelEvents;

/**
 *
 *
 */
public class SqlBuildPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField varNameTextField;
	private JTextArea srcSqlTextArea;
	private JTextArea sqlStringTextArea;

	/**
	 * Create the panel.
	 */
	public SqlBuildPanel() {

		SqlBuildPanelEvents events = new SqlBuildPanelEvents(this);

		JLabel label = new JLabel("\u5909\u6570\u540D");

		JLabel label_1 = new JLabel("SQL");

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		varNameTextField = new JTextField();
		varNameTextField.setText("sb");
		varNameTextField.setColumns(10);

		JButton createSqlStringbutton = new JButton("\u751F\u6210");
		events.addCreateSqlStringEvent(createSqlStringbutton);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
							.addGap(5)
							.addComponent(varNameTextField, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(412))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(263)
							.addComponent(createSqlStringbutton, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addGap(235))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)))
					.addGap(17))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(label))
						.addComponent(varNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(createSqlStringbutton)
							.addGap(6)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_1)))
					.addContainerGap(54, Short.MAX_VALUE))
		);

		sqlStringTextArea = new JTextArea();
		scrollPane.setViewportView(sqlStringTextArea);

		srcSqlTextArea = new JTextArea();
		scrollPane_1.setViewportView(srcSqlTextArea);
		setLayout(groupLayout);

	}
}
