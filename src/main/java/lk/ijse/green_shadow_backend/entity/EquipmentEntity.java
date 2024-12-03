package lk.ijse.green_shadow_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.green_shadow_backend.dto.FieldDto;
import lk.ijse.green_shadow_backend.dto.StaffDto;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "equipments")
@Entity
public class EquipmentEntity implements SuperEntity{
    @Id
    private String equipmentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Enums.EquipmentType type;

    @Column(nullable = false)
    private Enums.EquipmentStatus status;


//    private StaffDto assignedStaff;
//    private FieldDto assignedField;
}
