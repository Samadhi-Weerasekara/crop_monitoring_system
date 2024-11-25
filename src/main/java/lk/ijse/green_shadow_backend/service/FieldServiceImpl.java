package lk.ijse.green_shadow_backend.service;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_backend.customeobj.FieldErrorResponse;
import lk.ijse.green_shadow_backend.customeobj.FieldResponse;
import lk.ijse.green_shadow_backend.dao.FieldDao;
import lk.ijse.green_shadow_backend.dto.FieldDto;
import lk.ijse.green_shadow_backend.entity.FieldEntity;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.FieldNotFoundException;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    @Autowired
    private final FieldDao fieldDao;
    @Autowired
    private final Mapping mapping;
    @Override
    public void saveField(FieldDto fieldDto) {
        fieldDto.setFieldCode(AppUtil.createFieldId());
        FieldEntity savedField =
                fieldDao.save(mapping.converToEntity(fieldDto));
        if(savedField == null ) {
            throw new DataPersistFailedException("Cannot save data");
        }
    }

    @Override
    public void updateField(FieldDto fieldDto) {
        Optional<FieldEntity> tmpField = fieldDao.findById(fieldDto.getFieldCode());
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else {
            tmpField.get().setFieldName(fieldDto.getFieldName());
            tmpField.get().setFieldLocation(fieldDto.getFieldLocation());
            tmpField.get().setExtentSize(fieldDto.getExtentSize());
            tmpField.get().setFieldImage1(fieldDto.getFieldImage1());
            tmpField.get().setFieldImage2(fieldDto.getFieldImage2());
        }
    }

    @Override
    public void deleteField(String fieldId) {
        Optional<FieldEntity> selectedFieldId = fieldDao.findById(fieldId);
        if(!selectedFieldId.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else {
            fieldDao.deleteById(fieldId);
        }
    }

    @Override
    public FieldResponse getSelectedField(String fieldId) {
        if(fieldDao.existsById(fieldId)){
            FieldEntity fieldEntityByFieldId = fieldDao.getReferenceById(fieldId);
            return mapping.convertToDto(fieldEntityByFieldId);
        }else {
            return new FieldErrorResponse(0, "Field not found");
        }
    }

    @Override
    public List<FieldDto> getAllFields() {
        List<FieldEntity> getAllFields = fieldDao.findAll();
        return mapping.convertFieldEntityToDTO(getAllFields);
    }
}
