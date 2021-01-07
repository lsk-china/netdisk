package com.lsk.netdisk.gateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cookie {
	private Integer id;
	private String name;
	private Integer urlID;
}
