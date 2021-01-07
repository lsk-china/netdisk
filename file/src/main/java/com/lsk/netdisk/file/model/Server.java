package com.lsk.netdisk.file.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Server {
	private Integer id;
	private String name;
	private String uploadURL;
	private String downloadURL;
	private String extraSpaceURL;
	private Integer p;
}
