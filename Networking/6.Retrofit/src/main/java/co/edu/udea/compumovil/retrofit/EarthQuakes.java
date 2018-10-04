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


/*public class Earthquakes {
    private ArrayList<Earthquake> earthquakes;

    public ArrayList<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(ArrayList<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    public Earthquakes(ArrayList<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }
}*/
