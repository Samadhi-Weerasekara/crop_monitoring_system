package lk.ijse.green_shadow_backend.dto;
import lk.ijse.green_shadow_backend.customeobj.EquipmentResponse;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto  implements SuperDto, EquipmentResponse {
    private String equipmentId;
    private String name;
    private Enums.EquipmentType type;
    private Enums.EquipmentStatus status;
//    private StaffDto assignedStaff;
//    private FieldDto assignedField;
}
