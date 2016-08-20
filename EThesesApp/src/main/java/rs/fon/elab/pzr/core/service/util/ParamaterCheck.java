package rs.fon.elab.pzr.core.service.util;

import javax.activity.InvalidActivityException;

import rs.fon.elab.pzr.core.exception.InvalidArgumentException;

public class ParamaterCheck {
	
	public static void mandatory(String paramName, Object paramValue){
		if(paramValue==null){
			throw new InvalidArgumentException(paramName+" je obavezno polje!");
		}
	}
}
