package es.malmstein.madridtransporte.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class LibraryUtils {

	private static boolean DEBUG = false;
	private static String LOG_TAG = "BlinkLibrary";
	
	/**
	 * Muestra un mensaje de log
	 * @param text
	 */
	public static void log(String text){
		if (DEBUG == true){
			Log.d(LOG_TAG, text);
		}
	}

	/**
	 * Check for internet connectivity
	 * @param cxt
	 * @return
	 */
	public static boolean isInternetConnected(Context cxt) {
		ConnectivityManager cm = (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo networkInfo : netInfo)
		{ 
			if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
				if (networkInfo.isConnected())
					return true;

			if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
				if (networkInfo.isConnected())
					return true;
		}
		return false;
	}
	

}
