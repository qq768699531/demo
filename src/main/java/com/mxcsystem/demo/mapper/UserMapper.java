package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.base.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where PhoneNum = #{PhoneNum}")
    User getUserInfoByPhoneNum (String PhoneNum);

    //获取同组人员
    @Select("select * from user where GroupID = #{GroupID}")
    List<User> getGroupMember (User user);

    //获取同组管理人员
    @Select("select * from user where GroupID = #{GroupID} and isManager = 1")
    List<User> getGroupManager (User user);

    @Select("select Username from user")
    List<String> getUsername();

    //如果用户登陆过，获取用户的openid
    @Select("select openid from openid where PhoneNum = #{PhoneNum}")
    String getUserOpenID(User user);

    @Insert("insert into openid values(#{openid},#{phoneNum})")
    int insertOpenID (String openid, String phoneNum);

    @Update("update openid set OpenID = #{openid} where PhoneNum = #{phoneNum}")
    int updateOpenID (String openid, String phoneNum);

    @Insert("insert into user values(#{PhoneNum},#{Username},#{GroupID},#{isManager}," +
            "#{QQ},#{Weixin},#{Email},#{Hobby})")
    void insertUser(User user);



}
