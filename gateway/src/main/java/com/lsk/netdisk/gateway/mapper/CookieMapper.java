package com.lsk.netdisk.gateway.mapper;

import com.lsk.netdisk.gateway.models.Cookie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CookieMapper {
	@Select("select * from cookies where urlID=#{urlID}")
	public List<Cookie> queryCookiesByURLID(@Param("urlID") Integer urlID);
}
