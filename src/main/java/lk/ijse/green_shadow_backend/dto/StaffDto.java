package lk.ijse.green_shadow_backend.dto;

import lk.ijse.green_shadow_backend.customeobj.StaffResponse;
import lk.ijse.green_shadow_backend.embedded.Address;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto implements SuperDto, StaffResponse {
    private String id; // Unique code for each staff member
    private String firstName; // First name of the staff member
    private String lastName; // Last name of the staff member
    private Enums.Designation designation; // Designation of the member as ENUM
    private Enums.Gender gender; // Gender of the member as ENUM
    private Date joinedDate; // Joined date
    private Date dob; // Date of birth of the staff member
    private Address address;
    private String contactNo; // Mobile number to contact
    private String email; // Email to contact
    private Enums.Role role; // Role of the member as ENUM
//    private List<FieldDto> fields = new ArrayList<>(); // List of allocated fields, or empty if N/A
//    private List<VehicleDto> vehicles = new ArrayList<>(); // List of allocated vehicles, or empty if N/A
}
