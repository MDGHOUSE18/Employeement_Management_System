package com.company.cms.enums;
public enum HeaderEnum {
	ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),
	ACCESS_CONTROL_ALLOW_CREDENTIALS("Access-Control-Allow-Credentials"),
	ACCESS_CONTROL_ALLOW_METHODS("Access-Control-Allow-Methods"),
	ACCESS_CONTROL_MAX_AGE("Access-Control-Max-Age"),
	ACCESS_CONTROL_ALLOW_HEADERS("Access-Control-Allow-Headers"),
	ACCESS_CONTROL_EXPOSE_HEADERS("Access-Control-Expose-Headers"),
	ACCESS_CONTROL_ALLOW_ORIGIN_VALUES("*"),
	ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUES("true"),
	ACCESS_CONTROL_ALLOW_METHODS_VALUES("POST, GET, OPTIONS, DELETE, PUT"),
	ACCESS_CONTROL_MAX_AGE_VALUES("3600"),
	ACCESS_CONTROL_ALLOW_HEADERS_VALUES("Content-Type,enctype, authorization, Accept,cache-control, X-Requested-With, remember-me,X-Auth-Username,X-Auth-Password,X-Auth-Token,X-App-Id,Origin,responsetype"),
	ACCESS_CONTROL_EXPOSE_HEADERS_VALUES("X-Auth-Token,X-App-Id,IM_UserID,HTTP_IM_UserID,responsetype"),
	
	TOKEN_SESSION_KEY("token"),
	USER_SESSION_KEY("user"),
	USER_NAME_TOKEN("X-Auth-Username"),
	PWD_NAME_TOKEN("X-Auth-Password"),
	TOKEN("X-Auth-Token"),
	

	;


	private String codeDesc;

	HeaderEnum(String desc) {
		this.codeDesc = desc;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
}