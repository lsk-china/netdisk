package com.lsk.netdisk.auth.authz;

import com.lsk.netdisk.auth.authc.Authc;
import com.lsk.netdisk.auth.mappers.UrlPermissionMapper;
import com.lsk.netdisk.auth.model.UrlPermission;
import com.lsk.netdisk.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Authz {
	@Autowired
	private UrlPermissionMapper mapper;

	public boolean hasPermission(String url,String token){
		User user = Authc.getCurrentUser(token);
		String role = user.getRole();
		Permission userPermission = Permission.valueOf(role);
		UrlPermission urlPermission = mapper.queryPermissionByURL(url);
		Permission targetPermission = Permission.valueOf(urlPermission.getRole());
		return userPermission.hasPermission(targetPermission);
	}


}
