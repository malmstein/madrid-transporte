package es.malmstein.madridtransporte.app.utils;

import android.util.Log;

public class MTUtils {

	private static boolean DEBUG = false;
	private static String LOG_TAG = "MTUtils";
	
	/**
	 * Muestra un mensaje de log
	 * @param text
	 */
	public static void log(String text){
		if (DEBUG == true){
			Log.d(LOG_TAG, text);
		}
	}
	
}
