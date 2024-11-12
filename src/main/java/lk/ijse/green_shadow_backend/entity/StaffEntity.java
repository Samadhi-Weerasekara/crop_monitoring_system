package lk.ijse.green_shadow_backend.entity;
import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.embedded.AddressIdentifier;
import lk.ijse.green_shadow_backend.embedded.NameIdentifier;
import lk.ijse.green_shadow_backend.entity.enums.Designation;
import lk.ijse.green_shadow_backend.entity.enums.Gender;
import lk.ijse.green_shadow_backend.entity.enums.UserRole;

import java.util.Date;

@Entity
@Table(name = "staff")

public class StaffEntity implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "staff_name")
    private NameIdentifier nameIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "designation", nullable = false)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "joined_date", nullable = false)
    private Date joinedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "staff_address")
    private AddressIdentifier addressIdentifier;

    @Column(name = "contact_no", nullable = false)
    private String contactNo;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "field_list")
    private String fieldList; // "N/A" if none

    @Column(name = "vehicle_list")
    private String vehicleList; // "N/A" if none

}
