package itp.com.aqeelmirza_searchaddress.Utils;

public class List_DataModel {

    int id;
    String addressName;
    String addressLatitude;
    String addressLongitude;
    String pickup;

    public List_DataModel(String addressName, String addressLatitude, String addressLongitude, String pickup) {
        this.addressName = addressName;
        this.addressLatitude = addressLatitude;
        this.addressLongitude = addressLongitude;
        this.pickup = pickup;
    }

    public List_DataModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressLatitude() {
        return addressLatitude;
    }

    public void setAddressLatitude(String addressLatitude) {
        this.addressLatitude = addressLatitude;
    }

    public String getAddressLongitude() {
        return addressLongitude;
    }

    public void setAddressLongitude(String addressLongitude) {
        this.addressLongitude = addressLongitude;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }


}
