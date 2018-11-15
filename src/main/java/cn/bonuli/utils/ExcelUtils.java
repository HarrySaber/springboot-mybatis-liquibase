package cn.bonuli.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
	final static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
	private SimpleDateFormat sdf;
	private DecimalFormat df = new DecimalFormat("0");

	/**
	 * excel 2003 行数最大为65536
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] arrayToXSL(ArrayList<ArrayList<Object>> data) {
		return arrayToXLS(data, null);
	}

	/**
	 * excel 2003 行数最大为65536
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] arrayToXLS(ArrayList<ArrayList<Object>> data, String sheetName) {
		try {
			ByteArrayOutputStream file = new ByteArrayOutputStream();
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = null;
			if (null != sheetName) {
				sheet = workbook.createSheet(sheetName);
			} else {
				sheet = workbook.createSheet();
			}
			int i = 0;
			for (ArrayList<Object> rowdata : data) {
				HSSFRow row = sheet.createRow(i++);
				int j = 0;
				for (Object column : rowdata) {
					sheet.autoSizeColumn(j);
					HSSFCell cell = row.createCell(j++);
					if (null == column) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(column.toString());
					}
				}
			}
			workbook.write(file);
			file.close();
			return file.toByteArray();
		} catch (Exception e) {
			logger.error("arrayToXLS is error", e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * excel 2007以上
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] arrayToXLSX(ArrayList<ArrayList<Object>> data) {
		return arrayToXLSX(data, null);
	}

	/**
	 * excel 2007以上
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] arrayToXLSX(ArrayList<ArrayList<Object>> data, String sheetName) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream file = new ByteArrayOutputStream();
			XSSFSheet sheet = null;
			if (null != sheetName) {
				sheet = workbook.createSheet(sheetName);
			} else {
				sheet = workbook.createSheet();
			}
			XSSFCellStyle textStyle = workbook.createCellStyle();
			XSSFDataFormat format = workbook.createDataFormat();
			textStyle.setDataFormat(format.getFormat("@"));
			int i = 0;
			for (ArrayList<Object> rowdata : data) {
				XSSFRow row = sheet.createRow(i++);
				int j = 0;
				for (Object column : rowdata) {
					XSSFCell cell = row.createCell(j++);
					if (null == column) {
						cell.setCellValue("");
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					} else if (column instanceof Double) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((Double) column);
					} else if (column instanceof Integer) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue((Integer) column);
					} else {
						cell.setCellValue(column.toString());
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					}
					cell.setCellStyle(textStyle);// 设置单元格格式为"文本"

				}
			}
			workbook.write(file);
			file.close();
			return file.toByteArray();
		} catch (Exception e) {
			logger.error("arrayToXLSX is error", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * excel 2007以上
	 *
	 * @return
	 */
	public static byte[] arrayToXLSX(Map<String, List<List<Object>>> sheetData) {
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = null;
			for (Map.Entry<String, List<List<Object>>> entry : sheetData.entrySet()) {
				sheet = workbook.createSheet(entry.getKey());
				int i = 0;
				for (List<Object> rowdata : entry.getValue()) {
					XSSFRow row = sheet.createRow(i++);
					int j = 0;
					for (Object column : rowdata) {
						XSSFCell cell = row.createCell(j++);
						if (null == column) {
							cell.setCellValue("");
						} else {
							cell.setCellValue(column.toString());
						}
					}
				}
			}
			workbook.write(file);
			return file.toByteArray();
		} catch (Exception e) {
			logger.error("arrayToXLSX is error", e);
			throw new RuntimeException(e);
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isExcel2003(String fileName) {
		if (null == fileName) {
			return false;
		}
		return fileName.matches("^.+\\.(?i)(xls)$");
	}

	public static boolean isExcel2007(String fileName) {
		if (null == fileName) {
			return false;
		}
		return fileName.matches("^.+\\.(?i)(xlsx)$");
	}

	public List<List<String>> read(File file, boolean isExcel2003) {
		List<List<String>> dataList = null;
		/** 根据版本选择创建Workbook的方式 */
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(fis);
			} else {
				wb = new XSSFWorkbook(fis);
			}
			dataList = read(wb);
		} catch (IOException e) {
			logger.error("read xls error: ", e);
			return null;
		}
		return dataList;

	}

	private List<List<String>> read(Workbook wb) {
		List<List<String>> dataList = new ArrayList<List<String>>();
		/** 得到第一个shell */
		Sheet sheet = wb.getSheetAt(0);
		/** 得到Excel的行数 */
		int totalRows = sheet.getPhysicalNumberOfRows();
		/** 得到Excel的列数 */
		int totalCells = 0;
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		/** 循环Excel的行 */
		for (int r = 0; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			List<String> rowList = new ArrayList<String>();
			/** 循环Excel的列 */
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if (null != cell) {
					// 以下是判断数据的类型
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							cellValue = getSdf().format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).getTime());
						} else {
							double d = cell.getNumericCellValue();
							if (d > (int) d) {
								cellValue = d + "";
							} else {
								cellValue = df.format(d);
							}
						}
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						cellValue = cell.getBooleanCellValue() + "";
						break;
					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						cellValue = cell.getCellFormula() + "";
						break;
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						cellValue = "";
						break;
					case HSSFCell.CELL_TYPE_ERROR: // 故障
						cellValue = "Illegal Character";
						break;
					default:
						cellValue = "Unknown Type";
						break;
					}
				}
				rowList.add(cellValue);
			}
			/** 保存第r行的第c列 */
			// 排除空行
			long nullCellCount = rowList.stream().filter(cell -> (cell == null || cell.trim().equals(""))).count();
			if (totalCells == nullCellCount) {
				logger.debug("<<< Row:{} is empty,so breack read data", r);
				break;
			}
			dataList.add(rowList);
		}
		return dataList;
	}

	public SimpleDateFormat getSdf() {
		if (sdf == null) {
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static boolean isExcel(String fileName) {
		if (null == fileName) {
			return false;
		}
		return fileName.matches("^.+\\.(?i)(xls|xlsx)$");
	}

	public static Map<String, List<Map<String, String>>> parseExcel(File file) throws Exception {
		return doExcelParse(new FileInputStream(file));
	}

	public static Map<String, List<Map<String, String>>> parseExcel(InputStream inStream) throws Exception {
		return doExcelParse(inStream);
	}

	private static Map<String, List<Map<String, String>>> doExcelParse(InputStream inStream) throws Exception {

		Workbook workBook = null;
		Map<String, List<Map<String, String>>> result = Maps.newLinkedHashMap();
		try {
			workBook = WorkbookFactory.create(inStream);
			int numberOfSheetToParse = workBook.getNumberOfSheets();

			for (int i = 0; i < numberOfSheetToParse; i++) {
				Sheet sheet = workBook.getSheetAt(i);
				Row row = sheet.getRow(sheet.getFirstRowNum());
				if (row != null) {
					Map<Integer, String> indexColNameMap = getRow(row);
					List<Map<String, String>> sheetResult = Lists.newArrayList();
					for (int rowNum = sheet.getFirstRowNum() + 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
						Map<String, String> rowResult = getRow(sheet.getRow(rowNum), indexColNameMap);
						if (rowResult.size() > 0) {
							sheetResult.add(rowResult);
						}
					}
					result.put(sheet.getSheetName(), sheetResult);
				}
			}

		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (inStream != null) {
				inStream.close();
			}
		}
		return result;
	}

	/**
	 * @param row
	 *            void
	 */
	private static Map<String, String> getRow(Row row, Map<Integer, String> indexColNameMap) {
		Map<String, String> result = Maps.newLinkedHashMap();
		for (Map.Entry<Integer, String> entry : indexColNameMap.entrySet()) {
			result.put(entry.getValue(), getCell(row.getCell(entry.getKey())));
		}

		if (result.values().stream().allMatch(value -> StringUtils.isEmpty(value))) {
			return Maps.newLinkedHashMap();
		}

		return result;
	}

	/**
	 * @param row
	 *            void
	 */
	private static Map<Integer, String> getRow(Row row) {
		Map<Integer, String> result = Maps.newLinkedHashMap();
		for (short i = row.getFirstCellNum(); i <= row.getLastCellNum(); i++) {
			String cellValue = getCell(row.getCell(i));
			if (!StringUtils.isEmpty(cellValue)) {
				result.put((int) i, cellValue);
			}
		}
		return result;
	}

	/**
	 * @param cell
	 *            void
	 */
	private static String getCell(Cell cell) {
		String cellValue = "";
		DataFormatter formatter = new DataFormatter();
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					cellValue = formatter.formatCellValue(cell);
				} else {
					double value = cell.getNumericCellValue();
					int intValue = (int) value;
					cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}

	public static void setStyleCell(Workbook workbook) {
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int a = 0; a < numberOfSheets; a++) {
			Sheet sheet = workbook.getSheetAt(a);
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i < lastRowNum; i++) {
				Row row = sheet.getRow(i);
				short lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						CellStyle createCellStyle = cell.getCellStyle();
						// 添加边框
						createCellStyle.setBorderTop(CellStyle.BORDER_THIN);
						createCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
						createCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
						createCellStyle.setBorderRight(CellStyle.BORDER_THIN);
						cell.setCellStyle(createCellStyle);
					}
				}
			}
		}
	}

	public static void setStyleAutoColumn(Sheet sheet) {
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i < lastRowNum; i++) {
			// 内容自适应宽度
			sheet.autoSizeColumn(i, true);
		}
	}

	/**
	 * 创建样式
	 * 
	 */
	public static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short alignment, short verticalAlignment,
			String fontName, boolean isBold, short fontHeightInPoints, boolean addBorderThin) {
		return createCellStyle(workbook, alignment, verticalAlignment, fontName, isBold, fontHeightInPoints,
				addBorderThin, null, null);
	}

	public static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short alignment, short verticalAlignment,
			String fontName, boolean isBold, short fontHeightInPoints, boolean addBorderThin, HSSFColor backgroundColor,
			Short fontColor) {
		XSSFCellStyle createCellStyle = workbook.createCellStyle();
		// 设置字体
		XSSFFont font = workbook.createFont();
		font.setFontName(fontName);
		font.setFontHeightInPoints(fontHeightInPoints);
		font.setBold(isBold);
		if (fontColor != null) {
			font.setColor(fontColor);
		}
		createCellStyle.setFont(font);
		// 设置对齐方式
		createCellStyle.setAlignment(alignment);
		createCellStyle.setVerticalAlignment(verticalAlignment);
		// 填充背景色
		if (backgroundColor != null) {
			createCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			createCellStyle.setFillForegroundColor(backgroundColor.getIndex());
		}
		if (addBorderThin) {
			// 添加边框
			createCellStyle.setBorderTop(CellStyle.BORDER_THIN);
			createCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			createCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			createCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		}
		return createCellStyle;
	}
}
