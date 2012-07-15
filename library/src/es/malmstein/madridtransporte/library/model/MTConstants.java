package es.malmstein.madridtransporte.library.model;

public class MTConstants {

    // BUSGSENSE
    public static String BUGSENSE_API = "7ad52340";
    public static String BUGSENSE_APP_TAG = "MadridTransporte APP";
    public static String BUGSENSE_LIB_TAG = "MadridTransporte LIBRARY";

	// HTTP SERVER ADDRESS	
    static String HOST = "https://servicios.emtmadrid.es:8443/";
    
	public static String getApiResourcePath(String method){
			return HOST + method;		
	}
	
    // Methods for the API Calls
    public static class API_METHOD{
        public final static String GetStopsFromXYURL = "geo/servicegeo.asmx/getStopsFromXY";
        public final static String GetStopsFromStopURL = "geo/servicegeo.asmx/getStopsFromStop";
        public final static String GetArriveStopURL = "geo/servicegeo.asmx/getArriveStop";
        public final static String GetListLinesURL = "bus/servicebus.asmx/GetListLines";
        public final static String GetLineInformationURL = "geo/servicegeo.asmx/getInfoLine";
        public final static String GetGroupsURL = "bus/servicebus.asmx/GetGroups";    
        public final static String GetNodesLinesURL = "bus/servicebus.asmx/GetNodesLines"; 
        public final static String GetStreetURL = "geo/servicegeo.asmx/GetStreet"; 
        public final static String GetRSSURL = "rss/emtrss.xml";
        public final static String GetRouteLinesRoute = "bus/servicebus.asmx/GetRouteLinesRoute";
        public final static String SuggestionsURL = "Clientes/dmzsuscripciones/clientes.asmx/Suggestions";        
        public final static String IssuesNewSubscriptionURL = "Clientes/dmzsuscripciones/clientes.asmx/NewSubscription";    
        public final static String IssuesDropSubscriptionURL = "Clientes/dmzsuscripciones/clientes.asmx/DropSubscription";         
    }
    
    // Object Names for the API Calls
    public static class API_REQUEST_OBJECT {
        public final static String ID_CLIENT = "idClient";
        public final static String PASS_KEY = "passKey";
    }    
    
    public static String GET_LINE_SKETCH = "https://www.emtmadrid.es/data/mapasLineas/";
    public static String GOOGLE_DIRECTIONS = "https://maps.google.com/maps/api/directions/xml";
    
    // Different INTENT_EXTRAS
    public static class INTENT_EXTRAS{
        public final static String LOADING = "loading";
        public final static String ASYNC_TASK = "async_task";        
    }

}
