package lk.ijse.green_shadow_backend.dto;

import lk.ijse.green_shadow_backend.customeobj.StaffResponse;
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

    private List<Long> assignedFieldIds; // IDs of assigned fields
    private List<Long> logIds;
    private List<Long> vehicleIds; // IDs of assigned vehicles
}
