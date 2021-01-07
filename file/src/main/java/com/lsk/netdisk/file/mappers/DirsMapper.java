package com.lsk.netdisk.file.mappers;

import com.lsk.netdisk.file.model.Dir;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DirsMapper {
	@Select("select * from dirs where ownerid=#{ownerid} and parent=#{parent}")
	public List<Dir> lsDirs(@Param("ownerid") Integer ownerID, @Param("parent") String parent);
	@Insert("insert into dirs(name,ownerid,parent) values(#{name},#{ownerID},#{parent})")
	public void addDir(Dir dir);
	@Update("update set name=#{name} parent=#{parent} where id=#{id}")
	public void updateDirById(Dir dir);
	@Delete("delete from dirs where id=#{id}")
	public void deleteDirById(@Param("id") Integer id);
}
