package com.dahuamiao.service.impl;

import com.dahuamiao.GenerateTableInfoExcelApplication;
import com.dahuamiao.service.DataBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GenerateTableInfoExcelApplication.class)
public class DataBaseServiceImplTest {

    @Autowired
    DataBaseService dataBaseService;


    @Test
    public void createTablesInfoExcel() throws Exception {
        dataBaseService.createTablesInfoExcel("ltms_lite",
                "D://",
                "ltms_lite.xlsx",
                "ltms_lite");
    }
}