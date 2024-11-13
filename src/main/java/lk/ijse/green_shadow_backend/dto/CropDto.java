package lk.ijse.green_shadow_backend.dto;

import lk.ijse.green_shadow_backend.customeobj.CropResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CropDto implements SuperDto, CropResponse {
    private String crop_code;
    private String crop_comman_name;
    private String crop_scientific_name;
    private String crop_image;
    private String category;
    private String crop_season;
    private List<FieldDto> fieldDtos=new ArrayList<>();

}
