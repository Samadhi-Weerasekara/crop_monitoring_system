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
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
//    private List<FieldDto> fieldDtos=new ArrayList<>();

}
