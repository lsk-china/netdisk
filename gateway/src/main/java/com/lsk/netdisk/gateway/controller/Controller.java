package com.lsk.netdisk.gateway.controller;

import com.lsk.netdisk.gateway.helper.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	private RequestHelper requestHelper;
	@RequestMapping("/auth/*")
	public Object auth( ){
		return null;
	}
}
