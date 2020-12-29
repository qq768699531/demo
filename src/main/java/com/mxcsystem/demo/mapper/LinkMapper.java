package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Link;
import com.mxcsystem.demo.entity.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LinkMapper {
    //根据审批ID获取link
    @Select("select * from link where ID = #{ID} and WorkItemType = 0")
    List<Link> getApplyLinkListByApplyID (Apply apply);

    @Select("select * from link where ID = #{ID} and WorkItemType = 1")
    List<Link> getLogLinkListByLogID (Log log);

    @Delete("delete from link where ID = #{ID} and WorkItemType = 0")
    int deleteApplyLinksByApplyID (Apply apply);

    @Delete("delete from link where ID = #{ID} and WorkItemType = 1")
    int deleteLogLinksByLogID (Log log);

    @Select("select * from link where " +
            "ID = #{ID} and " +
            "WorkItemType = #{WorkItemType} and " +
            "LinkID = #{LinkID} and " +
            "LInkWorkItemType = #{LInkWorkItemType}")
    List<Link> getLinkListByLink (Link link);

    @Insert("insert into link values(#{ID},#{WorkItemType},#{LinkID},#{LinkWorkItemType})")
    int insertLink (Link link);
}
