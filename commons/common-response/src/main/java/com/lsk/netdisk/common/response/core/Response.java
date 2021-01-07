package com.lsk.netdisk.common.response.core;

import lombok.Data;

@Data
public class Response {
	private Integer code;
	private String message;
	private Object data;
}
