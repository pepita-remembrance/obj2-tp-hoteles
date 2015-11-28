package ar.edu.unq.obj2.hotels;

public class Contact {
    private String phone = "";
    private String email = "";

    public Contact() {
    }

    public Contact(String email) {
        this.email = email;
    }

    public Contact(String phone, String email) {
        this(email);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
