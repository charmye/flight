package com.hc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.User;

@Mapper
public interface UserDao {
	
	
	/**
	 * 查看所有用户
	 * 
	 * 结果映射
	 *	@Results({
	 *		@Result(property="nnNn",column="NN_NN")    
	 *	})
	 *
	 *@Option注解获得主键返回值
	 *@Options(useGeneratedKeys = true, keyProperty = "userId")
	 * 
	 */
	@Select("SELECT * FROM user")
	List<User> findAll();
	
	/**
	 * 通过id查询用户
	 */
	@Select("SELECT * FROM user WHERE userId = #{userId}")
	User findUserById(@Param("userId") int userId);
	
	/**
	 * 添加用户
	 * @return 
	 */
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	@Insert("INSERT INTO user(username, password, phone,email,fileurl) "
			+ "VALUES(#{username}, #{password}, #{phone}, #{email},#{fileurl})")
	Integer insert(User user);
	
	/**
	 * 登陆
	 * */
	@Select("SELECT * FROM user WHERE username = #{username} and password=#{password}")
	User findByUsernameAndPassowrd(User user);
	
	
	/**
	 * 通过id修改用户信息
	 */
	@Update("update user set username=#{username},password=#{password}"
			+ ",phone=#{phone},email=#{email},fileurl=#{fileurl} where userId=#{userId}")
	void update(User user);
	
	/**
	 * 通过username修改用户信息
	 */
	@Update("update user set password=#{password}"
			+ ",phone=#{phone},email=#{email},fileurl=#{fileurl} where username=#{username}")
	void updateByUsername(User user);
	
	/**
	 *
	 * 通过id删除用户
	 * 
	 */
	@Delete("delete from user where userid=#{userId}")
	void delete(int userId);

	/**
	 * 通过username查询用户是否存在且返回userId 
	 */
	@Select("SELECT userId FROM user WHERE username = #{username}")
	Integer findByUsername(@Param("username")String username);
	
	
	
}
