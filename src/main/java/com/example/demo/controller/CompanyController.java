package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.CompanyDto;
import com.example.demo.service.CompanyService;
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
public class CompanyController {

    @Autowired
    CompanyService companyService;


    @PostMapping("/company")
    public ResponseEntity<ApiResponce> addCompany(@Valid @RequestBody CompanyDto companyDto) {

        ApiResponce apiResponce = companyService.addCompany(companyDto);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/company/{id}")
    public ResponseEntity<ApiResponce> editCompany(@PathVariable Integer id, @Valid @RequestBody CompanyDto companyDto){

        ApiResponce apiResponce = companyService.editCompany(id, companyDto);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies(){

        List<Company> companies = companyService.getCompanies();

        return ResponseEntity.ok(companies);

    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id){

        Company company = companyService.getCompany(id);

         return ResponseEntity.ok(company);

    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<ApiResponce> deleteCompany(@PathVariable Integer id){

        ApiResponce apiResponce = companyService.deleteCompany(id);

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

