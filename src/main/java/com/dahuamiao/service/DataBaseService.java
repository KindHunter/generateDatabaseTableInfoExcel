package com.dahuamiao.service;


public interface DataBaseService {

    /**
     * 生成一個數據庫的所有表結構到一個Excel文件裡
     * @param firstSheetName 目錄頁名稱
     * @param generateFilePath 生成Excel路徑
     * @param fileName Excel文件名
     * @param dataBaseName 數據庫名稱
     * @throws Exception
     */
    void createTablesInfoExcel(String firstSheetName, String generateFilePath, String fileName, String dataBaseName) throws Exception;
}
