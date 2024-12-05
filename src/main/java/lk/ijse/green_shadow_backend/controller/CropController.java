package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.customeobj.CropResponse;
import lk.ijse.green_shadow_backend.dto.CropDto;
import lk.ijse.green_shadow_backend.exception.CropNotFoundException;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.service.CropService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
@RequiredArgsConstructor
@CrossOrigin
public class CropController {
    @Autowired
    private final CropService cropService;

    private static final Logger logger = LoggerFactory.getLogger(CropController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("commonName") String commonName,
            @RequestPart ("scientificName") String scientificName,
            @RequestPart ("cropImage") MultipartFile cropImage,
            @RequestPart ("category") String category,
            @RequestPart ("cropSeason") String cropSeason) {
        // Handle profile pic
        try {
            //byte [] imageByteCollection = cropImage.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(cropImage);
            // build the user object
            CropDto buildCropDTO = new CropDto();
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setCropImage(base64ProfilePic);
            buildCropDTO.setCategory(category);
            buildCropDTO.setCropSeason(cropSeason);
            //send to the service layer
            cropService .saveCrop(buildCropDTO);
            logger.info("Crop saved successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropCode") String cropCode) {
        try {
            cropService.deleteCrop(cropCode);
            logger.info("Crop deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropResponse getSelectedCrop(@PathVariable ("cropCode") String cropCode){
        return cropService.getSelectedCrop(cropCode);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDto> getAllCrops(){
        logger.info("Crops found successfully");
        return cropService.getAllCrops();
    }
    @PatchMapping(value = "/{cropCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCrop(
            @PathVariable ("cropCode") String cropCode,
            @RequestPart("updatedCommonName") String updatedCommonName,
            @RequestPart ("updatedScientificName") String updatedScientificName,
            @RequestPart ("updatedCropImage") MultipartFile updatedCropImage,
            @RequestPart ("updatedCategory") String updatedCategory,
            @RequestPart ("updatedCropSeason") String updatedCropSeason)
    {
        try {
            String updateBase64ProfilePic = AppUtil.toBase64ProfilePic(updatedCropImage);
            var updatedCrop = new CropDto();
            updatedCrop.setCropCode(cropCode);
            updatedCrop.setCommonName(updatedCommonName);
            updatedCrop.setScientificName(updatedScientificName);
            updatedCrop.setCropImage(updateBase64ProfilePic);
            updatedCrop.setCategory(updatedCategory);
            updatedCrop.setCropSeason(updatedCropSeason);
            cropService.updateCrop(updatedCrop);
            logger.info("Crop updated successfully with code: {}", cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            logger.error("Crop update failed - Crop with code {} not found", cropCode);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("Crop update failed - Internal server error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
