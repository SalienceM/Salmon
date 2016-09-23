package com.nmu.app.Salmon.util.data.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.nmu.app.Salmon.util.valid.ValidUtil;

/**格式处理类
 *201603311224	暂时不做superSplit
 * @author hackM
 *
 */
@SuppressWarnings("unused")
public class FormatStr {
	public static String SplitChar[]={".","/"};

	public static String FormatPath(String path){
		
		return new File("").getAbsolutePath()+"\\src\\main\\resource\\conf\\"+path;
		
	}
	/**定位项目中文件所在File可用地址(.)
	 * 
	 * @param Path
	 * @param FileName
	 * @return
	 */
	public static String FormatPath(String Path, String FileName){
		return FormatPath(Path, FileName, "\\.");
	}
	/**定位项目中文件所在File可用地址
	
	 * 
	 * @param Path	包地址
	 * @param FileName	文件名
	 * @param conditionSeq	分隔符
	 * @return	fileStr
	 */
	public static String FormatPath(String Path, String FileName, String conditionSeq){
		StringBuilder sb=new StringBuilder(); 
		String[] seq=Path.split(conditionSeq); 
		sb.append("\\src\\");
		for(String s:seq){
			sb.append(s).append("\\");
		}
		return new File("").getAbsolutePath()+sb.toString()+FileName;
	}
	
	
	public static String FormatStr(String str){
		return FormatStr(str,'\t',"");
	}
	/**格式化字符串
	 * 同replace
	 * @param str
	 * @param pre
	 * @param to
	 * @return
	 */
	public static String FormatStr(String str, char pre, String to){
		StringBuilder sb=new StringBuilder();
		char[] fixstr=str.toCharArray();
		for(char cr:fixstr){
			if(cr==pre){
				sb.append(to);
				continue;
			}else{
				sb.append(cr);
			}
		}
		
		return sb.toString();
	}
	
	/**删除空行
	 * 
	 * @param f
	 * @param f2
	 * @return
	 */
	public static boolean FormatLine(File f, File f2){
		boolean flag=false;
		FileReader fr = null;
		FileWriter fw = null;
		StringBuilder sb=new StringBuilder();
		try{
			fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(null!=line){
				if(!ValidUtil.IsLineBlank(line)){
				sb.append(line).append("\n");
				line=br.readLine();
				}else{
				line=br.readLine();
				}
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			try {
				fw=new FileWriter(f2);
				fw.write(sb.toString());
				fw.close();
				flag=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return flag;
		
		
	}
//	public static String[] SuperSplit(String preStr, String conditionChar, long Method){
//		return SuperSplit(preStr, new String[]{conditionChar}, Method);
//	}
//	public static String[] SuperSplit(String preStr, String[] conditionSeq, long Method){
//		
//		
//	}
//	private static String[] SuperSplitAny(String preStr, String[] conditionSeq){
//		if(ValidUtil.isBlank(conditionSeq)){
//		}
//		return conditionSeq;
//		
//		
//	}
//	private static String[] SuperSplitAll(String preStr, String[] conditionSeq){
//		if(ValidUtil.isBlank(conditionSeq)){
//			throw new Exception("NULL");
//		}
//		List l=new ArrayList<String>();
//		for(String s:conditionSeq){
//			
//		}
//	}
 	
	
//	@Test
//	public void test1(){
//		String str="{\"searchCriteria\":{\"travelerInfoSummary\":{\"paxTypeQuantity\":[{\"type\":\"ADT\",\"quantity\":1}]},\"airSearch\":{\"pos\":{\"pseudoCityCode\":\"SHA777\",\"airportCode\":\"SHA\",\"channelID\":\"1E\",\"iataNo\":\"\"},\"specificProductInfo\":[],\"airOriDestOption\":[{\"departureLocation\":{\"cityCode\":\"SHA\"},\"arrivalLocation\":{\"cityCode\":\"TAO\"},\"odNumber\":0,\"isDirectionInd\":false,\"departDate\":\"2016-03-03\"},{\"departureLocation\":{\"cityCode\":\"TAO\"},\"arrivalLocation\":{\"cityCode\":\"SHA\"},\"odNumber\":1,\"isDirectionInd\":false,\"departDate\":\"2016-03-05\"}],\"processingInfo\":{\"targetSource\":\"\",\"flightSvcInfoIndicator\":false,\"baseFaresOnlyIndicator\":false,\"availIndicator\":false},\"giftInd\":false,\"currency\":\"CNY\",\"tripType\":\"RT\",\"giftId\":null},\"travelType\":\"AIRONLY\"},\"channelInfo\":{\"firstChannelNo\":\"71\",\"secondChannelNo\":\"\",\"webSite\":\"zh_CN\"},\"timeStamp\":\"2015-06-2913:37:02.912\",\"version\":\"1.0\",\"transactionId\":\"2015-06-2913:37:02.912\",\"language\":\"zh\"}";
//		System.out.println(TransFor(str));
//	}
//	@Test
//	public void test2(){
////		FormatJSON("", "");
//		File f=new File("D://1.txt");
//	}
//	@Test
//	public void test3() throws Exception{
//		Travelers t=new Travelers();
////		BaseTravelerInfo bti=new BaseTravelerInfo();
////		
//////		List<InsTravelerInfo> l=new ArrayList<InsTravelerInfo>();
//////		InsTravelerInfo iti=new InsTravelerInfo();
////		main.java.com.ebiz.client.booking.PersonName pn=new main.java.com.ebiz.client.booking.PersonName();
////		pn.setFullName("bob");
////		bti.setPersonName(pn);
////
////		main.java.com.ebiz.client.booking.Telephone tp =new main.java.com.ebiz.client.booking.Telephone();
////		tp.setMobilePhoneNumber("15555555555");
////		bti.setTelephone(tp);
////
////
////		main.java.com.ebiz.client.booking.Document dm= new main.java.com.ebiz.client.booking.Document();
////		dm.setDocID("41000000000");
////		dm.setDocType("IN");
////		bti.getDocument().add(dm);
////
////		bti.setTravelerID(1);
////		t.getBase().add(bti);
////		t.getBase().addAll(t.dueTraveler(2, 1, 2));
//		
//		
//		
//		FormatStr.FormatJSON(BaseWork.Object2Json(t),FormatStr.FormatPath("travelers.txt"));
//	}
//	@Test
//	public void test4() throws IOException{
////		Object o=BaseWork.Json2Object(Format.FormatPath("taxDreq.txt"), AirDomTaxSearchRQ.class);
////		AirDomTaxSearchRQ arq=(AirDomTaxSearchRQ) o;
////		String f1=Format.FormatPath("taxDreq.txt");
////		Format.FormatJSON(BaseWork.Object2Json(arq), f1);
//		FormatJSON(TransFor(new File(FormatPath("taxIreq.txt"))), FormatPath("taxIreq.txt"));
//
//		
//	}
//	@Test
//	public void test5(){
//		fTt(new File(FormatPath("Bookingreq.txt")));
//	}
//	@Test
//	public void test6(){
//		System.out.println(FormatStr.FormatPath("com.sun.ebiz.test.test2", "2.txt"));
//	}
//	/**geshihuaJSON
//	 * @throws IOException 
//	 * 
//	 */
//	@Test
//	public void test7() throws IOException{
//		BookingRQ req;String f1;
//		req=(BookingRQ) BaseWork.Json2Object(new File(FormatStr.FormatPath("Bookingreq.txt")), BookingRQ.class);
//		f1=FormatStr.FormatPath("Bookingreq.txt");
//		boolean a=FormatStr.FormatJSON(BaseWork.Object2Json(req), f1);
//		System.out.println(a);
//	}
//	@Test
//	public void test8() throws Exception{
//		String f1=FormatStr.FormatPath("RWC.txt");
//		boolean a=FormatStr.FormatJSON(FormatStr.TransFor(new File(f1)), f1);
//		System.out.println(a);
//	}
}
