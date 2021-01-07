package com.lsk.netdisk.gateway.mapper;

import com.lsk.netdisk.gateway.models.URL;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface URLsMapper {
	@Select("select * from urls")
	public List<URL> allURL();
	@Select("select * from urls where service=#{service}")
	public List<URL> queryURLByServiceName(@Param("service") String service);
	@Select("select * from urls where url=#{url}")
	public URL queryURLByPath(@Param("url") String url);

}
