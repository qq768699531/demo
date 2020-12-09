package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper {
    @Insert("insert into log (Attachments,Departments,Title,CreatedBy,LogDate) values(")
    public int createNewLog(@Param("log") Log log);
}
