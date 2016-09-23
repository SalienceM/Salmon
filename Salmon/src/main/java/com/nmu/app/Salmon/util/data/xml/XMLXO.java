package com.nmu.app.Salmon.util.data.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.apache.xpath.jaxp.XPathImpl;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.nmu.app.Salmon.util.data.TVO.Person;


public class XMLXO {
	public static Document InputXml(String XMLPath){
		return InputXml(new File(XMLPath));
	}
	/**从文件中获取xmlDoc对象
	 * 
	 * @param file
	 * @return
	 */
	public static Document InputXml(File file){
		Document doc = null;
		DocumentBuilder builder;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try{
		dbf.setValidating(false);
		builder=dbf.newDocumentBuilder();
		doc=builder.parse(new FileInputStream(file));
		
		}catch(Exception e){
			System.out.println("InputXmlException:"+e.getMessage());
		}
		return doc;
	}
	/**将XMLDoc文件对象输出到指定文件中
	 * 
	 * @param doc
	 * @param NewXMLPath
	 * @return
	 */
	public static boolean OutputXml(Document doc, String NewXMLPath){
		TransformerFactory tf;
		tf=TransformerFactory.newInstance();
		boolean flag=false;
		try{
		Transformer t1=tf.newTransformer();
		 t1.setOutputProperty(OutputKeys.INDENT,"yes");
	     t1.setOutputProperty(OutputKeys.METHOD, "xml");
		t1.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(new File(NewXMLPath))));
		flag=true;
		}catch(Exception e){
			System.out.println("OutputXmlException:"+e.getMessage());
		}
		return flag;
		
	}
	
	public static File OutputXml(Document doc){
		TransformerFactory tf;
		tf=TransformerFactory.newInstance();
		File tempfile=null;
		
		boolean flag=false;
		try{
		
		Transformer t1=tf.newTransformer();
		 t1.setOutputProperty(OutputKeys.INDENT,"yes");
	     t1.setOutputProperty(OutputKeys.METHOD, "xml");
	     
	     tempfile=File.createTempFile("temp", ".tmp");
		t1.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(tempfile)));
		}catch(Exception e){
			System.out.println("OutputXmlException:"+e.getMessage());
		}
		return tempfile;
		
	}
	
	/**将bean转为xmlDoc对象
	 * 通过jaxb实现
	 * 需转换的类需要特殊声明(注解)
	 * @param ob
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Document Bean2DOC(Object ob) throws Exception{

		 JAXBContext context = JAXBContext.newInstance((ob.getClass()));  
         Marshaller marshaller = context.createMarshaller();  
         File f=File.createTempFile("temp", ".tmp");
         marshaller.marshal(ob, f); 
         Document doc=XMLXO.InputXml(f);
		return doc;
	}
	/**将xmlDoc对象转为bean
	 * @param <T>
	 * @throws JAXBException 
	 * 
	 */
	public static <T> Object Doc2Bean(Document doc, Class<T> clazz) throws JAXBException{
		Source XMLSource = new DOMSource(doc);  
		JAXBContext jaxbContext; 
		jaxbContext = JAXBContext.newInstance(clazz);  
			//创建解析  
		Unmarshaller um = jaxbContext.createUnmarshaller();  
			//unmarshal可以接受Source对象
		Object o= um.unmarshal(XMLSource);   
		return o;
	}
	/**Doc对象与bean对象相互转换test
	 *	该例子说明相互转换的2个方法均能正常执行
	 * @throws Exception
	 */
//	@Test
//	public void test1() throws Exception{
//		Person p=new Person();
//		p.setName("sun");
//		p.setSex("boy");
//		Document doc=Bean2DOC(p);
//		OutputXml(doc, "D:\\buildx.xml");
//		Person p2=(Person) Doc2Bean(doc, Person.class);
//		System.out.println(p2.getName());
//		System.out.println("");
//	}
	
	
	/**调用XPATHTOOLs进行数据筛取test
	 * @throws Exception 
	 * 
	 */
//	@Test
//	public void test2() throws Exception{
//		Document doc=InputXml(new File("D:\\OLDXML.xml"));
//		//初始化操作对象
//		XPathTools2.init(doc);
//		//XPATH操作串
//		String opstr="/*/singleCityPairInfo/PricingInfo/segList/fltInfoList/fareInfoList[price=-1]";
//		String opstr2="/*/singleCityPairInfo/PricingInfo/segList[fltInfoList/fareInfoList[price=-1]]";//以子节点为条件
//		String opstr3="/*/singleCityPairInfo/PricingInfo/segList[not(exists(fltInfoList/fareInfoList))]";//截取不存在xpath2.0语法
//		NodeList nl=XPathTools2.getLevelElesE1(doc, opstr,XPathTools2.xpath);
//		if(nl.getLength()>0){
//			int i=XPathTools.DomXpathDelete(doc, nl);
//			System.out.println(i);
//		}
//		NodeList nl2=XPathTools2.getLevelElesE1(doc, opstr3, XPathTools2.xpath);
//		if(nl2.getLength()>0){
//			int i=XPathTools.DomXpathDelete(doc, nl);
//			System.out.println(i);
//		}
//		
//		OutputXml(doc,"D:\\NEWXML.xml");
//		//删除空行
//		FormatStr.FormatLine(new File("D:\\NEWXML.xml"), new File("D:\\NEWXML.xml"));
//		
//		
//	}
}
