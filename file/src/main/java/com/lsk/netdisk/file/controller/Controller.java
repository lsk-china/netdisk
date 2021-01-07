package com.lsk.netdisk.file.controller;

import com.lsk.netdisk.common.response.aspect.annotation.JsonReturn;
import com.lsk.netdisk.file.helpers.CloudHelper;
import com.lsk.netdisk.file.helpers.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Controller {
	@Autowired
	private CloudHelper cloudHelper;
	@GetMapping("/ls")
	@JsonReturn
	public Object ls(String path,String token){
		return FileHelper.ls(path,cloudHelper.getCurrentUserID(token));
	}
	@GetMapping("/createUserDir")
	public void register(String token, String username){
		Integer currentUserID = cloudHelper.getCurrentUserID(token);
		FileHelper.mkUserDir(username,currentUserID);
		FileHelper.setWorkingDir(username,currentUserID);
	}
	@GetMapping("/mkdir")
	public void mkdir(String name,String parent,@CookieValue("token") String token){
		Integer currentUserID = cloudHelper.getCurrentUserID(token);
		if (!parent.equals("/")){
			FileHelper.setWorkingDir(parent,currentUserID);
		}
		FileHelper.mkdir(name,currentUserID	);
	}
	@PostMapping("/uploadFile")
	@JsonReturn
	public Object uploadFile(@CookieValue("token") String token, MultipartFile file){
		return null;
	}
	@GetMapping("/registStorageServer")

}
