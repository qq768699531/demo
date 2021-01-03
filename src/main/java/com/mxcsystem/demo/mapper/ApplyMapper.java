package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.base.Apply;
import com.mxcsystem.demo.entity.base.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApplyMapper {
    @Insert("insert into apply " +
            "(Title,Departments,CreatedBy,CreatedDate,AssignedTo,Status,History," +
            "Reason,MissionStatement,Analysis,Attachments,CorrectiveActionPlan," +
            "Applyer,ApplyerOwner,ApplicationType,ApplicationAmount) " +
            "values" +
            "(#{Title},#{Departments},#{CreatedBy},NOW(),#{AssignedTo},#{Status},#{Status}," +
            "#{Reason},#{MissionStatement},#{Analysis},#{Attachments},#{CorrectiveActionPlan}," +
            "#{Applyer},#{ApplyerOwner},#{ApplicationType},#{ApplicationAmount})")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    void createNewApply(Apply apply);

    @Update("update apply set " +
            "History = CONCAT('1|',History)," +
            "Departments = #{Departments}," +
            "Title = #{Title}," +
            "Reason = #{Reason}," +
            "MissionStatement = #{MissionStatement}," +
            "Analysis = #{Analysis}," +
            "Attachments = #{Attachments}," +
            "CorrectiveActionPlan = #{CorrectiveActionPlan} " +
            "where ID = #{ID} and Status = 1")
    int updateApplyWhileNotSubmit(Apply apply);

    @Delete("delete from apply where ID = #{ID} and Status = 1")
    int deleteApplyWhileNotSubmit(Apply apply);

    @Update("update apply set " +
            "History = CONCAT('2|',History)," +
            "Status = 2," +
            "ActivatedDate = NOW()," +
            "ActivatedBy = #{ActivatedBy} " +
            "where ID = #{ID}")
    int submitApply(int ID);

    @Update("update apply set " +
            "Status = 3," +
            "History = CONCAT('3|',History)," +
            "ApplyerOwner = #{ApplyerOwner}," +
            "ApplyerOwnerNote = #{ApplyerOwnerNote}," +
            "ResolvedDate = NOW()," +
            "ResolvedBy = #{ResolvedBy} " +
            "where ID = #{ID}")
    int updateApplyByApplyerOwner(Apply apply);

    //废案
    @Update("update apply set " +
            "Status = 4," +
            "History = CONCAT('4|',History)," +
            "ApplyerManager = #{ApplyerManager}," +
            "ApplyerManagerNote = #{ApplyerManagerNote}," +
            "FinishDate = NOW()" +
            "ClosedDate = #{ClosedDate}," +
            "ClosedBy = #{ClosedBy} " +
            "where " +
            "ID = #{ID}")
    int updateApplyByApplyerManager(Apply apply);

    @Select("select * from apply where ID = #{ID}")
    Apply getApplyByID(int ID);

    //按照审批状态查询审批列表
    @Select("select * from apply where Status >= #{Status}")
    List<Apply> getApplyListLargerThanStatus(int Status);

    //按照用户手机号码查询审批列表
    @Select("select * from apply where CreatedBy = #{PhoneNum}")
    List<Apply> getApplyListCreatedByMe (String phoneNum);

    //按照分配给我的查询审批列表
    @Select("select * from apply where AssignedTo = #{PhoneNum}")
    List<Apply> getApplyListAssignToMe (User user);

    //按照分配给我的查询审批列表
    @Select("select * from apply where CreatedBy = #{PhoneNum}")
    List<Apply> getApplyListCreateByMe (User user);

    @Select("select * from apply order by CreatedDate desc limit 5")
    List<Apply> getNewest5Apply();
}
