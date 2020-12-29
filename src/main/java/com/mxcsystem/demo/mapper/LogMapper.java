package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Log;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    @Insert("insert into log " +
            "(Title,Departments,AssignedTo,Status," +
            "LogDate,Record,Defect,Attachments,History," +
            "CreatedBy,CreatedDate,ActivatedDate,ActivatedBy,ClosedDate,ClosedBy,ResolvedDate,ResolvedBy) " +
            "values" +
            "(#{Title},#{Departments},#{AssignedTo},#{Status}," +
            "NOW(),#{Record},#{Defect},#{Attachments},#{Status}," +
            "#{CreatedBy},#{CreatedDate},#{ActivatedDate},#{ActivatedBy},#{ClosedDate},#{ClosedBy},#{ResolvedDate},#{ResolvedBy})")
    @Options(useGeneratedKeys = true, keyProperty = "ID")
    int createNewLog(Log log);

    @Update("update log set " +
            "History = CONCAT('1|',History)," +
            "Departments = #{Departments}," +
            "Title = #{Title}," +
            "Record = #{Record}," +
            "Defect = #{Defect}," +
            "Attachments = #{Attachments}," +
            "CorrectiveActionPlan = #{CorrectiveActionPlan} " +
            "where " +
            "ID = #{ID} and Status = 1")
    int updateLogWhileNotSubmit(Log log);

    @Delete("delete from log where ID = #{ID} and Status = 1")
    int deleteLogWhileNotSubmit(Log log);

    @Update("update log set " +
            "History = CONCAT('2|',History)," +
            "Status = 2," +
            "ActivatedDate = NOW()," +
            "ActivatedBy = #{ActivatedBy} " +
            "where ID = #{ID}")
    int submitLog(int ID);

    @Select("select * from log where ID = #{ID}")
    Log getLogByID(int ID);

    //按照用户手机号码查询日志列表
    @Select("select * from log where CreatedBy = #{PhoneNum}")
    List<Log> getLogListCreatedByMe (String phoneNum);

    @Update("update log set Status = #{Status}," +
            "History = CONCAT(#{Status},'|',History) " +
            "where ID = #{ID}")
    int updateLogStatusByLogID(Log log);

}
