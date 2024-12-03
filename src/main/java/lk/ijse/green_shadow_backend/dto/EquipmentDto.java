package lk.ijse.green_shadow_backend.dto;
import lk.ijse.green_shadow_backend.customeobj.EquipmentResponse;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto  implements SuperDto, EquipmentResponse {
    private String equipmentId;
    private String name;
    private Enums.EquipmentType type;
    private Enums.EquipmentStatus status;
    private List<Long> fieldIds; // IDs of fields using this equipment
}
