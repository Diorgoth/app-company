package com.example.demo.controller;

import com.example.demo.entity.Address;
import com.example.demo.payload.AddressDto;
import com.example.demo.payload.ApiResponce;
import com.example.demo.service.AddressService;
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
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/address")
    public ResponseEntity<ApiResponce> addAddress(@Valid @RequestBody AddressDto addressDto){

        ApiResponce apiResponce = addressService.addAddress(addressDto);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/address/{id}")
    public ResponseEntity<ApiResponce> editAddress(@PathVariable Integer id,@Valid @RequestBody AddressDto addressDto){

        ApiResponce apiResponce = addressService.editAddress(id, addressDto);
        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddresses(){
        List<Address> addresses = addressService.getAddresses();

        return ResponseEntity.ok(addresses);

    }

    @GetMapping("address")
    public ResponseEntity<Address> getAddress(@PathVariable Integer id){

        Address address = addressService.getAddress(id);

        return ResponseEntity.ok(address);

    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<ApiResponce> deleteAddress(@PathVariable Integer id){

        ApiResponce apiResponce = addressService.deleteAddress(id);

        return ResponseEntity.ok(apiResponce);

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
