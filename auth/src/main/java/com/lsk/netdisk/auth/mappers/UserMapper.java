package com.lsk.netdisk.auth.mappers;

import com.lsk.netdisk.auth.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
	@Select("select * from users where name=#{name}")
	public User queryUserByName(@Param("name") String name);
	@Select("select * from users where id=#{id}")
	public User queryUserById(@Param("id") Integer id);
	@Insert("insert into users(name,role,password,email) values(#{name},#{role},#{password},#{email})")
	public void addUser(User user);
	@Update("update users set name=#{name},role=#{role},password=#{password},email=#{email} where id=#{id}")
	public void updateUser(User user);
	@Delete("delete from users where id=#{id}")
	public void delete(@Param("id") Integer id);
	@Update("update users set state=1 where id=#{id}")
	public void enableUser(@Param("id") Integer id);

}
