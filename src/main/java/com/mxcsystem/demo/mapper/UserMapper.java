package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where PhoneNum = #{PhoneNum}")
    User getUserInfo(String PhoneNum);
}
