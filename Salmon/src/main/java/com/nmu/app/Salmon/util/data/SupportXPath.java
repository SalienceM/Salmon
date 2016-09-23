package com.nmu.app.Salmon.util.data;
/**Support4XPATHDEV
 * 
 * @author hackM
 *
 */
public class SupportXPath {
	/**循环索引
	 * 
	 */
	public static String roundCatch="/*/singleCityPairInfo/PricingInfo/segList[fltInfoList/fareInfoList[price!=-1]]";
	
	/**截取子节点不存在
	 * 
	 */
	public static String notexistCatch="/*/singleCityPairInfo/PricingInfo/segList[not(exists(fltInfoList/fareInfoList))]";
}
