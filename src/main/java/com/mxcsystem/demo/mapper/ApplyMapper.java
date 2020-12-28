package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApplyMapper {
    //用户创建新的审批
    @Insert("insert into apply " +
            "(Title,Departments,CreatedBy,CreatedDate,AssignedTo,Status,History," +
            "Reason,MissionStatement,Analysis,Attachments,CorrectiveActionPlan," +
            "Applyer,ApplicationType,ApplicationAmount) " +
            "values" +
            "(#{Title},#{Departments},#{CreatedBy},NOW(),#{AssignedTo},1,1," +
            "#{Reason},#{MissionStatement},#{Analysis},#{Attachments},#{CorrectiveActionPlan}," +
            "#{Applyer},#{ApplicationType},#{ApplicationAmount})")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    void createNewApply(Apply apply);

    @Update("update apply set " +
            "History = CONCAT(#{History},'|1')," +
            "Departments = #{Departments}," +
            "Title = #{Title}," +
            "Reason = #{Reason}," +
            "MissionStatement = #{MissionStatement}," +
            "Analysis = #{Analysis}," +
            "Attachments = #{Attachments}," +
            "CorrectiveActionPlan = #{CorrectiveActionPlan} " +
            "where " +
            "ID = #{ID} and Status = 1")
    int updateApplyWhileNotSubmit(Apply apply);

    @Delete("delete from apply where ID = #{ID} and Status = 1")
    int deleteApplyWhileNotSubmit(Apply apply);

    @Update("update apply set " +
            "History = CONCAT(#{History},'|2')," +
            "Status = 2," +
            "ActivatedDate = NOW()," +
            "ActivatedBy = #{ActivatedBy} " +
            "where " +
            "ID = #{ID}")
    int submitApply(int ID);

    @Update("update apply set " +
            "Status = 3," +
            "History = CONCAT(#{History},'|3')," +
            "ApplyerOwner = #{ApplyerOwner}," +
            "ApplyerOwnerNote = #{ApplyerOwnerNote}," +
            "ResolvedDate = NOW()," +
            "ResolvedBy = #{ResolvedBy} " +
            "where " +
            "ID = #{ID}")
    int updateApplyByApplyerOwner(Apply apply);

    //废案
    @Update("update apply set " +
            "Status = 4," +
            "History = CONCAT(#{History},'|4')," +
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
    List<Apply> getApplyListByPhoneNum (String phoneNum);

    //按照分配给我的查询审批列表
    @Select("select * from apply where AssignedTo = #{PhoneNum}")
    List<Apply> getApplyListAssignToMe (User user);

    //按照分配给我的查询审批列表
    @Select("select * from apply where CreatedBy = #{PhoneNum}")
    List<Apply> getApplyListCreateByMe (User user);
}
