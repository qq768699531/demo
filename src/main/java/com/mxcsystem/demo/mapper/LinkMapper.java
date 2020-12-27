package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Link;
import com.mxcsystem.demo.entity.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LinkMapper {
    //根据审批ID获取link
    @Select("select * from link where ID = #{ID} and WorkItemType = 0")
    List<Link> getLinkListByApplyID(Apply apply);

    @Select("select * from link where ID = #{ID} and WorkItemType = 1")
    List<Link> getLinkListByLogID (Log log);

    @Delete("delete * from link where ID = #{ID} and WorkItemType = 0")
    int deleteLinksByApplyID (Apply apply);

    @Delete("delete from link where ID = #{ID} and WorkItemType = 1")
    int deleteLinksByLogID (Log log);
}
