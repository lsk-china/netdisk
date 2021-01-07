package com.lsk.netdisk.file.mappers;

import com.lsk.netdisk.file.model.File;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FilesMapper {
	@Select("select * from files where storagename=#{sname}")
	public File queryFileByStorageName(@Param("sname") String storageName);
	@Insert("insert into files(parentdir,storagename,filename,storageserver) values(#{storageName},#{fileName},#{storageServer},#{parentDir})")
	public void addFile(File file);
	@Delete("delete from files where id=#{id}")
	public void deleteFileById(@Param("id") Integer id);
	@Update("update set filename=#{filename} where id=#{id}")
	public void updateFileNameById(@Param("filename") String fileName, @Param("id") Integer id);
	@Select("select * from files where parentdir=#{parentdir} and ownerid=#{ownerid}")
	public List<File> ls(@Param("parentdir") String parentDir, @Param("ownerid") Integer ownerID);
	@Update("update set parentdir=#{parentdir} where id=#{id}")
	public void updateParentDirById(@Param("parentdir") String parentDir);

}
