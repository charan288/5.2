package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataprovider {

	//dataprovider1 
	
	@DataProvider(name = "logindata")
	public String[][] getdata() throws IOException {
	    String path = ".\\testdata\\HDM testdata.xlsx";
	    ExcelUtilities xlutil = new ExcelUtilities(path);

	    int totalrows = xlutil.getRowCount("Sheet1");      // total rows including header
	    int totalcols = xlutil.getCellCount("Sheet1", 1);  // total cols in first data row

	    // create array with size = totalrows - 1 (skip header row)
	    String[][] logindata = new String[totalrows - 1][totalcols];

	    // loop from 1 to totalrows - 1 (inclusive)
	    for (int i = 1; i < totalrows; i++) {         // i < totalrows (not <=)
	        for (int j = 0; j < totalcols; j++) {
	            logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j).trim();
	        }
	    }

	    return logindata;
	}

	
	
}