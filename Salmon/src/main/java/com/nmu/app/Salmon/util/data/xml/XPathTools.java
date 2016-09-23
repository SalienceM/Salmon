package com.nmu.app.Salmon.util.data.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
//import org.apache.xpath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import com.ebiz.common.rule.j.MessageLanguageV2;
import org.w3c.dom.Text;
/**xpath工具
 * 用于xml的索引，替换，添加等操作
 * 使用文档有待进一步补全
 * @author hackM
 *
 */
public class XPathTools {
	public static Document doc;
	public static XPath xpath;
	
	private static DocumentBuilder builder;
	private static TransformerFactory tf;
	public XPathTools() throws Exception{
	}
	
	
	public static void main(String[] args)throws Exception{
		init();
		XPath x=xpath;
		System.out.println("hh");
//		getRootEle("/*");
//		getChildEle("/*/*");
//		
//		getLevelEles("/*/bookSub/*[name()='chestName']");
//		getLevelEles("child::auth");
//		getLevelEles("/*/bookSub[BookName='aB']/*[name()='auth']");
//		getLevelEles("/book/bookSub[chestName='ChestA' and price<2000]/price");
//		getLevelEles("/*/bookSub[chestName='ChestA' and price>1500]");
//		NodeList nl=getLevelElesE1("/book/bookSub[chestName='ChestB']");
		NodeList nl=getLevelElesE1(doc,"/*/cd[price>8]",xpath);
		
		//test();
		
		if(nl.getLength()>0){
//		DomXpathDelete(doc, nl);	
//		DomXpathModify(doc, nl, "{:number[*0.85]}");
//			Node father=	NewSub(doc, "good", "");
//			Node father2=	NewSub(doc,"data","");
//				AddSub2(doc, father, "name", "要你命3001");
//				AddSub2(doc, father, "price", "3000");
//				AddSub2(doc, father2,"name","damege");
//				AddSub2(doc, father2,"value","high");
//				AddSub2(doc, father, father2);
		for(int i=0;i<nl.getLength();i++){
////		DomXpathAdd(doc, nl.item(i), "Factory", "madeInChina");	
//		
//			DomXpathAdd(doc, nl.item(i), father);
//			String s=Integer.parseInt(nl.item(i).getTextContent())*0.9+"";
//			
		}
			int i=DomXpathDelete(doc, nl);
		System.out.println(i);
		//tofix(简易操作语言,待开发)
		//{:/name}
		//{:(number)[*0.85]}+{:/book(number)[]}
		//{@+"goods"}
		}

		
		Transformer t1=tf.newTransformer();
		 t1.setOutputProperty(OutputKeys.INDENT,"yes");
	     t1.setOutputProperty(OutputKeys.METHOD, "xml");
		t1.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(new File("d:/text1.xml"))));
		System.out.println("aa");
	}

	public static void test() throws Exception{
		NodeList nl=getLevelElesE1(doc,"/*/Streams/opt_stream",xpath);
		for(int i=0;i<nl.getLength();i++){
			Node n=nl.item(i);
			String s=n.getOwnerDocument().getElementsByTagName("streamIO").item(0).getAttributes().getNamedItem("name").getTextContent();
			System.out.println(s);
		}
		
	}
	
	public static void init()throws Exception{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		builder=dbf.newDocumentBuilder();
		doc=builder.parse(new FileInputStream(new File("D:\\CACKM\\MyEclipse 10\\HM\\WebContent\\static\\config\\xml\\OpenTest-config.xml")));
		tf=TransformerFactory.newInstance();
//		DOMSource doms=new 
		
		XPathFactory xf=XPathFactory.newInstance();
		xpath=xf.newXPath();
	}
	public static void init(Document document) throws Exception{
		doc=document;
		tf=TransformerFactory.newInstance();
		XPathFactory xf=XPathFactory.newInstance();
		xpath=xf.newXPath();
		
	}
	
	
	public static void getRootEle(String str) throws Exception{
		Node node=(Node)xpath.evaluate(str, doc, XPathConstants.NODE);
//		doc.getDocumentElement().
//		System.out.println(node.getNodeName()+"----"+node.getNodeValue());
	}
	
	public static void getChildEle(String str) throws Exception{
		NodeList nl=(NodeList) xpath.evaluate(str, doc, XPathConstants.NODESET);
//		System.out.println();
		for(int i=0;i<nl.getLength();i++){
			System.out.println(nl.item(i).getParentNode().getNodeName()+"--"+nl.item(i).getNodeValue());
//			nl.item(i).
		}
	}


	public static NodeList getLevelElesE1(Document doc,String xp,XPath xpa) throws Exception{
		String x=prefixXpath(xp);
		System.out.println(x);
		List<String> n=new ArrayList<String>();
		
		
		
		NodeList nl=(NodeList) xpa.evaluate(x, doc, XPathConstants.NODESET);
		return nl;
	}
	
	
	public static int DomXpathAdd(Document doc, Node nod, String tagName, String textContent){
		long time =System.currentTimeMillis();
		int i=AddSub(doc, nod, tagName, textContent);
		System.out.println("addDone.");
		System.out.println("Use "+(System.currentTimeMillis()-time)+" ms.");
		return i;
		
	}
	/**给doc对象添加子节点(节点形式)
	 * 用于添加多元素复杂节点
	 * @param doc	xml文档对象	
	 * @param nod	父节点
	 * @param sub	所添加子节点
	 * @return
	 */
	public static int DomXpathAdd(Document doc, Node nod,Node sub){
		long time =System.currentTimeMillis();
		int i=AddSub2(doc, nod, sub);
		System.out.println("addDone.");
		System.out.println("Use "+(System.currentTimeMillis()-time)+" ms.");
		return i;
	}
	/**给某元素添加简单节点(doc不变)
	 * 用于添加多元素复杂节点
	 * @param doc	xml文档对象
	 * @param father	父节点
	 * @param tagName	标签名	
	 * @param textContent	标签内容
	 * @return
	 */
	public static int AddSub2(Document doc,Node father,String tagName, String textContent){
		Element nameElement=doc.createElement(tagName);
		Text nameValue=doc.createTextNode(textContent);
		nameElement.appendChild(nameValue);
		father.appendChild(nameElement);
		return 1;
	}
	/**给某元素添加节点(节点形式)
	 * 用于添加多元素复杂节点
	 * @param doc xml文本对象
	 * @param father	父节点
	 * @param sub	所添加的子节点
	 * @return
	 */
	public static int AddSub2(Document doc,Node father,Node sub){
		father.appendChild(sub);
		return 1;
	}
	/**创建新元素
	 * 以文本对象为模板创建新元素
	 * @param doc	文本对象
	 * @param tagName	标签名字
	 * @param textContent	元素内容(textContent)
	 * @return	Element
	 */
	public static Element NewSub(Document doc,String tagName, String textContent){
		Element nameElement=doc.createElement(tagName);
		Text nameValue=doc.createTextNode(textContent);
		nameElement.appendChild(nameValue);
		return nameElement;
		
	}

	/**给doc添加简单节点
	 * 给doc添加单一标签节点
	 * @param doc	xml文本对象
	 * @param nod	所插入父节点
	 * @param tagName	标签名
	 * @param textContent	标签内容
	 * @return
	 */
	private static int AddSub(Document doc, Node nod, String tagName, String textContent){
		List l=DomMapping(doc, nod);
		for(int i=0;i<l.size();i++){
			//new element
			Element nameElement=doc.createElement(tagName);
			Text nameValue=doc.createTextNode(textContent);
			nameElement.appendChild(nameValue);
			
			doc.getElementsByTagName(nod.getNodeName()).item((Integer) l.get(i)).appendChild(nameElement);
		}
		return l.size();
	}

	/**修改节点元素
	 * 1定位2替换
	 * @param doc	doc对象
	 * @param nod	节点
	 * @param after	修改结果
	 */
	public static int DomXpathModify(Document doc, Node nod, String after){
		long time =System.currentTimeMillis();
		int i=ModifySub(doc, nod, after);
		
		System.out.println("modifyDone.");
		System.out.println("Use "+(System.currentTimeMillis()-time)+" ms.");
		return i;
	}
	public static int DomXpathModify(Document doc, NodeList nl, String after){
		long time =System.currentTimeMillis();
		for(int i=0;i<nl.getLength();i++){
			ModifySub(doc, nl.item(i), after);
		}
		System.out.println("modifyDone.");
		System.out.println("Use "+(System.currentTimeMillis()-time)+" ms.");
		return nl.getLength();
	}
	private static int ModifySub(Document doc,Node nod,String after){
		List l=DomMapping(doc, nod);
		
		//modify
		for(int i=0;i<l.size();i++){
			doc.getElementsByTagName(nod.getNodeName()).item((Integer) l.get(i)).setTextContent(after);
		}
		return l.size();
	}

	/**删除节点
	 * 传入指定节点与文本对象
	 * @param doc
	 * @param nod
	 */
	public static int DomXpathDelete(Document doc,Node nod){
		long time =System.currentTimeMillis();
		int i=DeleteSub(doc, nod);
		System.out.println("delectDone.");
		System.out.println("Use "+(System.currentTimeMillis()-time)+" ms.");
		return i;
	}
	public static int DomXpathDelete(Document doc,NodeList nl){
		long time =System.currentTimeMillis();
		for(int i=0;i<nl.getLength();i++){
			DeleteSub(doc, nl.item(i));
		}
		System.out.println("delectDone.");
		System.out.println("Use "+(System.currentTimeMillis()-time)+" ms.");
		return nl.getLength();
	}
	private static int DeleteSub(Document doc,Node nod){
		List l=DomMapping(doc, nod);
		//delete
		for(int i=0;i<l.size();i++){
			Node nson=doc.getElementsByTagName(nod.getNodeName()).item((Integer) l.get(i));
			doc.getElementsByTagName(nod.getNodeName()).item((Integer) l.get(i)).getParentNode().removeChild(nson);
		}
		return l.size();
	}
	
	/**节点元素在DOM中位置印射
	 * (用于xpath中索引的节点属性进一步操作定位)
	 * @param doc	xmldoc对象
	 * @param nod	节点
	 * @return
	 */
	public static List DomMapping(Document doc,Node nod){
		List l=new ArrayList();
		try{
		//find
		for(int i=0;i<doc.getElementsByTagName(nod.getNodeName()).getLength();i++){
			if(doc.getElementsByTagName(nod.getNodeName()).item(i).isSameNode(nod)){
				l.add(i);
			}
		}
		}catch(Exception e){
			System.out.println("DomMappingException:"+e.getMessage());
		}
//		System.out.println("mappingDone.");
		
		return l;
	}
	
	
//	@Test
//	public static void ShowAllParent(Node n){
//		Node n1=n;
//		while(null!=n1.getParentNode()){
//			System.out.println(n1.getParentNode().getNodeName());
//			n1=n1.getParentNode();
//		}
//		System.out.println("done");
//		
//		
//	}

	
	public static void drlTest4XML(String xmlpathSource,String xmlpathAfter,String drlPath){
		Document doct=XMLXO.InputXml("D:\\first.xml");
		//drool
		
		
	}
	public static String prefixXpath(String xpath){
		char[] a=xpath.toCharArray();
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<a.length;i++){
			if('/'==a[i]&&'/'==a[i+1]){
				sb.append('/');
				i++;
			}else{
				sb.append(a[i]);
			}
		}
		return sb.toString();
		
	}
}
