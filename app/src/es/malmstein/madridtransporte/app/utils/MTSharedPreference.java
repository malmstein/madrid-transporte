package es.malmstein.madridtransporte.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
* This class is used for storing Data in Shared Preference file
* @author David Gonz‡lez
* Blink Booking
* Date 25/04/2012
*/
public class MTSharedPreference {
	
    // Methods for the API Calls
    public static class PREFERENCES{
        public final static String DEFAULT_TRANSPORT = "default_transport";
        public final static String DEFAULT_BUS_PAGE = "default_bus_page";
        public final static String DEFAULT_METRO_PAGE = "default_metro_page";
        public final static String DEFAULT_TRAIN_PAGE = "default_train_page";
    }

	private static MTSharedPreference sMTUserPreferenceObject 	= null;
	private static SharedPreferences preferences 				= null;
	private static SharedPreferences.Editor editor 				= null;

	private MTSharedPreference() {

	}

	public static MTSharedPreference startInstance(Context context) {
		if (sMTUserPreferenceObject == null) {
			sMTUserPreferenceObject = new MTSharedPreference();
			preferences = context.getSharedPreferences("MTPREF", Context.MODE_PRIVATE);
			editor = preferences.edit();
		}
		return sMTUserPreferenceObject;
	}

	public static MTSharedPreference getInstance(){
		return sMTUserPreferenceObject;
	}

	public static void stopInstance(){
		sMTUserPreferenceObject = null;
	}

	public String getPreferenceString(String preferenceName) {

		return preferences.getString(preferenceName, null);
	}

	public void setPreferenceString(String preferenceName, String preferenceData) {

		editor.putString(preferenceName, preferenceData);
		editor.commit();
		
		MTUtils.log("MTSharedPreference string saved - " + preferenceName + " = " + preferenceData);
	}
	
	public void setPreferenceLong(String preferenceName, long preferenceData) {

        editor.putLong(preferenceName, preferenceData);
        editor.commit();
        
        MTUtils.log("MTSharedPreference long saved - " + preferenceName + " = " + preferenceData);
    }
	
	public long getPreferenceLong(String preferenceName) {

        return preferences.getLong(preferenceName, 0);
    }
	
	public void setPreferenceInt(String preferenceName, int preferenceData) {

        editor.putInt(preferenceName, preferenceData);
        editor.commit();
        
        MTUtils.log("MTSharedPreference int saved - " + preferenceName + " = " + preferenceData);
    }
	
	public int getPreferenceInt(String preferenceName) {

        return preferences.getInt(preferenceName, 0);
    }
	
	public void setPreferenceBoolean(String preferenceName, boolean preferenceData) {

        editor.putBoolean(preferenceName, preferenceData);
        editor.commit();
        
        MTUtils.log("MTSharedPreference boolean saved - " + preferenceName + " = " + preferenceData);
    }
   
   public boolean getPreferenceBoolean(String preferenceName) {

       return preferences.getBoolean(preferenceName, false);
   }
	
}
