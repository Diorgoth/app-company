package com.example.demo.controller;

import com.example.demo.entity.Worker;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.WorkerDto;
import com.example.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping("/worker")
    public ResponseEntity<ApiResponce> addWorker(@Valid @RequestBody WorkerDto workerDto){


        ApiResponce apiResponce = workerService.addWorker(workerDto);

        return ResponseEntity.status(apiResponce.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/worker/{id}")
    public ResponseEntity<ApiResponce> editWorker(@PathVariable Integer id, @Valid @RequestBody WorkerDto workerDto){

        ApiResponce apiResponce = workerService.editWorker(id, workerDto);

        return ResponseEntity.status(apiResponce.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponce);

    }

    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> getWorkers(){

        List<Worker> workers = workerService.getWorkers();

        return ResponseEntity.ok(workers);

    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Integer id){

        Worker worker = workerService.getWorker(id);

        return ResponseEntity.ok(worker);

    }

    @DeleteMapping("/worker")
    public ResponseEntity<ApiResponce> deleteWorker(@PathVariable Integer id){

        ApiResponce apiResponce = workerService.deleteWorker(id);

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
