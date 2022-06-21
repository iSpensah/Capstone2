
package pkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Java {
	
	public static void printArray(String[] names) {
		System.out.println(names);
	}
	public static String appendStrings(String[] strings) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<strings.length;i++) {
			sb.append("'" + strings[i] + "',");
		}
		String combinedStrings = sb.toString();
		combinedStrings = combinedStrings.substring(0, combinedStrings.length() - 1);
		return combinedStrings;
	}
	public static void main(String[] args) {
		String[] cat = {"1","2","3","4"};
		for(int i = 0; i<=3;i++) {
			System.out.println(cat[i]);
		}
		String[] names = {"John","Spencer","Bro"};
		for (int i =0;i<names.length;i++) {
			System.out.println(names[i]);
		}
		printArray(names);
		System.out.println(names);
		//String sql =  "where dept_name in("+filter+")"; 
		System.out.println(appendStrings(names));
	}
}
