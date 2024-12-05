package lk.ijse.green_shadow_backend.enums;

import lombok.Getter;

public class Enums {
    public enum Designation {
        MANAGER,
        SCIENTIST,
        ADMINISTRATIVE,
        FIELD_WORKER,
        DRIVER,
        ASSISTANT
    }


    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
@Getter
    public enum Role {
        MANAGER,
        ADMINISTRATIVE,
        SCIENTIST,
       OTHER
    }

    public enum EquipmentType {
    ELECTRICAL, MECHANICAL
    }

    public enum EquipmentStatus {
       AVAILABLE,UNAVAILABLE
    }

}
