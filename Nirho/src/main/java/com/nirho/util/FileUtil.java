package com.nirho.util;

public class FileUtil {

	public static final String HOME_FOLDER = System.getProperty("user.home");
	
	public String getPlantillaReporteBasePath() {
		return HOME_FOLDER + "/nirho/recursos/";
	}
	
}
