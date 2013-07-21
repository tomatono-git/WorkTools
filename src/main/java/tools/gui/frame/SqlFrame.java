/**
 *
 */
package tools.gui.frame;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import tools.gui.component.sql.LogViewerPanel;
import tools.gui.component.sql.SqlBuildPanel;
import tools.gui.component.sql.SqlParsePanel;

/**
 *
 *
 */
public class SqlFrame extends JFrame {
// private Log log = LogFactory.getLog(BaseSqlLogFrame.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SqlFrame frame = new SqlFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SqlFrame() {
		setTitle("SQL\u30ED\u30B0\u89E3\u6790");
		setBounds(100, 100, 600, 734);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE));

		JPanel panel = new SqlParsePanel();
		tabbedPane.addTab("SQLÉçÉOâêÕ", null, panel, null);
		JPanel panel2 = new LogViewerPanel();
		tabbedPane.addTab("ÉçÉO", null, panel2, null);
		JPanel panel3 = new SqlBuildPanel();
		tabbedPane.addTab("SQLê∂ê¨", null, panel3, null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
	}
}
