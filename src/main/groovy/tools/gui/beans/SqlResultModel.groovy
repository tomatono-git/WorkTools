/**
 *
 */
package tools.gui.beans

import groovy.sql.GroovyRowResult
import groovy.transform.TypeChecked

import javax.swing.table.AbstractTableModel

/**
 *
 *
 */
@TypeChecked
class SqlResultModel extends AbstractTableModel {

	/**
	 * コンストラクタ
	 * @param resultList
	 */
	public SqlResultModel(List resultList) {
		resultList.eachWithIndex { GroovyRowResult row, i ->
			if (i == 0){
				columnNames = row.keySet().toList()
			}
			def values = row.values();
			Vector<String> data = new Vector<String>();
			values.each  { String col ->
				data.addElement(col)
			}
			rows.addElement(data)
		}
	}

	//	private List resultList;
	private List columnNames;
	private Vector rows = new Vector();


	/* (非 Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames[column] ?: "";
	}

	/* (非 Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return rows.size();
	}

	/* (非 Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	/* (非 Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector row = (Vector)rows.elementAt(rowIndex);
		return row.elementAt(columnIndex);
	}
}
