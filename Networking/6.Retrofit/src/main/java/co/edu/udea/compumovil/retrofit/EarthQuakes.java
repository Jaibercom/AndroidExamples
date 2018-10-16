package co.edu.udea.compumovil.retrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Earthquakes {

    @SerializedName("earthquakes")
    @Expose
    private List<Earthquake> earthquakes = null;

    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

}
