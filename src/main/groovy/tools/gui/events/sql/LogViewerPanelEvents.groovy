/**
*
*/
package tools.gui.events.sql

import groovy.util.logging.Log4j

import java.awt.Desktop
import java.awt.event.MouseAdapter

import javax.swing.JComponent
import javax.swing.JOptionPane
import javax.swing.JTextField

import tools.gui.SqlConfig
import tools.gui.component.sql.LogViewerPanel


/**
*
*
*/
@Log4j
class LogViewerPanelEvents {

   public LogViewerPanelEvents(LogViewerPanel panel) {
	   this.panel = panel
   }

   private LogViewerPanel panel

   void initialize() {
	   panel.sqlLogFileTextField.setText(SqlConfig.instance.sqlLogPath);
	   panel.debugLogFileTextField.setText(SqlConfig.instance.debugLogPath);
	   panel.errorLogFileTextField.setText(SqlConfig.instance.errorLogPath);
   }

   public addOpenLogFolderEvent(JComponent target, JTextField textField) {
	   target.addMouseListener([mouseClicked : {
			   String path = textField.getText();
			   File file = new File(path);
			   openFile(file.getParent());
		   }] as MouseAdapter)
   }

   public void addOpenLogFileEvent(JComponent target, JTextField textField) {
	   target.addMouseListener([mouseClicked : {
			   String path = textField.getText();
			   openFile(path);
		   }] as MouseAdapter)
   }

   /**
	* @param path
	*/
   private boolean openFile(String path) {
	   if (path.length() <= 0) {
		   JOptionPane.showMessageDialog(panel, "ログファイルのパスが指定されていません。");
		   return false;
	   }
	   File file = new File(path);
	   if (!file.exists()) {
		   JOptionPane.showMessageDialog(panel,
				   "ログファイルが見つかりません。File :" + file.getPath());
		   return false;
	   }
	   try {
		   Desktop.getDesktop().open(file);
	   } catch (IOException e1) {
		   log.error(e1.getMessage(), e1);
		   JOptionPane.showMessageDialog(panel, e1.getMessage());
		   return false;
	   }
	   return true;
   }
}
