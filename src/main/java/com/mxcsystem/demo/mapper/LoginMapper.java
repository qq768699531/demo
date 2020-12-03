package com.mxcsystem.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {
    @Select("select * from worker where name = #{username} and password = #{password}")
    boolean checkLogin(String username,String password);
}
