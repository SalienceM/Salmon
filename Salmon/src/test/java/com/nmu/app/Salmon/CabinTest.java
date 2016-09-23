package com.nmu.app.Salmon;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class CabinTest {
	@Test
	public void test(){
		String baseCabinListStr="1/2-3";
		List<String> baseCabinList=new ArrayList<String>();
		//131割航程
		for(String ori:baseCabinListStr.split("/")){
			//132割航段
			for(String seg:ori.split("-")){
				baseCabinList.add(seg);
				System.out.println("seg:"+seg);
			}
		}
		System.out.println(baseCabinList.size());
	}
	

}
