package co.edu.udea.compumovil.volleygson;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Earthquakes {

    @SerializedName("earthquakes")
    private List<Earthquake> earthquakes = null;

    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

}