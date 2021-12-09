package com.wutl.toolcenter.poi;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/29 15:47
 */
@Controller
@RequestMapping("/poi")
public class ToolPoiController {


    @RequestMapping("/write")
    public void write() throws Exception {
        //创建Excel，超过100条数据会存入本地内存中，路径：C:\Users\Administrator\AppData\Local\Temp\poifiles
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        //2.创建sheet
        SXSSFSheet sheet = workbook.createSheet("sheet0");
        //3.row cell-- 创建行表头
        SXSSFRow title = sheet.createRow(0);
        //创建cell单元格--表头字段
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("地址");
        title.createCell(2).setCellValue("年龄");
        for (int i = 0; i < 1000; i++) {
            //添加表数据
            SXSSFRow dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue("张三");
            dataRow.createCell(1).setCellValue("河南省");
            dataRow.createCell(2).setCellValue("20");
        }
        //写入流  -- 文件路径自己修改
        FileOutputStream outputStream =
                new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\server\\222.xlsx"));
        workbook.write(outputStream);
        outputStream.flush();
        System.out.println("传输完成");
    }
}
