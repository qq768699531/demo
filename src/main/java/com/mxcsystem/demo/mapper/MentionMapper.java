package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.Mention;
import com.mxcsystem.demo.entity.Log;
import com.mxcsystem.demo.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MentionMapper {

    //查询用户提及列表
    @Select("select * from mention where PhoneNum = #{PhoneNum}")
    List<Mention> getMentionListByPhoneNumber (User user);

    //根据审批ID查询提及对象
    @Select("select * from mention where ID = #{ID} and WorkItemType = 0")
    List<Mention> getMentionListByApplyID(Apply apply);

    //根据日志ID查询提及对象
    @Select("select * from mention where ID = #{ID} and WorkItemType = 1")
    List<Mention> getMentionListByLogID(Log log);

    @Insert("insert into mention values" +
            "(#{PhoneNum},#{ID},#{WorkItemType},#{Status},#{Title},#{AssignTo})")
    int insertMention(Mention mention);

    @Select("select * from mention where " +
            "PhoneNum = #{PhoneNum} and " +
            "ID = #{ID} and " +
            "WorkItemType = #{WorkItemType} and " +
            "Status = #{Status} and " +
            "Title = #{Title} and " +
            "AssignTo = #{AssignTo}")
    List<Mention> getMentionListByMention (Mention mention);

    @Delete("delete from mention where ID = #{ID} and WorkItemType = 0")
    int deleteMentionsByApplyID (Apply apply);

    @Delete("delete from mention where ID = #{ID} and WorkItemType = 1")
    int deleteMentionsByLogID (Log log);
}
