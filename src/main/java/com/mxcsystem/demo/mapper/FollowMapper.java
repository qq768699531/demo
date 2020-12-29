package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Follow;
import com.mxcsystem.demo.entity.Log;
import com.mxcsystem.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FollowMapper {

    //查询用户关注列表
    @Select("select * from follow where phoneNum = #{phoneNum}")
    List<Follow> getFollowListByPhoneNumber(User user);

    @Insert("insert into follow values (#{PhoneNum},#{ID},#{WorkItemType})")
    int insertFollow(Follow follow);

    @Delete("delete from follow where ID = #{ID} and WorkItemType = #{WorkItemType}")
    int deleteFollow(Follow follow);

    @Delete("delete from follow where ID = #{ID} and WorkItemType = 0")
    int deleteAllFollowFromApply (Apply apply);

    @Delete("delete from follow where ID = #{ID} and WorkItemType = 1")
    int deleteAllFollowFromLog (Log log);
}
