package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Log;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {
    @Insert("insert into log " +
            "(Title,Departments,CreatedBy,CreatedDate,AssignedTo,Status," +
            "LogDate,Record,Defect,Attachments) " +
            "values" +
            "(#{Title},#{Departments},#{CreatedBy},#{CreateDate},#{AssignedTo},1," +
            "NOW(),#{Record},#{Defect},#{Attachments})")
    @Options(useGeneratedKeys = true, keyProperty = "ID")
    int createNewLog(Log log);

    @Update("update log set " +
            "History = CONCAT(#{History},'|1')," +
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
            "History = CONCAT(#{History},'|2')," +
            "Status = 2," +
            "ActivatedDate = NOW()," +
            "ActivatedBy = #{ActivatedBy} " +
            "where ID = #{ID}")
    int submitLog(int ID);

    @Select("select * from log where ID = #{ID}")
    Log getLogByID(int ID);

    //按照用户手机号码查询日志列表
    @Select("select * from log where CreatedBy = #{PhoneNum}")
    List<Log> getLogListByPhoneNum (String phoneNum);
}
