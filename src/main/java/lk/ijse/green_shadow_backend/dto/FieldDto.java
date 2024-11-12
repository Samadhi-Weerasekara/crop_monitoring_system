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
    private String field_code;
    private String field_name;
    private String field_location;
    private Double extent_size_of_the_field;
    private List<CropDto> crops = new ArrayList<>();
    private List<StaffDto> staff = new ArrayList<>();
    private String field_image1;
    private String field_image2;



}
