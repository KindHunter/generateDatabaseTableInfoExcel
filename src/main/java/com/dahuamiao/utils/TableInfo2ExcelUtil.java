package com.dahuamiao.utils;

import com.dahuamiao.pojo.ColumnInfo;
import com.dahuamiao.pojo.TableInfo;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;
import java.util.Map;

public class TableInfo2ExcelUtil {


    public static void createTableInfoExcel(String firstSheetName, TableInfo tableInfo, List<ColumnInfo> columnInfos, XSSFWorkbook workbook, int i){


        XSSFSheet sheet = workbook.createSheet(tableInfo.getTableName());

        XSSFFont font = workbook.createFont();
        font.setUnderline(XSSFFont.U_DOUBLE);
        font.setColor(IndexedColors.RED.getIndex());
        XSSFCreationHelper creationHelper = workbook.getCreationHelper();
        XSSFHyperlink hyperlink = creationHelper.createHyperlink(HyperlinkType.DOCUMENT);
        String linkAddress = "#" + firstSheetName + "!E" + (i + 5);
        hyperlink.setAddress(linkAddress);
        CellStyle linkCellStyle = workbook.createCellStyle();
        linkCellStyle.setFont(font);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);


        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        XSSFRow row1 = sheet.createRow(0);
        XSSFCell cell1 = row1.createCell(0);
        cell1.setCellStyle(cellStyle);
        XSSFCell cell2 = row1.createCell(1);
        cell2.setCellStyle(cellStyle);
        cell1.setCellValue(tableInfo.getTableName());
        cell2.setCellValue(tableInfo.getTableComment());
        XSSFCell cell3 = row1.createCell(2);
        cell3.setCellStyle(linkCellStyle);
        cell3.setCellValue("返回");
        cell3.setHyperlink(hyperlink);

        XSSFRow row2 = sheet.createRow(1);
        XSSFCell c1 = row2.createCell(0);
        c1.setCellStyle(cellStyle);
        c1.setCellValue("列名");
        XSSFCell c2 = row2.createCell(1);
        c2.setCellStyle(cellStyle);
        c2.setCellValue("数据类型");
        XSSFCell c3 = row2.createCell(2);
        c3.setCellStyle(cellStyle);
        c3.setCellValue("字段类型");
        XSSFCell c4 = row2.createCell(3);
        c4.setCellStyle(cellStyle);
        c4.setCellValue("长度");
        XSSFCell c5 = row2.createCell(4);
        c5.setCellStyle(cellStyle);
        c5.setCellValue("是否可为空");
        XSSFCell c6 = row2.createCell(5);
        c6.setCellStyle(cellStyle);
        c6.setCellValue("默认值");
        XSSFCell c7 = row2.createCell(6);
        c7.setCellStyle(cellStyle);
        c7.setCellValue("备注");

        int rowNum = 2;
        for( ColumnInfo column: columnInfos){
            XSSFRow row = sheet.createRow(rowNum);
            XSSFCell cc1 = row.createCell(0);
            cc1.setCellStyle(cellStyle);
            cc1.setCellValue(String.valueOf(column.getColumnName()));
            XSSFCell cc2 = row.createCell(1);
            cc2.setCellStyle(cellStyle);
            cc2.setCellValue(String.valueOf(column.getColumnType()));
            XSSFCell cc3 = row.createCell(2);
            cc3.setCellStyle(cellStyle);
            cc3.setCellValue(String.valueOf(column.getDataType()));
            XSSFCell cc4 = row.createCell(3);
            cc4.setCellStyle(cellStyle);
            cc4.setCellValue(String.valueOf(column.getCharacterMaximumLength()));
            XSSFCell cc5 = row.createCell(4);
            cc5.setCellStyle(cellStyle);
            cc5.setCellValue(String.valueOf(column.getIsNullable()));
            XSSFCell cc6 = row.createCell(5);
            cc6.setCellStyle(cellStyle);
            cc6.setCellValue(String.valueOf(column.getColumnDefault()));
            XSSFCell cc7 = row.createCell(6);
            cc7.setCellStyle(cellStyle);
            cc7.setCellValue(String.valueOf(column.getColumnComment()));

            ++rowNum;

        }
    }
}
