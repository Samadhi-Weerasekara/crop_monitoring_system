package lk.ijse.green_shadow_backend.entity;

import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.dto.FieldDto;
import lk.ijse.green_shadow_backend.dto.StaffDto;
import lk.ijse.green_shadow_backend.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "equipments")
@Entity
public class EquipmentEntity implements SuperEntity{
    @Id
    private String equipmentId;


    private String name;

    @Enumerated(EnumType.STRING)
    private Enums.EquipmentType type;

    @Enumerated(EnumType.STRING)
    private Enums.EquipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;



}
