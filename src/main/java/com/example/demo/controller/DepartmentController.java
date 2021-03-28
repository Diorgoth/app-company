package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.DepartmentDto;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseEntity<ApiResponce> addDepartments(@Valid @RequestBody DepartmentDto departmentDto){

        ApiResponce apiResponce = departmentService.addDepartment(departmentDto);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/department/{id}")
    public ResponseEntity<ApiResponce> editDepartment(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto){

        ApiResponce apiResponce = departmentService.editDepartment(id, departmentDto);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartments(){

        List<Department> departments = departmentService.getDepartments();

        return ResponseEntity.ok(departments);

    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Integer id){

        Department department = departmentService.getDepartment(id);

       return ResponseEntity.ok(department);


    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<ApiResponce> deleteDepartment(@PathVariable Integer id){

        ApiResponce apiResponce = departmentService.deleteDepartment(id);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
