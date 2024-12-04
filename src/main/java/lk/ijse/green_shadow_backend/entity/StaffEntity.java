package lk.ijse.green_shadow_backend.entity;
import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.embedded.Address;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")

public class StaffEntity implements SuperEntity{
    @Id
    @Column(name = "id",  unique = true)
    private String id;

    // Staff member's first name
    @Column(name = "first_name")
    private String firstName;

    // Staff member's last name
    @Column(name = "last_name")
    private String lastName;

    // Staff member's designation (e.g., Manager, Developer)
    @Enumerated(EnumType.STRING)
    @Column(name = "designation")
    private Enums.Designation designation;

    // Staff member's gender (Male, Female, Other)
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Enums.Gender gender;

    // Date when the staff member joined
    @Temporal(TemporalType.DATE)
    @Column(name = "joined_date")
    private Date joinedDate;

    // Date of birth of the staff member
    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dob;

    // Address details of the staff member
    @Embedded
    @Column(name = "address")
    private Address address;

    // Contact number for the staff member
    @Column(name = "contact_no")
    private String contactNo;

    // Email address for the staff member
    @Column(name = "email")
    private String email;

    // Role of the staff member (e.g., Admin, User)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Enums.Role role;

    @ManyToOne
    @JoinColumn(name = "field_id") // or whatever column name you are using
    private FieldEntity field;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<MonitoringLogDetailsEntity> logs;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicles;

}
