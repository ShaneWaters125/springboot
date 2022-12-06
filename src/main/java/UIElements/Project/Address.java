package UIElements.Project;
import jakarta.persistence.*;
import org.geolatte.geom.Point;
import org.geolatte.geom.Position;
import org.locationtech.jts.geom.Geometry;

import java.sql.Blob;
import java.sql.Timestamp;

@Entity
@Table(name="address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int addressId;

    @Column(name = "address")
    String address;

    @Column(name = "address2")
    String address2;

    @Column(name = "district")
    String district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    City city;

    @Column(name = "postal_code")
    String postalCode;

    @Column(name = "phone")
    String phone;

    @Column(name = "location")
    Geometry location;

    @Column(name = "last_update")
    Timestamp lastUpdate;

    public Address(){

    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getLocation() {
        return location.toString();
    }

    public void setLocation(Geometry location) {
        this.location = location;
    }
}
