package lk.ijse.green_shadow_backend.dto;

import lk.ijse.green_shadow_backend.entity.Designation;
import lk.ijse.green_shadow_backend.entity.Gender;
import lk.ijse.green_shadow_backend.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto {
    private String id; // Unique code for each staff member
    private String firstName; // First name of the staff member
    private String lastName; // Last name of the staff member
    private Designation designation; // Designation of the member as ENUM
    private Gender gender; // Gender of the member as ENUM
    private Date joinedDate; // Joined date
    private Date dob; // Date of birth of the staff member
    private String addressLine01; // Building no or name
    private String addressLine02; // Lane
    private String addressLine03; // Main city
    private String addressLine04; // Main state
    private String addressLine05; // Postal code
    private String contactNo; // Mobile number to contact
    private String email; // Email to contact
    private UserRole role; // Role of the member as ENUM
    private List<FieldDto> fields = new ArrayList<>(); // List of allocated fields, or empty if N/A
    private List<VehicleDto> vehicles = new ArrayList<>(); // List of allocated vehicles, or empty if N/A
}
