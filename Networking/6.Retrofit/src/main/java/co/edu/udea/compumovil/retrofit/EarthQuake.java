package co.edu.udea.compumovil.retrofit;

/**
 * EarthQuake Model
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Earthquake {

    @SerializedName("datetime")
    @Expose
    private String datetime;

    @SerializedName("depth")
    @Expose
    private Double depth;

    @SerializedName("lng")
    @Expose
    private Double lng;

    @SerializedName("src")
    @Expose
    private String src;

    @SerializedName("eqid")
    @Expose
    private String eqid;

    @SerializedName("magnitude")
    @Expose
    private Double magnitude;

    @SerializedName("lat")
    @Expose
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

/*public class EarthQuake {
    private String datetime;
    private int depth;
    private String eqid;
    private float lat;
    private float lng;
    private float magnitude;
    private String src;

    public EarthQuake(String dateTime, int depth, String eqid, float lat, float lng, float magnitude, String src) {
        datetime = dateTime;
        this.depth = depth;
        this.eqid = eqid;
        this.lat = lat;
        this.lng = lng;
        this.magnitude = magnitude;
        this.src = src;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getEqid() {
        return eqid;
    }

    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}*/
