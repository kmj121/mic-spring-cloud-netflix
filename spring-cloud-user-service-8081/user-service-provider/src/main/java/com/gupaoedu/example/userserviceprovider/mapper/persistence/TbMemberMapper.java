package com.gupaoedu.example.userserviceprovider.mapper.persistence;

import com.gupaoedu.example.userserviceprovider.mapper.entitys.TbMember;
import com.gupaoedu.example.userserviceprovider.mapper.entitys.TbMemberExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TbMemberMapper {
    long countByExample(TbMemberExample example);

    int deleteByExample(TbMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbMember record);

    int insertSelective(TbMember record);

    List<TbMember> selectByExample(TbMemberExample example);

    TbMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbMember record, @Param("example") TbMemberExample example);

    int updateByExample(@Param("record") TbMember record, @Param("example") TbMemberExample example);

    int updateByPrimaryKeySelective(TbMember record);

    int updateByPrimaryKey(TbMember record);
}