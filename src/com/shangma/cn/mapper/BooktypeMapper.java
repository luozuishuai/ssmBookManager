package com.shangma.cn.mapper;

import com.shangma.cn.entity.Booktype;
import com.shangma.cn.entity.BooktypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooktypeMapper {
    int countByExample(BooktypeExample example);

    int deleteByExample(BooktypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(Booktype record);

    int insertSelective(Booktype record);

    List<Booktype> selectByExample(BooktypeExample example);

    Booktype selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") Booktype record, @Param("example") BooktypeExample example);

    int updateByExample(@Param("record") Booktype record, @Param("example") BooktypeExample example);

    int updateByPrimaryKeySelective(Booktype record);

    int updateByPrimaryKey(Booktype record);
}