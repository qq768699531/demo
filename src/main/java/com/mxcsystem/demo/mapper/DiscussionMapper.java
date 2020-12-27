package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Discussion;
import com.mxcsystem.demo.entity.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiscussionMapper {
    //根据审批ID获取讨论内容
    @Select("select * from discussion where ID = #{ID} and WorkItemType = 0")
    List<Discussion> getDiscussionListByApplyID(Apply apply);

    @Select("select * from discussion where ID = #{ID} and WorkItemType = 1")
    List<Discussion> getDiscussionListByLogID (Log log);

    @Delete("delete from discussion where ID = #{ID} and WorkItemType = 0")
    int deleteDiscussionsByApplyID (Apply apply);

    @Delete("delete from discussion where ID = #{ID} and WorkItemType = 1")
    int deleteDiscussionsByLogID (Log log);
}
