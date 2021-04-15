package library;

import java.util.ArrayList;
public class search { 
	public void searchString(Object[][] data, ArrayList<Object[]> searched, int index, String value) {
		for (int i=0; i<data.length; i++){ 
			if (((String) data[i][index]).equalsIgnoreCase(value)) {
				searched.add(data[i]); 
				} 
			} 
		}
}