package com.example.demo.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * 直播报告计算生成EXCEL
 * 
 * @author zhanglei
 *
 */
public class ExcelStyle {

	/**
	 * 基础样式
	 * 
	 * @param wb
	 * @return
	 */
	static CellStyle createCellStyle(Workbook wb) {
		CellStyle contentStyle = wb.createCellStyle();
		Font conFont = wb.createFont();
		conFont.setFontName("宋体");
		conFont.setFontHeightInPoints((short) 10);
		contentStyle.setFont(conFont);
		return contentStyle;
	}

	/**
	 * 标题样式
	 * 
	 * @param workbook
	 * @return
	 */
	static CellStyle createCellStyleTitle(Workbook workbook) {
		XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
		cellStyle.cloneStyleFrom(createCellStyle(workbook));
		Font headerFont = workbook.createFont();
		headerFont.setFontName("宋体");
		headerFont.setFontHeightInPoints((short) 12);
		cellStyle.setFont(headerFont);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor((short) 42); //前景填充色
		return cellStyle;
	}
}
