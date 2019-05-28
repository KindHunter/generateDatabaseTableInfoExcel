package com.dahuamiao.mapper;

import com.dahuamiao.pojo.ColumnInfo;
import com.dahuamiao.pojo.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataBaseMapper {


    /**
     * query all table name and discription
     * @param dataBaseName
     * @param tableType
     * @return
     */
    List<TableInfo> queryTableInfoList(@Param("dataBaseName") String dataBaseName,
                                              @Param("tableType") String tableType);

    /**
     * query column info by table name and database name
     * @param dataBaseName
     * @param tableName
     * @return
     */
    List<ColumnInfo> queryColumnInfoList(@Param("dataBaseName") String dataBaseName,
                                         @Param("tableName") String tableName);


}
