package com.lsk.netdisk.file.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dir {
	private Integer id;
	private String name;
	private String parent;
	private Integer ownerID;
}
