package com.chori.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chori.model.ExcelImportDemo;

@Controller
@RequestMapping(value = "/")
public class DemoController {
	/**
	 * This function return view for add new Accessory
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/importExcelFile", method = RequestMethod.GET)
	public String importExcelFile(ModelMap model) {
		ExcelImportDemo excelImportDemo = new ExcelImportDemo();
		model.addAttribute("excelImportDemo", excelImportDemo);
		return "configuration/excelimportdemo/demo";
	}

	@RequestMapping(value = "/testClass", method = RequestMethod.GET)
	public String testClass() {
//		ExcelImportDemo excelImportDemo = new ExcelImportDemo();
//		model.addAttribute("excelImportDemo", excelImportDemo);
		return "configuration/excelimportdemo/demoClass";
	}
	
	@RequestMapping(value = "/importExcelFile", method = RequestMethod.POST)
	public String addNewAccessoryPost(@Valid ExcelImportDemo excelImportDemo,
			BindingResult result, HttpServletRequest request)
			throws IOException {

		// accessoryValidator.validate(accessoryAddModel, result);

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
			return "configuration/excelimportdemo/error";
		} else {
			System.out.println("Fetching files" + excelImportDemo.getFile());
			// List<String> fileNames= new ArrayList<String>(2);

			File dir = new File(request.getSession().getServletContext()
					.getRealPath("/resources/chori/AccessoryImage/"));
			if (!dir.exists()) {
				dir.mkdirs();
			}
			FileCopyUtils.copy(excelImportDemo.getFile().getBytes(),
					new File(request.getSession().getServletContext()
							.getRealPath("/resources/chori/AccessoryImage/")
							+ excelImportDemo.getFile().getOriginalFilename()));
			System.out.println(request.getSession().getServletContext()
					.getRealPath("/resources/chori/AccessoryImage/"));

			// phần excel
			try {
				String fileName = excelImportDemo.getFile()
						.getOriginalFilename();

				FileInputStream file = new FileInputStream(new File(request
						.getSession().getServletContext()
						.getRealPath("/resources/chori/AccessoryImage/")
						+ excelImportDemo.getFile().getOriginalFilename()));

				// if file suffix is .xls
				if (fileName.substring(fileName.lastIndexOf(".")).toLowerCase()
						.equals(".xls")) {

					// Get the workbook instance for XLS file
					HSSFWorkbook workbook = new HSSFWorkbook(file);

					// Get first sheet from the workbook
					HSSFSheet sheet = workbook.getSheetAt(0);

					// Iterate through each rows from first sheet
					@SuppressWarnings("unchecked")
					Iterator<Row> rowIterator = sheet.rowIterator();
					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();

						// For each row, iterate through each columns
						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_BOOLEAN:
								System.out.print(cell.getBooleanCellValue()
										+ "\t\t");
								break;
							case Cell.CELL_TYPE_NUMERIC:
								System.out.print(cell.getNumericCellValue()
										+ "\t\t");
								break;
							case Cell.CELL_TYPE_STRING:
								System.out.print(cell.getStringCellValue()
										+ "\t\t");
								break;
							}
						}
						System.out.println("");
					}
					file.close();
				} else if (fileName.substring(fileName.lastIndexOf("."))
						.toLowerCase().equals(".xlsx")) {// if file suffix is
															// .xlsx
					// Get the workbook instance for XLS file
					XSSFWorkbook workbook = new XSSFWorkbook(file);

					// Get first sheet from the workbook
					XSSFSheet sheet = workbook.getSheetAt(0);

					// Iterate through each rows from first sheet
					Iterator<Row> rowIterator = sheet.rowIterator();
					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();

						// For each row, iterate through each columns
						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();

							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_BOOLEAN:
								System.out.print(cell.getBooleanCellValue()
										+ "\t\t");
								break;
							case Cell.CELL_TYPE_NUMERIC:
								System.out.print(cell.getNumericCellValue()
										+ "\t\t");
								break;
							case Cell.CELL_TYPE_STRING:
								System.out.print(cell.getStringCellValue()
										+ "\t\t");
								break;
							}
						}
						System.out.println("");
					}
					file.close();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// end phần excel

			return "configuration/excelimportdemo/success";
		}
	}
}
