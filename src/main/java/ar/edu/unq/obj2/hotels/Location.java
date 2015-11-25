package ar.edu.unq.obj2.hotels;

public class Location {
    private String country;
    private String city;
    private String address;

    public Location(String country, String city, String address) {
        this.country = country;
        this.city = city;
        this.address = address;
    }

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
