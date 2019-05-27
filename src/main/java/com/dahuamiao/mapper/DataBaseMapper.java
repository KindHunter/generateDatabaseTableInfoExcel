package com.dahuamiao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataBaseMapper {


    public List<Map<String, String>> queryTableInfoList();

    public List<Map<String, Object>> queryTableDetails(String tableName);

}
