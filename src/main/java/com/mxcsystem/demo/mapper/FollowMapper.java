package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Follow;
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

    @Insert("insert into follow values (#{PhoneNum},#{ID},#{WorkItemType},#{Status},#{Title},#{AssignTo})")
    int insertFollow(Follow follow);

    @Delete("delete from follow where ID = #{ID} and WorkItemType = #{WorkItemType}")
    int deleteFollow(Follow follow);

    @Update("update follow set Status = #{Status} where ID = #{ID} and WorkItemType = #{WorkItemType}")
    int updateFollowStatus(Follow follow);
}
