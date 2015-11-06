package com.dingmei;

import java.util.*;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.dingmei.dto.MyTimeDTO;
import org.junit.Test;

public class SimpleTest {
	
	@Test
	public void test1(){
		String tilte = "title-4758416";
		String[] strs = tilte.split("-");
		System.out.println(strs[strs.length-1]);
	}
	
	@Test
	public void test2(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+10);
		System.out.println(calendar.getTime());
	}

	@Test
	public void test3(){
		String regex = "%如果%那么%".replace("%", "[\\S|\\s]+");
		System.out.println(regex);
		if(Pattern.matches(regex, "d如果你2b，那么你2b")){
			System.out.println("success");
		}
	}

    @Test
    public void testMyTimeDTO(){
        MyTimeDTO n1 = new MyTimeDTO();
        n1.setTimeStyle("quarter");
        n1.setYear(2019);
        n1.setQuarter(2);

        MyTimeDTO n2 = new MyTimeDTO();
        n2.setTimeStyle("quarter");
        n2.setYear(3);
        n2.setMonth(null);
        n2.setQuarter(3);

        System.out.println(n1);

        System.out.println(n1.equals(n2));
    }

    @Test
    public void testTreeMap(){
        MyTimeDTO n1 = new MyTimeDTO();
        n1.setTimeStyle("MM");
        n1.setMonth(3);
        MyTimeDTO n2 = new MyTimeDTO();
        n2.setTimeStyle("MM");
        n2.setMonth(1);
        MyTimeDTO n3 = new MyTimeDTO();
        n3.setTimeStyle("MM");
        n3.setMonth(5);
        MyTimeDTO n4 = new MyTimeDTO();
        n4.setTimeStyle("MM");
        n4.setMonth(2);

        Map<MyTimeDTO,String> tree = new TreeMap<MyTimeDTO, String>();
        tree.put(n1,"1");
        tree.put(n2,"2");
        tree.put(n3,"3");
        tree.put(n4,"4");

        Set<MyTimeDTO> sets = new TreeSet<MyTimeDTO>();
        for(Map.Entry<MyTimeDTO,String> entry : tree.entrySet()){
            System.out.println(entry.getKey().toString());
            sets.add(entry.getKey());
        }

        for(MyTimeDTO dto : sets){
            System.out.println(dto);
        }

        System.out.println(JSONObject.toJSONString(sets));
    }
}
