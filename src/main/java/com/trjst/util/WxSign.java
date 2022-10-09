package com.trjst.util;

import java.util.*;

public class WxSign {
	private static SpellComparator spellComparator = null;

	public static String createSign(Map<String, String> map,String pay_key) {
		if (map == null || map.size() == 0)
			return null;
		StringBuffer sb = new StringBuffer();
		List<String> keys = executeDictionnarySort(map);
		for (String key : keys) {
			if (StringUtil.isNotEmpty(map.get(key))) {
				sb.append(key + "=" + map.get(key) + "&");
			}
		}
		sb.append("key="+pay_key);
		String sign2 = MD5.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign2;
	}
	
	static {
		spellComparator = new WxSign().new SpellComparator();
	}

	public static List<String> executeDictionnarySort(Map<String, String> map) {
		if (map == null || map.isEmpty())
			return null;
		List<String> keys = new ArrayList<String>();
		keys.addAll(map.keySet());
		Collections.sort(keys, spellComparator);
		return keys;
	}

	public static String executeDictionnarySort(List<String> list) {
		if (list == null || list.isEmpty())
			return null;
		StringBuilder sb = new StringBuilder();
		Collections.sort(list, spellComparator);
		for (String s : list) {
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 字典排序比较器
	 */
	class SpellComparator implements Comparator<Object> {
		public int compare(Object o1, Object o2) {
			try {
				// 取得比较对象的汉字编码，并将其转换成字符串
				String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
				String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
				// 运用String类的 compareTo（）方法对两对象进行比较
				return s1.compareTo(s2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	}
	
}
