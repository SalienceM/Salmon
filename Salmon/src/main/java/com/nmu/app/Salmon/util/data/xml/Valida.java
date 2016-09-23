/**仅用于文件的验证
 * 
 */
package com.nmu.app.Salmon.util.data.xml;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;

public class Valida {
	/**XSD_XML验证器
	 * 
	 * @param xsdf xsd文件对象.
	 * @param xmlf xml文件对象.
	 * @return	boolean 通过true.
	 */
	public static boolean ValidXSDXML(File xsdf, File xmlf){
		boolean flag=true;
		try{
			//实例化约束工厂
			SchemaFactory schemafactory=
					SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			//创建新约束
			Source s=new StreamSource(xsdf);
			Schema schema=schemafactory.newSchema(s);
			//创建新验证器
			javax.xml.validation.Validator valid=schema.newValidator();
			//验证
			Source vs=new StreamSource(xmlf);
			valid.validate(vs);

		}catch(Exception e){
			System.out.println(e.getMessage());
			flag=false;
		}
		return flag;
		
	}
	/**指定目录索引xsd//blank
	 * 
	 * @param xmlf
	 * @return
	 */
	public static boolean ValidXSDXML(File xmlf){
		return false;
		
		
	}
//	@Test
//	public void test(){
//		File xsdf=new File("D:\\CACKM\\MyEclipse 10\\HM\\WebContent\\static\\config\\xsd\\OpenTest-config.xsd");
//		File xmlf=new File("D:\\CACKM\\MyEclipse 10\\HM\\WebContent\\static\\config\\xml\\OpenTest-config.xml");
//		System.out.println(ValidXSDXML(xsdf, xmlf));
//		Document doc =XMLXO.InputXml(xmlf);
//		File f=XMLXO.OutputXml(doc);
//		System.out.println("aaa");
//	}


}
