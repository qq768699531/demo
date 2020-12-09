package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Apply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ApplyMapper {

    @Insert("insert into apply " +
            "(Title,Departments,CreatedBy,CreatedDate,AssignedTo,Status," +
            "Reason,MissionStatement,Analysis,Attachments,CorrectiveActionPlan," +
            "Applyer,ApplicationType,ApplicationAmount) " +
            "values" +
            "(#{Title},#{Departments},#{CreatedBy},#{CreatedDate},#{AssignedTo},#{Status}," +
            "#{Reason},${MissionStatement},#{Analysis},#{Attachments},#{CorrectiveActionPlan}," +
            "#{Applyer},#{ApplicationType},#{ApplicationAmount})")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    void createNewApply(Apply apply);

    @Update("update apply set " +
            "ApplyerOwner = #{ApplyerOwner}," +
            "ApplyerOwnerNote = #{ApplyerOwnerNote}," +
            "ActivatedDate = #{ActivatedDate}," +
            "ActivatedBy = #{ActivatedBy} " +
            "where" +
            "ID = #{ID}")
    int updateApplyByApplyerOwner(Apply apply);

    @Update("update apply set " +
            "ApplyerManager = #{ApplyerManager}," +
            "ApplyerManagerNote = #{ApplyerManagerNote}," +
            "ResolvedDate = #{ResolvedDate}," +
            "ResolvedBy = #{ResolvedBy} " +
            "where" +
            "ID = #{ID}")
    int updateApplyByApplyerManager(Apply apply);

    @Update("update apply set " +
            "ApplyerManager = #{ApplyerManager}," +
            "ApplyerManagerNote = #{ApplyerManagerNote}," +
            "ClosedDate = #{ClosedDate}," +
            "ClosedBy = #{ClosedBy} " +
            "where" +
            "ID = #{ID}")
    int updateApplyToClose(Apply apply);
}
