package com.dahuamiao.service.impl;

import com.dahuamiao.mapper.DataBaseMapper;
import com.dahuamiao.service.DataBaseService;
import com.dahuamiao.utils.TableInfo2ExcelUtil;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@Service
public class DataBaseServiceImpl implements DataBaseService {

    @Autowired
    DataBaseMapper dataBaseMapper;

    @Override
    public void createTablesInfoExcel() throws Exception{

        String firstSheetName = "LITE_LTMS";
        List<Map<String, String>> tables = dataBaseMapper.queryTableInfoList();
        File file = new File("D://LITE-LTMS.xlsx");
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
            Map<String, String> table = tables.get(i);
            XSSFCreationHelper creationHelper = workbook.getCreationHelper();
            XSSFHyperlink hyperlink = creationHelper.createHyperlink(HyperlinkType.DOCUMENT);
            hyperlink.setAddress("#"+ table.get("table_name") +"!A10");
            sheet.setColumnWidth(4, 40 * 256);
            sheet.setColumnWidth(5, 40 * 256);
            XSSFRow row = sheet.createRow(rowNum);
            XSSFCell cell3 = row.createCell(3);
            XSSFCell cell4 = row.createCell(4);
            XSSFCell cell5 = row.createCell(5);
            cell3.setCellValue(i+1);
            cell3.setCellStyle(cellStyle);
            cell4.setCellStyle(cellStyle2);
            cell4.setCellValue(table.get("table_name"));
            cell4.setHyperlink(hyperlink);
            cell5.setCellStyle(cellStyle);
            cell5.setCellValue(table.get("table_comment"));


            ++rowNum;
        }


        for(int i = 0; i < tables.size(); i++){
            Map<String, String> table = tables.get(i);
            List<Map<String, Object>> details = dataBaseMapper.queryTableDetails(table.get("table_name"));
            TableInfo2ExcelUtil.createTableInfoExcel(firstSheetName, table, details,workbook, i);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            workbook.write(fileOutputStream);
        }finally {
            fileOutputStream.flush();
            fileOutputStream.close();
        }

    }
}
