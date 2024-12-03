package lk.ijse.green_shadow_backend.dto;

import lk.ijse.green_shadow_backend.customeobj.FieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FieldDto  implements SuperDto, FieldResponse {
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private Double extentSize;
    private String fieldImage1;
    private String fieldImage2;

    private List<Long> cropIds;
    private List<Long> assignedStaffIds;
    private List<Long> logIds;




}
