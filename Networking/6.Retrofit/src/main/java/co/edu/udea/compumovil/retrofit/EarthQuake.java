package co.edu.udea.compumovil.retrofit;

/**
 * EarthQuake Model
 */
import com.google.gson.annotations.SerializedName;

public class Earthquake {

    @SerializedName("datetime")
    private String datetime;

    @SerializedName("depth")
    private Double depth;

    @SerializedName("lng")
    private Double lng;

    @SerializedName("src")
    private String src;

    @SerializedName("eqid")
    private String eqid;

    @SerializedName("magnitude")
    private Double magnitude;

    @SerializedName("lat")
    private Double lat;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getEqid() {
        return eqid;
    }

    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

}
