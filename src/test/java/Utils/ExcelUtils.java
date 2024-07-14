package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DTO.LoginDTO;

public class ExcelUtils {

	private static FileInputStream inputStream = null;
	private static Workbook workbook = null;

	public static Sheet getSheet(int sheetNo) throws IOException {
		File f = new File("C:\\Users\\User\\git\\repository\\Temp\\Selenium-Automation\\Data.xlsx");
		inputStream = new FileInputStream(f);

		workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(sheetNo);
		return sheet;

	}

	public static List<LoginDTO> getLoginData() throws IOException {

		List<LoginDTO> logindata = new ArrayList<LoginDTO>();
		DataFormatter formater = new DataFormatter();
		Iterator<Row> rowiIterator = ExcelUtils.getSheet(0).iterator();

		while (rowiIterator.hasNext()) {
			Row nextRow = rowiIterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			LoginDTO login = new LoginDTO();
			byte cellcounter = 0;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (cellcounter) {
				case 0:
					login.setUsername(formater.formatCellValue(cell));
					cellcounter++;
					break;
				case 1:
					login.setPassword(formater.formatCellValue(cell));
					break;

				case 2:
					login.setUsername(formater.formatCellValue(cell));
					cellcounter++;
					break;

				case 3:
					login.setPassword(formater.formatCellValue(cell));
					break;
				}
			}

			logindata.add(login);

		}
		// workbook.close();
		return logindata;
	}
}
