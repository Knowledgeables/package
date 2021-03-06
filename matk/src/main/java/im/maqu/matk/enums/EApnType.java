/*

 */

package im.maqu.matk.enums;


import im.maqu.matk.utils.UtilString;

/**
 * @author : Maqu
 * @date   : 2014-3-17
 * @desc   : 网络接入类型
 */
public enum EApnType {
	//未知接入类型
	APN_UNKNOWN("UNKNOWN"), 
	//中国电信APN 2G
	APN_CTWAP("CTWAP"),
	//中国电信APN 3G
	APN_CTNET("CTNET"),  
	//中国联通APN 2G手机
	APN_UNIWAP("UNIWAP"),
	//2G手机  3G手机
	APN_3GWAP("3GWAP"),
	//2G手机 2G电脑
	APN_UNINET("UNINET"),
	//2G手机  3G电脑
	APN_3GNET("3GNET"),
	//中国移动APN 2G
	APN_CMWAP("CMWAP"),
	//中国移动APN 3G
	APN_CMNET("CMNET");
	
	private String mValue;
	
	EApnType(String value) {
		this.mValue = value;
	}
	
	public String getValue() {
		return this.mValue;
	}
	
	public static EApnType valueof(String value) {
		EApnType apnType = EApnType.APN_UNKNOWN;
		
		if(UtilString.isNotEmpty(value)) {
			String info = value.toUpperCase();
			
			if (info.contains(EApnType.APN_CTNET.getValue())) {
				apnType = EApnType.APN_CTNET;
			} else if (info.contains(EApnType.APN_CTWAP.getValue())) {
				apnType = EApnType.APN_CTWAP;
			} else if (info.contains(EApnType.APN_CMNET.getValue())) {
				apnType = EApnType.APN_CMNET;
			} else if (info.contains(EApnType.APN_CMWAP.getValue())) {
				apnType = EApnType.APN_CMWAP;
			} else if (info.contains(EApnType.APN_UNIWAP.getValue())) {
				apnType = EApnType.APN_UNIWAP;
			} else if (info.contains(EApnType.APN_3GWAP.getValue())) {
				apnType = EApnType.APN_3GWAP;
			} else if (info.contains(EApnType.APN_UNINET.getValue())) {
				apnType = EApnType.APN_UNINET;
			} else if (info.contains(EApnType.APN_3GNET.getValue())) {
				apnType = EApnType.APN_3GNET;
			} 
		}
		
		return apnType;
	}
}
