package com.lsk.netdisk.gateway.mapper;

import com.lsk.netdisk.gateway.models.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ParamMapper {
	@Select("select * from param where urlID=#{urlID}")
	public List<Param> queryParamsByUrlID(@org.apache.ibatis.annotations.Param("urlID") Integer url);
}
