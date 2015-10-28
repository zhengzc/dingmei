package com.dingmei;

import java.util.Calendar;
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
        n1.setDay(3);
        n1.setQuarter(2);

        MyTimeDTO n2 = new MyTimeDTO();
        n2.setDay(3);
        n2.setMonth(null);
        n2.setQuarter(3);

        System.out.println(n1.equals(n2));
    }

    @Test
    public void testJson(){
    }
}
