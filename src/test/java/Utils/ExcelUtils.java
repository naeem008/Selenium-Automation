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

import DTO.BulkProductDTO;
import DTO.CartTestProductDTO;
import DTO.LoginDTO;
import DTO.ProductDTO;
import DTO.StockBaseDTO;
import DataProvider.CartProductDataProvider;

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

	public static List<ProductDTO> getProductDTO() throws IOException {
		List<ProductDTO> productdata = new ArrayList<ProductDTO>();
		DataFormatter pDataFormatter = new DataFormatter();
		Iterator<Row> rowIterator = ExcelUtils.getSheet(1).iterator();

		while (rowIterator.hasNext()) {
			Row nexRow1 = rowIterator.next();
			Iterator<Cell> celIterator1 = nexRow1.cellIterator();
			ProductDTO productDTO = new ProductDTO();
			byte cellcounter1 = 0;

			while (celIterator1.hasNext()) {
				Cell cell1 = celIterator1.next();

				switch (cellcounter1) {
				case 0:
					productDTO.setProductName(pDataFormatter.formatCellValue(cell1));
					break;

				}
			}
			productdata.add(productDTO);
		}
		return productdata;
	}

//	public static List<ExpectedAmountDTO> ExpectedData() throws IOException
//	{
//		List<ExpectedAmountDTO> expectedAmount = new ArrayList<ExpectedAmountDTO>();
//		DataFormatter dataFormatter = new DataFormatter();
//		Iterator<Row> nextRox002 = ExcelUtils.getSheet(2).iterator();
//		while(nextRox002.hasNext())
//		{
//			Row row002 = nextRox002.next();
//			Iterator<Cell> cell002 = row002.cellIterator();
//			ExpectedAmountDTO expectedAmountDTO = new ExpectedAmountDTO();
//			byte cellcounter = 0;
//			
//			while(cell002.hasNext())
//			{
//				Cell cell = cell002.next();
//				
//				switch (cellcounter) {
//				case 0:
//					expectedAmountDTO.setExpectedAmount(dataFormatter.formatCellValue(cell));
//					break;
//
//				}
//			}
//			expectedAmount.add(expectedAmountDTO);
//		}
//		return expectedAmount;
//	}

	public static List<CartTestProductDTO> getCdata() throws IOException {

		List<CartTestProductDTO> cartTestProductDTOs = new ArrayList<CartTestProductDTO>();
		DataFormatter formater = new DataFormatter();
		Iterator<Row> rowiIterator003 = ExcelUtils.getSheet(2).iterator();

		while (rowiIterator003.hasNext()) {
			Row nextRow = rowiIterator003.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			CartTestProductDTO cdata = new CartTestProductDTO();
			byte cellcounter = 0;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (cellcounter) {
				case 0:
					cdata.setCproductDTO1(formater.formatCellValue(cell));
					cellcounter++;
					break;
				case 1:
					cdata.setCproductDTO2(formater.formatCellValue(cell));
					break;

				}
			}

			cartTestProductDTOs.add(cdata);

		}
		// workbook.close();
		return cartTestProductDTOs;

	}

	public static List<BulkProductDTO> getBulkData() throws IOException {
		List<BulkProductDTO> bulkProductData = new ArrayList<BulkProductDTO>();
		DataFormatter dataFormatter = new DataFormatter();
		Iterator<Row> roIterator = ExcelUtils.getSheet(3).iterator();

		while (roIterator.hasNext()) {
			Row row = roIterator.next();
			Iterator<Cell> ceIterator = row.cellIterator();
			BulkProductDTO bulkProduct = new BulkProductDTO();

			byte cellcounter = 0;
			while (ceIterator.hasNext()) {
				Cell cell = ceIterator.next();

				switch (cellcounter) {
				case 0:
					bulkProduct.setProducts(dataFormatter.formatCellValue(cell));
					break;

				}
				bulkProductData.add(bulkProduct);
			}

		}
		return bulkProductData;
	}

	public static List<StockBaseDTO> getStockData() throws IOException {
		List<StockBaseDTO> stockdata = new ArrayList<StockBaseDTO>();
		DataFormatter dataFormatter = new DataFormatter();
		Iterator<Row> roIterator = ExcelUtils.getSheet(4).iterator();

		while (roIterator.hasNext()) {
			Row row = roIterator.next();
			Iterator<Cell> ceIterator = row.cellIterator();

			StockBaseDTO stockBaseDTO = new StockBaseDTO();
			byte cellcounter = 0;

			while (ceIterator.hasNext()) {
				Cell cell = ceIterator.next();

				switch (cellcounter) {
				case 0:
					stockBaseDTO.setProduct1(dataFormatter.formatCellValue(cell));
					cellcounter++;
					break;

				case 1:
					stockBaseDTO.setProduct2(dataFormatter.formatCellValue(cell));
					break;
				default:
					break;
				}
			}
			stockdata.add(stockBaseDTO);

		}
		return stockdata;
	}

	public static List<BulkProductDTO> getBundleSetData() throws IOException

	{
		List<BulkProductDTO> bundle = new ArrayList<BulkProductDTO>();
		DataFormatter dataFormatter = new DataFormatter();
		Iterator<Row> rIterator = ExcelUtils.getSheet(6).iterator();

		while (rIterator.hasNext()) {
			Row row = rIterator.next();
			Iterator<Cell> cIterator = row.iterator();
			BulkProductDTO bulkProductDTO = new BulkProductDTO();
			byte cellcounter = 0;

			while (cIterator.hasNext()) {
				Cell cell = cIterator.next();

				switch (cellcounter) {
				case 0:
					bulkProductDTO.setProducts(dataFormatter.formatCellValue(cell));
					break;

				}
			}
			bundle.add(bulkProductDTO);

		}
		return bundle;
	}
}
