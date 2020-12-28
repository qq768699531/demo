package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Follow;
import com.mxcsystem.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FollowMapper {

    //查询用户关注列表
    @Select("select * from follow where phoneNum = #{phoneNum}")
    List<Follow> getFollowListByPhoneNumber(User user);

    @Insert("insert into follow values (#{PhoneNum},#{ID},#{WorkItemType},#{Status},#{Title},#{AssignTo})")
    void insertFollow (Follow follow);
}
