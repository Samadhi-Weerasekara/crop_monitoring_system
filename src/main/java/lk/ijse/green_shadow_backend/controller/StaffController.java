package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.customeobj.StaffErrorResponse;
import lk.ijse.green_shadow_backend.customeobj.StaffResponse;
import lk.ijse.green_shadow_backend.dto.StaffDto;
import lk.ijse.green_shadow_backend.exception.DataPersistFailedException;
import lk.ijse.green_shadow_backend.exception.StaffNotFoundException;
import lk.ijse.green_shadow_backend.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    StaffService staffService;

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStaff(@RequestBody StaffDto staffDto) {
        if (staffDto == null) {
            return new ResponseEntity<>(new StaffErrorResponse(1, "Invalid staff data"), HttpStatus.BAD_REQUEST);
        }
        try {
            staffService.saveStaff(staffDto);
            logger.info("Staff data saved successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.info("Failed to save staff data");
            return new ResponseEntity<>(new StaffErrorResponse(2, "Failed to save staff data"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Internal server error");
            return new ResponseEntity<>(new StaffErrorResponse(3, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/allstaff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        List<StaffDto> staffList = staffService.getAllStaff();
        logger.info("All staff data retrieved successfully");
        return new ResponseEntity<>(staffList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSelectedStaff(@PathVariable("id") String staffId) {
        if (staffId == null || staffId.isEmpty()) {
            logger.error("Not valid staff id");
            return new ResponseEntity<>(new StaffErrorResponse(1, "Not valid staff id"), HttpStatus.BAD_REQUEST);
        }
        StaffResponse staffResponse = staffService.getSelectedStaff(staffId);
        logger.info("Selected staff data retrieved successfully");
        return new ResponseEntity<>(staffResponse, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStaff(@PathVariable("id") String staffId, @RequestBody StaffDto staffDto) {
        if (staffId == null || staffId.isEmpty()) {
            return new ResponseEntity<>(new StaffErrorResponse(1, "Not valid staff id"), HttpStatus.BAD_REQUEST);
        }
        try {
            staffService.updateStaff(staffId, staffDto);
            logger.info("Staff data updated successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            logger.error("Staff not found");
            return new ResponseEntity<>(new StaffErrorResponse(4, "Staff not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal server error");
            return new ResponseEntity<>(new StaffErrorResponse(3, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") String staffId) {
        try {
            staffService.deleteStaff(staffId);
            logger.info("Staff data deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            logger.error("Staff not found");
            return new ResponseEntity<>(new StaffErrorResponse(4, "Staff not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal server error");
            return new ResponseEntity<>(new StaffErrorResponse(3, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
