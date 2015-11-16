package ar.edu.unq.obj2.hotels;

public class Location {
    private String country;
    private String city;
    private String address;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public boolean isAt(String place) {
        return getCity().contains(place) || getAddress().contains(place) || getCountry().contains(place);
    }
}
