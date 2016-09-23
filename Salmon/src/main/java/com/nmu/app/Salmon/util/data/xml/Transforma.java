/**用于格式转换的工具
 * 
 */
package com.nmu.app.Salmon.util.data.xml;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

//import com.sun.main.instances.pojo.manual.StreamBase;

public class Transforma {
	/**调用xsl对指定xml进行转换
	 * 
	 * @param xsl
	 * @param beforexml
	 * @param afterxml
	 * @return boolean 成功true
	 */
	public static boolean TransforXMLXSL(File xsl, File beforexml, File afterxml){
		boolean flag=true;
		try{
			//转换器工厂初始化
			TransformerFactory tf=TransformerFactory.newInstance();
			DocumentBuilderFactory factory=
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder =
					factory.newDocumentBuilder();
			//xmlDoc对象
			Document doc=builder.parse(beforexml);
			//xsl引入
			Source xslSource=new StreamSource(xsl);
			//构造模板
			Transformer t1=tf.newTransformer(xslSource);
			//输出返回
			t1.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(afterxml)));			
		}catch(Exception e){
			flag=false;
		}
		
		return flag;
		
	}
	
	
	/**根据xsl对xmlDoc进行转换
	 * 返回转化完毕的doc
	 * @param xsl
	 * @param doc
	 * @return
	 */
	public static Document TransforXMLXSL(File xsl,Document doc){
		boolean flag=true;
		File tempfile=null;
		Document d=null;
		try{
			//转换器工厂初始化
			TransformerFactory tf=TransformerFactory.newInstance();

			//xsl引入
			Source xslSource=new StreamSource(xsl);
			//构造模板
			Transformer t1=tf.newTransformer(xslSource);
			//输出返回
			//File f1=new File(System.getProperty("webapp.root")+"static\\config\\buffer.xml");
			tempfile=File.createTempFile("temp", ".tmp");
			t1.transform(new DOMSource(doc),new StreamResult(tempfile));
			d= XMLXO.InputXml(tempfile);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return d;
		
	}
	/**将对象转为xmlDoc对象
	 * 通过jaxb实现
	 * @param ob
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Document TransforBEAN2DOC(Object ob,String path) throws Exception{

		 JAXBContext context = JAXBContext.newInstance((ob.getClass()));  
         Marshaller marshaller = context.createMarshaller();  
//         Class<?> c=Class.forName(path);
// 		 Object o=c.newInstance();
//         Object o=
         File f=File.createTempFile("temp", ".tmp");
         marshaller.marshal(ob, f); 
         Document doc=XMLXO.InputXml(f);
		return doc;
	}
	public static void main(String[] args) throws Exception{
//		StreamBase sb=new StreamBase();
//		sb.setStreamID("1");
//		TransforBEAN2DOC(sb,"com.sun.main.instances.pojo.manual.StreamBase");
	}
	
//	@Test
//	public void test() throws Exception{
//		File beforexml=new File("D:\\CACKM\\MyEclipse 10\\HM\\WebContent\\static\\config\\xml\\HMS\\HMS_featurePerson.xml");
//		File afterxml=new File("D:\\a.xml");
//		File xsl=new File("D:\\CACKM\\MyEclipse 10\\HM\\WebContent\\static\\config\\xslt\\S2R\\S2R_featurePerson.xsl");
//		
//		System.out.println(TransforXMLXSL(xsl, beforexml, afterxml));
//		
////		TransformerFactory tf=TransformerFactory.newInstance();
////		DocumentBuilderFactory factory=
////				DocumentBuilderFactory.newInstance();
////		DocumentBuilder builder =
////				factory.newDocumentBuilder();
////		//xmlDoc对象
////		Document doc=builder.parse(beforexml);
////		Document d=TransforXMLXSL(xsl, doc);
//		
//		
//	}
	

}
