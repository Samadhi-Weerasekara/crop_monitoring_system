package lk.ijse.green_shadow_backend.dto;
import lk.ijse.green_shadow_backend.entity.EquipmentStatus;
import lk.ijse.green_shadow_backend.entity.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto  implements SuperDto{
    private String equipmentId; // Unique ID for each equipment, with a proper prefix
    private String name; // Name of the equipment
    private EquipmentType type; // Type of equipment (Electrical, Mechanical) as ENUM
    private EquipmentStatus status; // Availability status of equipment as ENUM
    private StaffDto assignedStaff; // Assigned staff member (or null if N/A)
    private FieldDto assignedField; // Assigned field (or null if N/A)
}
