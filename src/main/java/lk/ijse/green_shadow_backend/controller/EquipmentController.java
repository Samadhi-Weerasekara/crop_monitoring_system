package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.customeobj.EquipmentErrorResponse;
import lk.ijse.green_shadow_backend.customeobj.EquipmentResponse;
import lk.ijse.green_shadow_backend.dto.EquipmentDto;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.EquipmentNotFoundException;
import lk.ijse.green_shadow_backend.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipments")
@CrossOrigin
public class EquipmentController {

    @Autowired
    private  EquipmentService equipmentService;

    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEquipment(@RequestBody EquipmentDto equipment) {
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                equipmentService.saveEquipment(equipment);
                logger.info("Equipment created successfully");
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                logger.error("Failed to create equipment");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Failed to create equipment");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(value = "allEquipments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDto> getAllEquipments() {
        logger.info("All equipments fetched successfully");
        return equipmentService.getAllEquipments();
    }

    @GetMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getSelectedEquipment(@PathVariable("equipmentId") String equipmentId) {
        if (equipmentId == null || equipmentId.isEmpty()) {
            logger.error("Invalid equipment ID");
            return new EquipmentErrorResponse(1, "Invalid equipment ID");
        }
        logger.info("Selected equipment fetched successfully");
        return equipmentService.getSelectedEquipment(equipmentId);
    }

    @PatchMapping(value = "/{equipmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@PathVariable("equipmentId") String equipmentId, @RequestBody EquipmentDto equipment) {
        try {
            if (equipment == null || (equipmentId == null || equipmentId.isEmpty())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipmentId, equipment);
            logger.info("Equipment updated successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            logger.error("Equipment not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Failed to update equipment");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
        try {
            equipmentService.deleteEquipment(equipmentId);
            logger.info("Equipment deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            logger.error("Equipment not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Failed to delete equipment");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
