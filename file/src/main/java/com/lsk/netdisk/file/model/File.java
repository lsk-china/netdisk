package com.lsk.netdisk.file.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class File {
	private Integer id;
	private String storageName;
	private Integer ownerID;
	private Integer storageServer;
	private String fileName;
	private String parentDir;
}
