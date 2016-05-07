package com.sias.util;

import java.util.Enumeration;
import javax.comm.*;

public class ComPort {

	static CommPortIdentifier portId;
    static Enumeration<CommPortIdentifier>	portList;
	
	@SuppressWarnings("unchecked")
	public static String getComs(){
		portList = CommPortIdentifier.getPortIdentifiers();
		String ret = "";
		while (portList.hasMoreElements()){
			portId = portList.nextElement();
			ret = ret + portId.getName() + "\t";
		}
		return ret;
	}
}
