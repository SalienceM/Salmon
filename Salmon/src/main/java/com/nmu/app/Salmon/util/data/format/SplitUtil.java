package com.nmu.app.Salmon.util.data.format;

import java.util.ArrayList;
import java.util.List;


import com.nmu.app.Salmon.util.valid.ValidUtil;

public class SplitUtil {
//	public static String CHAR_BRACKET=
	
	
	
	
	
	/**Make sure if all the Sequence from logicStr can be found in factStr. 
	 * 
	 * @param factStr		contains all fact Sequence
	 * @param logicStr		contains all condition Sequence
	 * @param leftSp		left separateChar 
	 * @param rightSp		right separateChar
	 * @return
	 */
	public static boolean AllBeInThere(String factStr,String logicStr,char leftSp, char rightSp){
		//首次校验.
		if(ValidUtil.isBlank(factStr,logicStr)){
			return false;
		}
		//2两组逻辑串进行分隔成为List
		List<String> factList=splitIntoList(factStr, leftSp, rightSp);
		List<String> logicList=splitIntoList(logicStr, leftSp, rightSp);
		
		return factList.containsAll(logicList);
	}
	
	public static boolean AllBeInThere(String factStr,String logicStr){
		return AllBeInThere(factStr, logicStr, '[', ']');
	}
	
	
	/**符号分隔
	 * 
	 * @param oriStr 原始逻辑串
	 * @param leftSp		分隔符:逻辑左
	 * @param rightC	分隔符:逻辑右
	 * @return
	 */
	private static List<String> splitIntoList(String oriStr,char leftSp, char rightSp){
		int sign=0;
		StringBuilder sb=new StringBuilder();
		List<String> sl=new ArrayList<String>();
		char[] chseq=oriStr.toCharArray();
		//二次校验
		//不通过直接返新list
		if(0==chseq.length||leftSp!=chseq[0]|| rightSp!=chseq[chseq.length-1]){
			return new ArrayList<String>();
		
		}else{
			for(int i=0;i<chseq.length;i++){
				//链接头串
				if(chseq[i]==leftSp){
					if(sign==0){
					sign++;
					sb=new StringBuilder();
					continue;
					}else{
						sb.append(chseq[i]);
					}
				//2.2开始阅读字串
				//链接尾串	
				}else if(chseq[i]==rightSp){
					//有效
					if(i==chseq.length-1|| leftSp==chseq[i+1]){
						sign=0;
						sl.add(sb.toString());
						continue;
						//无效	
					}else{
						sb.append(chseq[i]);
						continue;
					}
					//其余一般串	
				}else{
					sb.append(chseq[i]);
				}
			}
			return sl;
		}
	}
	
//	@Test
//	public void test(){
////		List l=splitIntoList("[[a][b]][[c]]\\", '[', ']');
//		
//		
//		
////		long t1=System.currentTimeMillis();
//		System.out.println(AllBeInThere("[d][[a][SS]","[d][[a][]"));
////		System.out.println(System.currentTimeMillis()-t1);
////		char[] cseq=new StringBuilder().toString().toCharArray();
//		System.out.println("");
//		
//	}
	
}
