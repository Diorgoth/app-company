package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Department;
import com.example.demo.entity.Worker;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.WorkerDto;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public ApiResponce addWorker(WorkerDto workerDto){


        boolean byPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());

        if (byPhoneNumber){
            return new ApiResponce("Such Worker already exist",false);
        }

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());

        if (!optionalDepartment.isPresent()){

            return new ApiResponce("Such Department not found",false);

        }

        Address address = new Address();

        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());

        Address savedAddress = addressRepository.save(address);

        Worker worker = new Worker();

        worker.setAddress(savedAddress);
        worker.setDepartment(optionalDepartment.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());

        workerRepository.save(worker);


        return new ApiResponce("Worker added",true);


    }

    public ApiResponce editWorker(Integer id, WorkerDto workerDto){

        boolean numberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);

        if (numberAndIdNot){
            return new ApiResponce("Such Worker already exist",false);
        }

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());

        if (!optionalDepartment.isPresent()){

            return new ApiResponce("Such Department not found",false);

        }


        Optional<Worker> optionalWorker = workerRepository.findById(id);

        if (!optionalWorker.isPresent()){

            return new ApiResponce("Such Worker not found",false);

        }



        Address address =  optionalWorker.get().getAddress();

        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());

        Address savedAddress = addressRepository.save(address);

        Worker worker = optionalWorker.get();

        worker.setAddress(savedAddress);
        worker.setName(workerDto.getName());
        worker.setDepartment(optionalDepartment.get());
        worker.setPhoneNumber(workerDto.getPhoneNumber());

        workerRepository.save(worker);

        return new ApiResponce("Worker edited",true);



    }


    public List<Worker> getWorkers(){

        List<Worker> repositoryAll = workerRepository.findAll();

        return repositoryAll;

    }

    public Worker getWorker(Integer id){

        Optional<Worker> optionalWorker = workerRepository.findById(id);

        if (optionalWorker.isPresent()){

            return optionalWorker.get();

        }else {

            return new Worker();

        }

    }

    public ApiResponce deleteWorker(Integer id){

        Optional<Worker> optionalWorker = workerRepository.findById(id);

        if (optionalWorker.isPresent()){

            workerRepository.deleteById(id);

            return new ApiResponce("Worker deleted",true);

        }else {

            return new ApiResponce("Such Worker not found",false);

        }
    }

}
