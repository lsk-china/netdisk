package com.lsk.netdisk.file.mappers;

import com.lsk.netdisk.file.model.Server;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ServersMapper {
	@Select("select * from servers order by p")
	public List<Server> queryAllServers();
	@Select("select * from servers where id=#{id}")
	public Server queryServerById(@Param("id") Integer id);
}
