/**
 *
 */
package tools.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import sun.util.ResourceBundleEnumeration;
import groovy.transform.TypeChecked
import groovy.util.logging.Log4j;

/**
 * XML用のResourceBundle
 *
 */
@Log4j
public class XMLResourceBundle extends ResourceBundle {

	private static final XMLResourceBundleControl control = new XMLResourceBundleControl()

	/**
	 * コンストラクタ
	 *
	 * @param stream
	 * @throws IOException
	 */
	public XMLResourceBundle(InputStream stream) throws IOException {
		Properties properties = new Properties();
		try {
			//			XMLUtils.load(properties, stream)
			properties.loadFromXML(stream);
		} catch (Exception e) {
			log.error e.message, e
			throw e
		}
		propertis = new HashMap(properties);
	}

	private Map<String, ?> propertis;

	/**
	 * XMLの設定ファイルの情報を読み込みます。
	 *
	 * @param baseName 設定ファイル名（拡張子なし）
	 * @return XMLの設定ファイルの情報
	 */
	public static final ResourceBundle getBundleFromXml(String baseName) {
		return getBundle(baseName, control)
	}

	/* (非 Javadoc)
	 * @see java.util.PropertyResourceBundle#handleGetObject(java.lang.String)
	 */
	@Override
	public Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}
		return propertis.get(key);
	}

	/* (非 Javadoc)
	 * @see java.util.PropertyResourceBundle#getKeys()
	 */
	@Override
	public Enumeration<String> getKeys() {
		ResourceBundle parent = this.parent;
		return new ResourceBundleEnumeration(propertis.keySet(),
		(parent != null) ? parent.getKeys() : null);
	}

	/* (非 Javadoc)
	 * @see java.util.PropertyResourceBundle#handleKeySet()
	 */
	@Override
	protected Set<String> handleKeySet() {
		return propertis.keySet();
	}
}
