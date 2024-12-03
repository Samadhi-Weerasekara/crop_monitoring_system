package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.customeobj.EquipmentErrorResponse;
import lk.ijse.green_shadow_backend.customeobj.EquipmentResponse;
import lk.ijse.green_shadow_backend.dto.EquipmentDto;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.EquipmentNotFoundException;
import lk.ijse.green_shadow_backend.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipments")
public class EquipmentController {

    @Autowired
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEquipment(@RequestBody EquipmentDto equipment) {
        if (equipment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            equipmentService.saveEquipment(equipment);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "allEquipments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDto> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    @GetMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getSelectedEquipment(@PathVariable("equipmentId") String equipmentId) {
        if (equipmentId == null || equipmentId.isEmpty()) {
            return new EquipmentErrorResponse(1, "Invalid equipment ID");
        }
        return equipmentService.getSelectedEquipment(equipmentId);
    }

    @PatchMapping(value = "/{equipmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@PathVariable("equipmentId") String equipmentId, @RequestBody EquipmentDto equipment) {
        try {
            if (equipment == null || (equipmentId == null || equipmentId.isEmpty())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipmentId, equipment);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
        try {
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
