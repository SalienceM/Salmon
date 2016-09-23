package com.nmu.app.Salmon.util.data.date.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nmu.app.Salmon.util.data.date.IEDate;

/**日期操作工具,能有效的快速处理日期问题
 * 
 * @author hackM
 *
 */
public class EDate implements IEDate{
	/**日期预处理,用于整理为统一格式 4-2-2
	 * 固定format格式
	 * @param date
	 * @return
	 */
	public static String prefixDate(String date){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<date.split("-").length;i++){
			if(0==i){
				sb.append(date.split("-")[i]);
				continue;
			}else{
				if(2>date.split("-")[i].length()){
					String str="0"+date.split("-")[i];
					sb.append("-"+str);
				}else{
					sb.append("-"+date.split("-")[i]);
				}
			}
			
		}
		
		return sb.toString();
				
	
	}
	public static String prefixDate(String date, String format){
		return format;
		//tobeContinue
	}
	/**判断日期是否合法 用于统一格式 4-2-2
	 * 固定格式
	 * @param date
	 * @return
	 */
	public static boolean checkDate(String date){

		boolean Bflag=false;
		try{
		int[] month31=new int[]{1,3,5,7,8,10,12};
		
		
		if(baseCheckDate(date)&& 
			3==date.split("-").length&&
			(IsInt(date,"-"))){
			//year不做判断
			//month
			if(containInt(month31,Integer.parseInt(date.split("-")[1]))){
				if(31>=Integer.parseInt(date.split("-")[2])){
					Bflag=true;
				}
			}else if(2==Integer.parseInt(date.split("-")[1])){
				//闰年
				if((Integer.parseInt(date.split("-")[0])%4==0&& 
					Integer.parseInt(date.split("-")[0])%100!=0)||
					Integer.parseInt(date.split("-")[0])%400==0){
					
					if(29>=Integer.parseInt(date.split("-")[2])){
						Bflag=true;
					}
				//非闰年
				}else{
					if(28>=Integer.parseInt(date.split("-")[2])){
						Bflag=true;
					}
					
				}
			}else{
				if(30>=Integer.parseInt(date.split("-")[2])){
					Bflag=true;
				}
				
			}		
		}
		}catch(Exception e){
			Bflag=false;
		}
		return Bflag;
				
	}

	/**日期增长moreEffective(1.5times)
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String dateAddV2(String date) throws Exception{
		int[] month31=new int[]{1,3,5,7,8,10,12};
		int[] month30=new int[]{4,6,9,11};
		String result="";
		if(checkDate(date)){
			List<Integer> intA=S2Ia(date, "-");
			//2月
			if(2==intA.get(1).intValue()){
				if((dateYearRun(date)&&29==intA.get(2).intValue())||
					!dateYearRun(date)&&28==intA.get(2).intValue()){
					result=intA.get(0)+"-03-01";
				}else{
					result=intA.get(0)+"-"+intA.get(1)+"-"+(intA.get(2).intValue()+1)+"";
				}
			}
			//平月
			else if(containInt(month30, intA.get(1))&&
					30==intA.get(2).intValue()){
				result=intA.get(0)+"-"+(intA.get(1)+1)+"-1";
			}
			//闰月
			else if(containInt(month31, intA.get(1))&&
					31==intA.get(2).intValue()){
				if(12==intA.get(1)){
					result=intA.get(0)+1+"-01-01";
				}else{
					result=intA.get(0)+"-"+(intA.get(1)+1)+"-01";
				}
			}
			else{
				result=intA.get(0)+"-"+intA.get(1)+"-"+(intA.get(2).intValue()+1)+"";
			}
			
		}
		
		return prefixDate(result);
		
	}
	public static String dateAdd(String date)throws Exception{
		date=prefixDate(date);
		if(checkDate(date)){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		long afterl=sdf.parse(date).getTime()+24*1000*3600;
		return sdf.format(new Date(afterl));
		}else{
			return null;
		}
	}	
	public static String dateAddV3(String date, String format)throws Exception{
		//待开发
		return format;
		
	}
	
	/**对比传入日期大小(返回比较天数)dependency dateAdd
	 * 所传日期需为标准日期格式//待改:为format类型决策
	 * @param datebefore 起始日期
	 * @param dateafter	  终止日期
	 * @return
	 * @throws Exception 
	 */
	public static long compareDate(String datebefore, String dateafter) throws Exception{
		//默认第二个为较大日期进行处理
		//判断日期大小
		//1.用'-'做分隔符,将日期串分隔
		int num=0;
		boolean sign=false;//匹配标签
		String[] bseq=datebefore.split("-");
		String[] aseq=dateafter.split("-");
		//2.将字串转为long
		List<Long> bl=new ArrayList<Long>();
		List<Long> al=new ArrayList<Long>();
		for(String s:bseq){
			bl.add(Long.parseLong(s));
		}
		for(String s:aseq){
			al.add(Long.parseLong(s));
		}
		boolean flag=false;
		if(al.size()==bl.size()){
			for(int i=0;i<al.size();i++){
				if(al.get(i)>bl.get(i)){
					flag=true;
					break;
				}
			}
		}
		//若前者大位置对调
		if(!flag){
			String s=dateafter;
			dateafter=datebefore;
			datebefore=s;
		}
			while(!sign){
				String tomo=dateAdd(datebefore);
				num++;
				if(prefixDate(dateafter).equalsIgnoreCase(prefixDate(tomo))){
					break;
				}
				datebefore=tomo;
			}
		
		return (flag)?num:0-num;
	}	
	/**对比日期大小moreEfective(80times)
	 * 
	 * @param datebefore
	 * @param dateafter
	 * @return
	 * @throws Exception
	 */
	public static long compareDateV2(String datebefore, String dateafter)throws Exception{
		//prefixdate
		datebefore=prefixDate(datebefore);
		dateafter=prefixDate(dateafter);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date db=sdf.parse(datebefore);
		Date da=sdf.parse(dateafter);
		long dbl=db.getTime();
		long dal=da.getTime();
		
		//trun to days
		long days= (dal-dbl)/1000/3600/24;
		return days;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static boolean dateYearRun(String date){
		boolean flag=false;
		if((Integer.parseInt(date.split("-")[0])%4==0&& 
				Integer.parseInt(date.split("-")[0])%100!=0)||
				Integer.parseInt(date.split("-")[0])%400==0){
			flag=true;
	}
		return flag;
		}

	private static boolean baseCheckDate(String date){
		boolean flag=true;
		try{
		if(Integer.parseInt(date.split("-")[0])<=1000 ||
			Integer.parseInt(date.split("-")[0])>=9999){
			flag=false;
		}
		if(Integer.parseInt(date.split("-")[1])<1||
			Integer.parseInt(date.split("-")[1])>12	){
			flag=false;
		}
		if(Integer.parseInt(date.split("-")[2])<1||
				Integer.parseInt(date.split("-")[2])>31	){
			flag=false;
		}
		}catch(Exception e){
			flag=false;
		}
		
		return flag;
		
	}	
	/**判断INT是否合法
	 * 
	 * @param a
	 * @param chara
	 * @return
	 */	
	private static boolean IsInt(String a,String chara){
		boolean flag=true;
		try{
		for(String str:a.split(chara)){
			int i=Integer.parseInt(str);
		}
		}catch(Exception e){
			flag=false;
		}finally{
			return flag;
		}
	}

	/**判断INT是否存在
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean containInt(int[] a,int b){
		boolean flag=false;
		for(int i:a){
			if(i==b){
				flag=true;
				break;
			}
		}
		return flag;
		
	}

	/**date转int
	 * 
	 * @param str
	 * @param subCharater
	 * @return
	 * @throws Exception
	 */
	private static List<Integer> S2Ia(String str,String subCharater) throws Exception{
		String[] strA=str.split(subCharater);
		List<Integer> intA=new ArrayList<Integer>();
		try{
		for(String s:strA){
			intA.add(Integer.parseInt(s));
		}
		}catch(Exception e){
			throw new Exception(
					"FormatException:S2Ia wrong.");
		}
		return intA;
		
	}
}
