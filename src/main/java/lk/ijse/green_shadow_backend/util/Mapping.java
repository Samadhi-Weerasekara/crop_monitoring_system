package lk.ijse.green_shadow_backend.util;

import lk.ijse.green_shadow_backend.dto.StaffDto;
import lk.ijse.green_shadow_backend.entity.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //matters of StaffEntity and DTO
    public StaffDto convertToDto(StaffEntity staff){
        return modelMapper.map(staff,StaffDto.class);
    }

    public StaffEntity converToEntity(StaffDto staffDto){
        return modelMapper.map(staffDto,StaffEntity.class);
    }

    public List<StaffDto> convertToDTO(List<StaffEntity> staffEntities) {
        return modelMapper.map(staffEntities, new TypeToken<List<StaffDto>>() {}.getType());
    }
}
