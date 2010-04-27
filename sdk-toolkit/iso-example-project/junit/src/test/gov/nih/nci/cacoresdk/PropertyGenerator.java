package test.gov.nih.nci.cacoresdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class PropertyGenerator {

	String currentHeader;

	public String getCurrentHeader() {
		return currentHeader;
	}

	public void setCurrentHeader(String currentHeader) {
		this.currentHeader = currentHeader;
	}

	@SuppressWarnings("deprecation")
	public void generateConfigurationFiles(String confFileName, String outputDir) throws Exception {
		InputStream configFile = new FileInputStream(confFileName);
		HSSFWorkbook wb = new HSSFWorkbook(configFile);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		HSSFRow row = sheet.getRow(0);
		int colNum = row.getLastCellNum();

		ArrayList<String> names = new ArrayList<String>();
		for (int i =0; i<colNum;i++) {
			HSSFCell currentCell = row.getCell(i);
			if (currentCell == null) {
				names.add("");					
			}else {
				currentCell.setCellType(currentCell.CELL_TYPE_STRING);
				names.add(currentCell.toString());
			}
		}
		/*
		 * First Line includes names for all different configurations   (Requirement 1)
		 * Second column is the default value  (Requirement 2)
		 * From 3 - colNum are names of different configurations. (Requirement 3) 
		 */

		int currentRowNum  = 1;


		/*
		 * Loop through xls file to generate properties files
		 */

		while (currentRowNum <= rowNum && countRow(sheet.getRow(currentRowNum++)) == 1) {
			ArrayList<String> propertiesNames = new ArrayList<String>();
			
			String filename = getFirstString(sheet.getRow(currentRowNum-1)) ;

			int rowCount = 0;
			int headerRow = currentRowNum-1;
			while (currentRowNum <= rowNum && countRow(sheet.getRow(currentRowNum++)) != 1) { rowCount ++; }

			ArrayList<Properties> properties = new ArrayList<Properties>();
			properties.add(new Properties());
			for (int i=1; i<colNum; i++) {
				Properties prop = new Properties();
				properties.add(prop);
			}

			currentRowNum = headerRow + 1;
			for (int i=currentRowNum; i< currentRowNum+rowCount; i++) {
				HSSFRow currentRow = sheet.getRow(i);
				if (currentRow == null) continue;
				if (currentRow.getCell(0) == null) continue;
				String propertyName = currentRow.getCell(0).getStringCellValue();
				propertiesNames.add(propertyName);
				for (int j=1; j<colNum; j++) {
					Properties currentP = properties.get(j);
					HSSFCell currentCell = currentRow.getCell(j);
					if (currentCell == null) {
						currentP.put(propertyName, "");					
					}else {
						currentCell.setCellType(currentCell.CELL_TYPE_STRING);
						currentP.put(propertyName, currentCell.toString());
					}
				}
			}
			currentRowNum = currentRowNum+rowCount;

			/*
			 * Merge default properties with other properties
			 */
			for (int j=2; j<colNum; j++) {
				Properties defaultP = properties.get(1);
				Properties currentP = properties.get(j);
				Set<Object> propertyNames = defaultP.keySet();
				for (Object propertyKey : propertyNames) {
					String key=(String)propertyKey;
					if (currentP.getProperty(key).equals(""))
						currentP.setProperty(key, defaultP.getProperty(key));
				}

			}

			for (int j=1; j<colNum; j++) {
				File directory = new File(outputDir + "/" + names.get(j));
				directory.mkdirs();
				File f = new File(directory, filename);
				Properties currentP = properties.get(j);

				saveProperties(propertiesNames, currentP, f);
			}
		}
	}
	private int countRow(HSSFRow row) {
		int nonEmptyCount = 0;
		if (row == null) return 0;
		for (int i =0; i<row.getLastCellNum();i++) {
			HSSFCell currentCell = row.getCell(i);
			if (currentCell != null) {
				currentCell.setCellType(currentCell.CELL_TYPE_STRING);
				if (!(currentCell.toString().equals(""))) nonEmptyCount++;
			}
		}
		return nonEmptyCount;
	}

	private String  getFirstString(HSSFRow row) {
		for (int i =0; i<row.getLastCellNum();i++) {
			HSSFCell currentCell = row.getCell(i);
			if (currentCell != null) {
				currentCell.setCellType(currentCell.CELL_TYPE_STRING);
				if (!(currentCell.toString().equals("")))  return currentCell.toString();
			}
		}
		return "";
	}

	private void saveProperties(ArrayList<String> propertyNames, Properties props, File file) throws IOException{   
      PrintWriter printer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "EUC-JP"));
      for (int nameIndex = 0; nameIndex < propertyNames.size();nameIndex++){
        printer.println(propertyNames.get(nameIndex) + "=" + props.getProperty(propertyNames.get(nameIndex)));   
      }   
      printer.close();   
    }   

	public static void main(String[] argvs) throws Exception {
		PropertyGenerator pg = new PropertyGenerator();
		if(argvs!=null && argvs.length==2){
			String xlsLocation=argvs[0];
			String outputDirectory=argvs[1];
			pg.generateConfigurationFiles(xlsLocation,outputDirectory);
		}else{
			throw new Exception("Please specify valid arguments. argvs[0]=location of xls file argvs[1]=location of the output directory");
		}
	}
}
