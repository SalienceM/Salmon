package com.nmu.app.Salmon.util.data.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nmu.app.Salmon.util.data.format.FormatStr;

public class JSONXO {
	/**JSON转对象(File)
	 * 
	 * @param f
	 * @param clazz
	 * @return
	 * @throws IOException 
	 */
	public static <T> Object Json2Object(File f, Class<T> clazz) throws IOException{
		FileInputStream fis=new FileInputStream(f);
		StringBuilder sb=new StringBuilder();
		for(String lineItem:org.apache.commons.io.IOUtils.readLines(fis,"utf-8")){
			sb.append(lineItem);
		}
		//取缔特定字符
		String s=FormatStr.FormatStr(sb.toString());
		
		Object o= JSON.parseObject(s,clazz);
		return o;			
	}
	/**JSON转对象(JSONStr)
	 * 
	 * @param JsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> Object Json2Object(String JsonStr, Class<T> clazz){
		//取缔特定字符
		String s=FormatStr.FormatStr(JsonStr);
		
		Object o=JSON.parseObject(s,clazz);
		return o;
	}
	/**对象转JSON
	 * 
	 * @param o
	 * @return JSONSTR
	 */
	public static <T> String Object2Json(Object o){
		return JSON.toJSONString(o,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**JsonStr处理格式(JSONStr)
	 * 将json字串转为可阅读格式
	 * @param jsonStr
	 * @return
	 */
	public static String TransFor(String jsonStr){
		StringBuilder sb=new StringBuilder();
		char[] strChar=jsonStr.toCharArray();
		int com=0;
		int com1=0;
		for(int i=0;i<strChar.length;i++){
			if(strChar[i]=='{'){
				if(i>0&& strChar[i-1]==','){
					sb.append("\t");
				}
				sb.append('{').append("\n");
				for(int k=0;k<com;k++){
					sb.append("\t");
				}
				com++;
				continue;
			}
			
			if(strChar[i]=='['){
				if(strChar[i+1]=='{'){
					sb.append("\n");	
					for(int k=0;k<com1;k++){
						sb.append("\t");
					}
					sb.append('[').append("\n");
					for(int k=0;k<com;k++){
						sb.append("\t");
					}
				}else{
					sb.append('[');
				}
				com1++;
				continue;
			}
			
			if(strChar[i]==']'){
				com1--;
				if(strChar[i-1]=='}'){
					sb.append("\n");
					for(int k=0;k<com1;k++){
						sb.append("\t");
					}
				}
				sb.append(']');
				continue;
			}
			if(strChar[i]==','){
				sb.append(',').append("\n");
				for(int k=0;k<com-1;k++){
					sb.append("\t");
				}
				continue;
			}
			
			if(i<strChar.length-1){
				if(strChar[i]=='}'){
					com--;
					sb.append("\n");
					for(int j=0;j<com;j++){
						sb.append("\t");
					}
					sb.append('}');

					continue;
				}
			}
			
			
			if(strChar[i]=='\\'){
				if(strChar[i+1]=='t'){
					i++;
					continue;
				}else{
					sb.append('\\');
					continue;
				}
				
			}
			
			if(i==strChar.length-1){
				sb.append("\n").append(strChar[i]);
				continue;
			}
			
			sb.append(strChar[i]);
		}
		
		return sb.toString();
		
	}
	/**JsonStr处理格式(File)
	 * 将json字串转为可阅读格式
	 * @param f
	 * @return
	 */
	public static String TransFor(File f){
		FileReader fr = null;
		StringBuilder sb=new StringBuilder();
		try{
			fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			while(null!=line){
				sb.append(line);
				line=br.readLine();
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
		return TransFor(sb.toString());
	}
	/**格式化json文件
	 * 
	 * @param file
	 * @param fileStr
	 * @return
	 */
	public static boolean FormatJSONF(File file, String fileStr){
		return FormatJSON(TransFor(file), fileStr);
	}
	
	public static boolean FormatJSONS(String JSONstr, String fileStr){
		return FormatJSON(TransFor(JSONstr), fileStr);
	}
	/**将处理后的json装入文件
	 * 
	 * @param fixedJsonStr	JSONFIXED
	 * @param targetfile	FILEPATH
	 * @return
	 */
	private static boolean FormatJSON(String fixedJsonStr, String targetfile){
		FileWriter fw;
		try {
//			String fx=fixedJsonStr.replaceAll("\n\n", "\n");
//			System.out.println("");
			File f=new File(targetfile);
			fw=new FileWriter(f);			
			fw.write(fixedJsonStr);
			fw.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;

		
	}
//	@Test
//	public void test(){
//		JSONXO.FormatJSONS(JSONXO.TransFor(new File("D://P1.txt")), "D://P3.TXT");
//		
//	}
//	
//	
//	@Test
//	public void test1(){
////		Ps p=new Ps();
////		p.setName("a");
////		JSONXO.FormatJSON(TransFor(Object2Json(p)), "D://pp.txt");
////		System.out.println("yah");
//	}
}
