package com.lsk.netdisk.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlPermission {
	private Integer id;
	private String url;
	private String role;
	private String service;
}
