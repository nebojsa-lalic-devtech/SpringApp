package test.domain;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_first", nullable = true)
    private String firstName;

    @Column(name = "name_last", nullable = true)
    private String lastName;

    @Column(name = "city", nullable = true)
    private String City;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
