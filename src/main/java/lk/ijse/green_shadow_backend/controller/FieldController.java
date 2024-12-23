package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.customeobj.FieldResponse;
import lk.ijse.green_shadow_backend.dto.FieldDto;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.FieldNotFoundException;
import lk.ijse.green_shadow_backend.service.FieldService;
import lk.ijse.green_shadow_backend.util.AppUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
@CrossOrigin
public class FieldController {
    @Autowired
    FieldService fieldService;

    private static final Logger logger = LoggerFactory.getLogger(FieldController.class);


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("extentSize") Double extentSize,
            @RequestParam("fieldImage1") MultipartFile fieldImage1,
            @RequestParam("fieldImage2") MultipartFile fieldImage2) {

        try {

            System.out.println("Received fieldName: " + fieldName);
            System.out.println("Received fieldLocation: " + fieldLocation);
            System.out.println("Received extentSize: " + extentSize);
            System.out.println("Received fieldImage1: " + fieldImage1.getOriginalFilename());
            System.out.println("Received fieldImage2: " + fieldImage2.getOriginalFilename());


            String base64Image1 = AppUtil.toBase64ProfilePic(fieldImage1);
            String base64Image2 = AppUtil.toBase64ProfilePic(fieldImage2);

            FieldDto buildFieldDto = new FieldDto();
            buildFieldDto.setFieldName(fieldName);
            buildFieldDto.setFieldLocation(fieldLocation);
            buildFieldDto.setExtentSize(extentSize);
            buildFieldDto.setFieldImage1(base64Image1);
            buildFieldDto.setFieldImage2(base64Image2);

            //send to the service layer
            fieldService.saveField(buildFieldDto);
            logger.info("Field created successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to create field");
            System.err.println("Invalid WKT for fieldLocation: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.info("Failed to create field");
            System.err.println("Unexpected error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{fieldCode}")
    public ResponseEntity<Void> deleteUser(@PathVariable("fieldCode") String fieldCode) {
        try {
            fieldService.deleteField(fieldCode);
            logger.info("Field deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            logger.info("Field not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.info("Failed to delete field");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldResponse getSelectedField(@PathVariable("fieldCode") String fieldCode) {
        logger.info("Selected field fetched successfully");
        return fieldService.getSelectedField(fieldCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDto> getAllFields() {
        logger.info("All fields fetched successfully");
        return fieldService.getAllFields();
    }

    @PatchMapping(value = "/{fieldCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateField(
            @PathVariable("fieldCode") String fieldCode,
            @RequestParam("updateFieldName") String updateFieldName,
            @RequestParam("updateFieldLocation") String updateFieldLocation,
            @RequestParam("updateExtentSize") Double updateExtentSize,
            @RequestParam("updateFieldImage1") MultipartFile updateFieldImage1,
            @RequestParam("updateFieldImage2") MultipartFile updateFieldImage2
    ) {
        try {

            String updateFieldImage1Base64 = AppUtil.toBase64ProfilePic(updateFieldImage1);
            String updateFieldImage2Base64 = AppUtil.toBase64ProfilePic(updateFieldImage2);

            var updateField = new FieldDto();
            updateField.setFieldCode(fieldCode);
            updateField.setFieldName(updateFieldName);
            updateField.setFieldLocation(updateFieldLocation);
            updateField.setExtentSize(updateExtentSize);
            updateField.setFieldImage1(updateFieldImage1Base64);
            updateField.setFieldImage2(updateFieldImage2Base64);
            fieldService.updateField(updateField);
            logger.info("Field updated successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            logger.info("Field not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.info("Failed to update field");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
