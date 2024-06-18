package com.banckend.blogappapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
//  RuntimeExceptiom ---bacause checking the uncheaked excepttion
	
	String resouceName;
	String feildName;
	long feildValue;
	
	public ResourceNotFoundException(String resouceName, String feildName, long feildValue) {
	super(String.format("%s not found with %s :%s", resouceName,feildName,feildValue));
	this.resouceName = resouceName;
	this.feildName = feildName;
	this.feildValue = feildValue;
}
	
}
