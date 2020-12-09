package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper {
    @Insert("insert into log " +
            "(Title,Departments,CreatedBy,CreatedDate,AssignedTo,Status," +
            "LogDate,Record,Defect,Attachments) " +
            "values" +
            "(#{log.Title},#{log.Departments},#{log.CreatedBy},#{log.CreateDate},#{log.AssignedTo},#{log.Status}," +
            "#{log.LogDate},#{log.Record},#{log.Defect},#{log.Attachments})")
    @Options(useGeneratedKeys = true, keyProperty = "ID")
    public int createNewLog(@Param("log") Log log);

}
