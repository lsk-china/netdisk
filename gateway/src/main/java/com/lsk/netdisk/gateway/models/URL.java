package com.lsk.netdisk.gateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class URL {
	private Integer id;
	private String url;
	private String method;
	private String service;
}
