package lk.ijse.green_shadow_backend.service;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_backend.customeobj.CropErrorResponse;
import lk.ijse.green_shadow_backend.customeobj.CropResponse;
import lk.ijse.green_shadow_backend.dao.CropDao;
import lk.ijse.green_shadow_backend.dto.CropDto;
import lk.ijse.green_shadow_backend.entity.CropEntity;
import lk.ijse.green_shadow_backend.exception.CropNotFoundException;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService{
    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void saveCrop(CropDto cropDto) {
        cropDto.setCropCode(AppUtil.createCropId());
        var cropEntity = mapping.converToEntity(cropDto);
        var savedCrop = cropDao.save(cropEntity);
        if(savedCrop == null){
            throw new DataPersistFailedException("Cannot save crop");
        }
    }

    @Override
    public void updateCrop(CropDto cropDto) {
        Optional<CropEntity> tmpCropEntity= cropDao.findById(cropDto.getCropCode());
        if(!tmpCropEntity.isPresent()){
            throw new CropNotFoundException("Crop not found");
        }else {
            tmpCropEntity.get().setCommonName(cropDto.getCommonName());
            tmpCropEntity.get().setScientificName(cropDto.getScientificName());
            tmpCropEntity.get().setCropImage(cropDto.getCropImage());
            tmpCropEntity.get().setCategory(cropDto.getCategory());
            tmpCropEntity.get().setCropSeason(cropDto.getCropSeason());
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> selectedCropId = cropDao.findById(cropCode);
        if(!selectedCropId.isPresent()){
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
        if(cropDao.existsById(cropCode)){
            CropEntity cropEntityById = cropDao.getReferenceById(cropCode);
            return mapping.convertToDto(cropEntityById);
        }else {
            return new CropErrorResponse(0, "Crop not found");
        }
    }

    @Override
    public List<CropDto> getAllCrops() {
        List<CropEntity> getAllCrops = cropDao.findAll();
        return mapping.convertCropEntityToDTO(getAllCrops);
    }
}
