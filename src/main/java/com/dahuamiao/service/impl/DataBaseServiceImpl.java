package com.dahuamiao.service.impl;

import com.dahuamiao.mapper.DataBaseMapper;
import com.dahuamiao.pojo.ColumnInfo;
import com.dahuamiao.pojo.TableInfo;
import com.dahuamiao.service.DataBaseService;
import com.dahuamiao.utils.TableInfo2ExcelUtil;
import com.dahuamiao.utils.TableType;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class DataBaseServiceImpl implements DataBaseService {

    @Autowired
    DataBaseMapper dataBaseMapper;

    @Override
    public void createTablesInfoExcel(String firstSheetName, String generateFilePath, String fileName, String dataBaseName) throws Exception{
        Assert.notNull(firstSheetName, "firstSheetName can not be null!");
        Assert.notNull(generateFilePath, "generateFilePath can not be null!");
        Assert.notNull(fileName, "fileName can not be null!");
        File filePath = new File(generateFilePath);
        Assert.isTrue(filePath.isDirectory(), "filePath illegal!");

        if (!filePath.exists()){
            filePath.mkdir();
        }

        List<TableInfo> tables = dataBaseMapper.queryTableInfoList(dataBaseName, TableType.BASETABLE);

        File file = new File(generateFilePath + File.separator + fileName);

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFFont font = workbook.createFont();
        font.setUnderline(XSSFFont.U_DOUBLE);
        font.setColor(IndexedColors.RED.getIndex());

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);

        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.cloneStyleFrom(cellStyle);
        cellStyle2.setFont(font);
        XSSFSheet sheet = workbook.createSheet(firstSheetName);

        int rowNum = 4;
        for( int i = 0; i < tables.size(); i++){
            TableInfo table = tables.get(i);
            XSSFCreationHelper creationHelper = workbook.getCreationHelper();
            XSSFHyperlink hyperlink = creationHelper.createHyperlink(HyperlinkType.DOCUMENT);
            hyperlink.setAddress("#"+ table.getTableName() +"!A10");
            sheet.setColumnWidth(4, 40 * 256);
            sheet.setColumnWidth(5, 40 * 256);
            XSSFRow row = sheet.createRow(rowNum);
            XSSFCell cell3 = row.createCell(3);
            XSSFCell cell4 = row.createCell(4);
            XSSFCell cell5 = row.createCell(5);
            cell3.setCellValue(i+1);
            cell3.setCellStyle(cellStyle);
            cell4.setCellStyle(cellStyle2);
            cell4.setCellValue(table.getTableName());
            cell4.setHyperlink(hyperlink);
            cell5.setCellStyle(cellStyle);
            cell5.setCellValue(table.getTableComment());


            ++rowNum;
        }


        for(int i = 0; i < tables.size(); i++){
            TableInfo table = tables.get(i);
            List<ColumnInfo> details = dataBaseMapper.queryColumnInfoList(dataBaseName, table.getTableName());
            TableInfo2ExcelUtil.createTableInfoExcel(firstSheetName, table, details,workbook, i);
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            workbook.write(fileOutputStream);
        }

    }
}
