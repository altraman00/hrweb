package com.sunlands.hr.hanlp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Test4 {

	public static void main(String[] args) {

		TreeMap<String, String> tm = new TreeMap<String, String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		
		tm.put("1", "专科");
		tm.put("6", "博士");
		tm.put("3", "硕士");
		tm.put("2", "本科");
		
		for (String  key : tm.keySet()) {
            System.out.println("key :"+key+",對應的value："+tm.get(key));
        }
		String key = tm.entrySet().iterator().next().getKey();
		String value = tm.entrySet().iterator().next().getValue();
		
		System.out.println(key + "" + value);

	}

}
