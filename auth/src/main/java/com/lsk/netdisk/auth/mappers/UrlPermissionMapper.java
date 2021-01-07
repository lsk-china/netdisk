package com.lsk.netdisk.auth.mappers;

import com.lsk.netdisk.auth.model.UrlPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UrlPermissionMapper {
	@Select("select * from url_role where url=#{url}")
	public UrlPermission queryPermissionByURL(@Param("url") String url);
}
