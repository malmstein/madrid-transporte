package es.malmstein.madridtransporte.library.response;

import java.util.List;

import es.malmstein.madridtransporte.library.objects.Stop;

public class GetStopsFromXYResponse {

	private List<Stop> stopsList;	

	public List<Stop> getData() {
		return stopsList;
	}

	public void setData(List<Stop> data) {
		this.stopsList = data;
	}
	
}
