/**
 *
 */
package tools.gui.events.sql

import groovy.util.logging.Log4j

import java.awt.event.MouseAdapter

import javax.swing.JComponent
import javax.swing.JOptionPane

import org.codehaus.groovy.tools.Utilities

import tools.gui.component.sql.SqlBuildPanel
import tools.util.SqlLogUtils

/**
 *
 *
 */
@Log4j
class SqlBuildPanelEvents {

	public SqlBuildPanelEvents(SqlBuildPanel panel) {
		this.panel = panel
	}

	private SqlBuildPanel panel

	/**
	 * SQLï∂éöóÒê∂ê¨ÅiJavaÅj
	 * @return
	 */
	public MouseAdapter addCreateSqlStringEvent(JComponent component) {
		component.addMouseListener([mouseClicked : {
				try {
					String sql = panel.srcSqlTextArea.getText();
					sql = SqlLogUtils.expand(sql, 4);
					if (sql.length() <= 0) {
						JOptionPane.showMessageDialog(panel, "SQLÇ™ãÛÇ≈Ç∑ÅB");
						return;
					}
					String varName = panel.varNameTextField.getText();
					String buff = createBuildSql(sql, varName);
					panel.sqlStringTextArea.setText(buff);
				} catch (Exception e) {
					log.error(e.getMessage(), e)
					JOptionPane.showMessageDialog(panel, e.getMessage());
				}
			}] as MouseAdapter);
	}

	private String createBuildSql(String sql, String varName) {
		varName = varName ?: "sb"
		def buff = [];
		def result = sql.eachLine { line ->
			if (!line) return;
			def m = (line =~ /(\w+)(\/\*(\w+)\*\/)/)
			if (m) {
				def text = m.replaceAll(/#@$3#$2/)
				def appendList = text.split('#').collect{
					def val;
					if (it =~ /^@/) {
						val = it.replaceFirst(/^@/, '')
					} else {
						val = /"$it"/
					}
					/append(${val})/
				}
				text = "${varName}.${appendList.join('.')}.appendLine();"
				buff << text
			} else {
				buff << /${varName}.append("$line").appendLine();/
			}
		}
		buff.add(0, 'SQLBuilder sb = new SQLBuilder();')
		return buff.join(Utilities.eol());
	}
}
