package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where PhoneNum = #{PhoneNum}")
    User getUserInfo(User user);

    //获取同组人员
    @Select("select * from user where GroupID = #{GroupID}")
    List<User> getGroupMember (User user);

    //获取同组管理人员
    @Select("select * from user where GroupID = #{GroupID} and isManager = 1")
    List<User> getGroupManager (User user);
}
