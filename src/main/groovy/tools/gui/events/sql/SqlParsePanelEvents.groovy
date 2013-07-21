/**
 *
 */
package tools.gui.events.sql

import groovy.sql.Sql
import groovy.util.logging.Log4j

import java.awt.event.MouseAdapter
import java.sql.Types

import javax.swing.JComponent
import javax.swing.JOptionPane

import tools.db.DB
import tools.db.OracleDB
import tools.gui.beans.SqlResultModel
import tools.gui.component.sql.SqlParsePanel
import tools.util.SqlLogUtils


/**
 *
 *
 */
@Log4j
class SqlParsePanelEvents {

	public SqlParsePanelEvents(SqlParsePanel panel){
		this.panel = panel
	}

	private SqlParsePanel panel


	/**
	 * SQL���s
	 * @return
	 */
	public void addSqlExeEvent(JComponent target) {
		target.addMouseListener([mouseClicked : {
				try {
					String query = panel.sqlTextArea.getText();
					if (query.length() <= 0) {
						JOptionPane.showMessageDialog(panel, "SQL����ł��B");
						return;
					}
					DB db = new OracleDB()
					List list = db.connect { Sql sql -> sql.rows(query) }
					if (!list) {
						JOptionPane.showMessageDialog(panel, "�������ʂ�0���ł��B�B");
						return;
					}
					SqlResultModel model = new SqlResultModel(list);
					panel.table.setModel(model);
				} catch (Exception e2) {
					log.error(e2.getMessage(), e2);
					JOptionPane.showMessageDialog(panel, e2.getMessage());
					return;
				}
			}] as MouseAdapter);
	}

	/**
	 * ���O���
	 * @return
	 */
	public void addParseSqlEvent(JComponent target) {
		target.addMouseListener([mouseClicked : {
				try {
					String logText = panel.logTextArea.getText();
					if (logText.length() <= 0) {
						JOptionPane.showMessageDialog(panel, "���O����ł��B");
						return;
					}

					SqlLogUtils.storeLogFile(logText);
					String sql = parse(logText);
					panel.sqlTextArea.setText(sql);
					panel.sqlTextArea.requestFocusInWindow();
					panel.sqlTextArea.selectAll();
				} catch (Exception e2) {
					log.error(e2.getMessage(), e2);
					JOptionPane.showMessageDialog(panel, e2.getMessage());
					return;
				}
			}] as MouseAdapter);
	}

	/**
	 * �N���A
	 * @return
	 */
	public void addClearEvent(JComponent target) {
		target.addMouseListener([mouseClicked : {
				panel.logTextArea.setText(null);
				panel.sqlTextArea.setText(null);
				panel.logTextArea.requestFocusInWindow();
			}] as MouseAdapter);
	}


	private parse(File file) {
		def logText = file.getText('Shift_JIS')
		parse(logText)
	}

	private String parse(String logText) {

		// TODO �O���[�v���̎w���JDK1.7�����ł͖��Ή�
		// SQL�ƃp�����[�^�[�̃O���[�v���o�p
		def macther = (logText =~ /(?ms).+start query\(\) (?<sql>.+[^\s])\s*Thread.+end query\(\)(?<params>.+)/)
		String originalSql = null;
		def paramList = [];

		if (macther.matches()) {
			originalSql = macther.group("sql")

			def paramLog = macther.group("params")
			paramLog.eachLine { line ->
				def lineMatcher = (line =~ /.+add param\[(\d+)\] = \[(.+)\] (,columnType = (-?\d+) )?Thread.+/)
				if (lineMatcher.matches()) {
					String param = lineMatcher.group(2)
					def sqlType = null;
					if (lineMatcher.group(4)) {
						sqlType = Integer.parseInt(lineMatcher.group(4))
					}

					switch(sqlType) {
						case Types.VARCHAR:
						case Types.DATE:
						case Types.TIME:
						case Types.TIMESTAMP:
							param = param != 'null' ? "'${param}'" : null
							break;
					}

					paramList += param
					//					println "${lineMatcher.group(0)}\tparam=${param}"
				}
			}
		}

		def paramQueue = paramList as Queue
		def result = originalSql.replaceAll(/(?ms)(\?)/) { paramQueue.poll() }

		return result;
	}

}
