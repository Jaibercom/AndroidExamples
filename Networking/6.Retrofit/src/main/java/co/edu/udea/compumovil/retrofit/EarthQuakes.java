package co.edu.udea.compumovil.retrofit;

import java.util.ArrayList;

public class EarthQuakes {
    private ArrayList<EarthQuake> earthquakes;

    public ArrayList<EarthQuake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(ArrayList<EarthQuake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    public EarthQuakes(ArrayList<EarthQuake> earthquakes) {
        this.earthquakes = earthquakes;
    }
}
