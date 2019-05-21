package com.sam.springaop.mapper;

import com.sam.springaop.pojo.Logtable;

public interface LogtableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logtable record);

    int insertSelective(Logtable record);

    Logtable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logtable record);

    int updateByPrimaryKey(Logtable record);
}