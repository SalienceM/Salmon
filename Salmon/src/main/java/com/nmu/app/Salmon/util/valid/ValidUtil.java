package com.nmu.app.Salmon.util.valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**验证工具类
 * 
 * @author hackM
 *
 */
public class ValidUtil {
	public static char[] nullchar={' ','\t'};
	
	private static boolean CheckNull(Object o){
		if(null==o){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isBlank(String s){
		if(CheckNull(s)){
			return true;
		}
		if("".equals(s)||"".equals(s.trim())){
			return true;
		}else{
			return false;
		}
		
	}
	public static boolean isBlank(String...args){
		for(String s:args){
			if(isBlank(s)){
				return true;
			}
		}
		return false;
		
	}
	public static <E> boolean isBlank(Iterable<E> c){
		if(CheckNull(c)){
			return true;
		}
		if(!c.iterator().hasNext()){
			return true;
		}else{
			return false;
		}
	}

	public static <E> boolean isBlank(Collection<E> c){
		if(CheckNull(c)){
			return true;
		}
		if(c.isEmpty()||0==c.size()){
			return true;
		}else{
			return false;
		}

	}
	
	public static <K, V> boolean isBlank(Map<K, V> m){
		if(CheckNull(m)){
			return true;
		}
		if(m.isEmpty()|| isBlank(m.values())|| isBlank(m.keySet())|| isBlank(m.entrySet())){
			return true;
		}else{
			return false;
		}
	}
	public static boolean IsTxtLineBlank(String s){
		if(CheckNull(s)){
			return true;
		}
		if("".equals(s)||"".equals(s.trim())||"".equals(s.replace("\t", ""))
				||"".equals(s.replace(" ", ""))){
			return true;
			
		}else{
			return false;
		}
	}
	/**判断是行为空
	 * 特殊符号可以配在nullchar字串作为例外配置
	 * @param s
	 * @return
	 */
	public static boolean IsLineBlank(String s){
		if(CheckNull(s)){
			return true;
		}
		char[] ca=s.toCharArray();
		int num=0;
		for(char c:ca){
			for(char sc:nullchar){
				if(sc==c){
					num++;
					break;
				}
			}
		}
		return num==ca.length?true:false;
	}
}
