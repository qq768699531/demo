package com.mxcsystem.demo.mapper;

import com.mxcsystem.demo.entity.Mention;
import com.mxcsystem.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MentionMapper {

    //查询用户提及列表
    @Select("select * from mention where phoneNum = #{phoneNum}")
    List<Mention> getMentionListByPhoneNumber (User user);
}
