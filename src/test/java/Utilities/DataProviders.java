package Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "orderItems", parallel = true)
	public Object[][] getData(){
		return new Object[][]  {
			{"Cucumber", "India"},
			{"Brinjal", "Australia"},
			{"Apple", "Canada"},
			{"Mango", "Germany"},
		};
	}
	
	//we can return a 2 dimensions objects array or an iterator
	//LinkedHashMap : to get ordered map
	@DataProvider(name = "orderItemsFromExcel", parallel = true)
	public static Iterator<LinkedHashMap<String, String>> excelReader(Method methodName) throws EncryptedDocumentException, IOException {
		
		List<LinkedHashMap<String, String>> dataFromExcel = new ArrayList<>();
		
		Workbook workbook =   WorkbookFactory.create(new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\files\\TestData.xlsx" ));
		String sheetName = methodName.getName();
		Sheet sheet = workbook.getSheet(sheetName);
		
		int totalRows = sheet.getPhysicalNumberOfRows();
		LinkedHashMap<String,String> mapData;
		List<String> allKeys = new ArrayList<>();
		DataFormatter dataFormatter = new DataFormatter();
		
		for(int i = 0; i< totalRows ; i++) {
			 mapData = new LinkedHashMap<>();
			 if( i == 0) {
	                int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
	                for (int j = 0; j < totalCols; j++) {
	                    allKeys.add(sheet.getRow(0).getCell(j).getStringCellValue());
	                }
	            }
			 else {
	                int totalCols = sheet.getRow(i).getPhysicalNumberOfCells();
	                for (int j = 0; j < totalCols; j++) {
	                    String cellValue = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));

	                    mapData.put(allKeys.get(j), cellValue);
	                }
	                dataFromExcel.add(mapData);
	            }
		}
		
		dataFromExcel = dataFromExcel.stream()
		    		  .filter(keyValuePair -> keyValuePair.get("Enabled").equalsIgnoreCase("Y"))
		    		  .collect(Collectors.toList());
      
		return dataFromExcel.iterator();
		
	}

}
