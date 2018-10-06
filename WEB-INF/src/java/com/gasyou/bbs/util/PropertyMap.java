package com.gasyou.bbs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyMap {

	private static final PropertyMap INSTANCE = new PropertyMap();

	private Map<String, String> props = new HashMap<>();

	public PropertyMap() {

		InputStream is = this.getClass().getClassLoader().getResourceAsStream("/init.properties");

		Properties p = new Properties();
		try {
			p.load(is);
			is.close();
			for (Object key : p.keySet()) {
				props.put(key.toString(), p.get(key).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key, String defaultValue) {
		String val = this.props.get(key);

		return StringUtils.value(val, defaultValue);
	}

	public String[] getProperty(String key, String[] defaultValue) {

		String val = getProperty(key, "");

		String[] vals = val.split(",");

		if (vals.length == 0) {
			return defaultValue;
		}

		String[] ret = new String[vals.length];

		for (int i = 0; i < vals.length; i++) {
			ret[i] = StringUtils.value(vals[i].trim(), StringUtils.EMPTY);
		}

		return ret;
	}

	public static PropertyMap getInstance() {
		return INSTANCE;
	}
}
