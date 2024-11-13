package lk.ijse.green_shadow_backend.entity;
import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.embedded.Address;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")

public class StaffEntity implements SuperEntity{
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    // Staff member's first name
    @Column(name = "first_name", nullable = false)
    private String firstName;

    // Staff member's last name
    @Column(name = "last_name", nullable = false)
    private String lastName;

    // Staff member's designation (e.g., Manager, Developer)
    @Enumerated(EnumType.STRING)
    @Column(name = "designation", nullable = false)
    private Enums.Designation designation;

    // Staff member's gender (Male, Female, Other)
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Enums.Gender gender;

    // Date when the staff member joined
    @Temporal(TemporalType.DATE)
    @Column(name = "joined_date", nullable = false)
    private Date joinedDate;

    // Date of birth of the staff member
    @Temporal(TemporalType.DATE)
    @Column(name = "dob", nullable = false)
    private Date dob;

    // Address details of the staff member
    @Embedded
    @Column(name = "address", nullable = false)
    private Address address;

    // Contact number for the staff member
    @Column(name = "contact_no", nullable = false)
    private String contactNo;

    // Email address for the staff member
    @Column(name = "email", nullable = false)
    private String email;

    // Role of the staff member (e.g., Admin, User)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Enums.Role role;

    // List of fields allocated to the staff member (use String to represent list or customize as needed)
//    @Column(name = "field_list")
//    private String fieldList; // "N/A" if none
//
//    // List of vehicles allocated to the staff member (use String to represent list or customize as needed)
//    @Column(name = "vehicle_list")
//    private String vehicleList; // "N/A" if none

}
