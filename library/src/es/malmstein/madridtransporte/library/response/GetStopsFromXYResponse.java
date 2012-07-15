package es.malmstein.madridtransporte.library.response;

import java.util.List;

import es.malmstein.madridtransporte.library.objects.BusStop;

public class GetStopsFromXYResponse {

	private List<BusStop> stopsList;	

	public List<BusStop> getData() {
		return stopsList;
	}

	public void setData(List<BusStop> data) {
		this.stopsList = data;
	}
	
}
