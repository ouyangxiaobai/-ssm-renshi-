package com.mty.cc.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: excel工具类
 * @Author:
 * @date:2019/4/23 16:16
 */
@Component
public class ExcelUtil {

    public static void main(String[] args) throws Exception {

        ExcelUtil export = new ExcelUtil();
        String srcFilePath = "F:/I11_固有担保关系表.xlsx";
        String fileName = "test_" + System.currentTimeMillis() + ".xlsx";
        String desFilePath = "F:/" + fileName;
        export.exportExcel(srcFilePath,desFilePath);
    }

        //根据指定的excel模板导出数据
        public void exportExcel(String srcFilePath, String desFilePath) throws Exception {
        //创建Excel文件的输入流对象
        FileInputStream fis = new FileInputStream(srcFilePath);
        //根据模板创建excel工作簿
        XSSFWorkbook workBook = new XSSFWorkbook(fis);
        //创建Excel文件输出流对象
        FileOutputStream fos = new FileOutputStream(desFilePath);
        //获取创建的工作簿第一页
        XSSFSheet sheet = workBook.getSheetAt(0);
        //给指定的sheet命名
        workBook.setSheetName(0,"2016-11-30");

        //修改标题
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        //获取指定单元格值
        String s = cell.getStringCellValue();
        cell.setCellValue(s);//修改

        //获取当前sheet最后一行数据对应的行索引
        int currentLastRowIndex = sheet.getLastRowNum();
        int newRowIndex = currentLastRowIndex + 1;
        XSSFRow newRow = sheet.createRow(newRowIndex);
        //开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始
        int cellIndex = 0;

        //创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
        XSSFCell newNameCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
        newNameCell.setCellValue("乔玉宝");
        XSSFCell newGenderCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
        newGenderCell.setCellValue("男");
        XSSFCell newAgeCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
        newAgeCell.setCellValue(25);
        XSSFCell newAddressCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
        newAddressCell.setCellValue("重庆市渝北区");

        workBook.write(fos);

        //关闭流
        fis.close();
        fos.flush();
        fos.close();
        System.out.println("导出成功");
        }




    //根据指定的excel模板导出数据
    public void exportExcels(String srcFilePath, String desFilePath, List<Map<String,Object>> data) throws Exception {
        //创建Excel文件的输入流对象
        FileInputStream fis = new FileInputStream(srcFilePath);
        //根据模板创建excel工作簿
        XSSFWorkbook workBook = new XSSFWorkbook(fis);
        //创建Excel文件输出流对象
        FileOutputStream fos = new FileOutputStream(desFilePath);
        //获取创建的工作簿第一页
        XSSFSheet sheet = workBook.getSheetAt(0);
        //给指定的sheet命名
        workBook.setSheetName(0,"2016-11-30");

        //修改标题
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        //获取指定单元格值
        String s = cell.getStringCellValue();
        cell.setCellValue(s);//修改

        //获取当前sheet最后一行数据对应的行索引
        int currentLastRowIndex = sheet.getLastRowNum();
        int newRowIndex = currentLastRowIndex + 1;
        for(int i = 0;i<data.size();i++){
            XSSFRow newRow = sheet.createRow(newRowIndex);
            //开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始
            int cellIndex = 0;
            Map<String, Object> mp = data.get(i);
            for(String key:mp.keySet()){
                //创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
                XSSFCell newNameCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
                if(mp.get(key)==null){
                    newNameCell.setCellValue("");
                }else{
                    newNameCell.setCellValue(mp.get(key).toString());
                }
            }
            newRowIndex++;
        }
        workBook.write(fos);
        //关闭流
        fis.close();
        fos.flush();
        fos.close();
        System.out.println("导出路径："+desFilePath);
        System.out.println("模版"+srcFilePath+"导出成功");
    }








}
