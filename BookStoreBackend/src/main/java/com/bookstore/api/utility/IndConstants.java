package com.bookstore.api.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndConstants {
	public final static String IND = "IND";
	
	public static final Map<String, String> mapOfIndStates = new HashMap<String, String> () {
	{
		put("AN", "Andaman and Nicobar Islands");
		put("AP", "Andhra Pradesh");
		put("AR", "Arunachal Pradesh");
		put("AS", "Assam");
		put("BR", "Bihar");
		put("CG", "Chandigarh");
		put("CH", "Chhattisgarh");
		put("DH", "Dadra and Nagar Haveli");
		put("DD", "Daman and Diu");
		put("DL", "Delhi");
		put("GA", "Goa");
		put("GJ", "Gujarat");
		put("HR", "Haryana");
		put("HP", "Himachal Pradesh");
		put("JK", "Jammu and Kashmir");
		put("JH", "Jharkhand");
		put("KA", "Karnataka");
		put("KL", "Kerala");
		put("LD", "Lakshadweep");
		put("MP", "Madhya Pradesh");
		put("MH", "Maharashtra");
		put("MN", "Manipur");
		put("ML", "Meghalaya");
		put("MZ", "Mizoram");
		put("NL", "Nagaland");
		put("OR", "Odisha");
		put("PY", "Puducherry");
		put("PB", "Punjab");
		put("RJ", "Rajasthan");
		put("SK", "Sikkim");
		put("TN", "Tamil Nadu");
		put("TS", "Telangana");
		put("TR", "Tripura");
		put("UK", "Uttar Pradesh");
		put("UP", "Uttarakhand");
		put("WB", "West Bengal");
	}
	
  };
  
  public final static List<String> listOfIndStatesCode = new ArrayList<>(mapOfIndStates.keySet());
  public final static List<String> listOfIndStatesName = new ArrayList<>(mapOfIndStates.values());
}
