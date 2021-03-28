package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.DepartmentDto;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public ApiResponce addDepartment(DepartmentDto departmentDto){

        boolean existsByName = departmentRepository.existsByName(departmentDto.getName());

        if (existsByName){

            return new ApiResponce("Such department already exist",false);

        }

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());

        if (!optionalCompany.isPresent()){

            return new ApiResponce("Company not found",false);

        }

        Department department = new Department();

        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());

        departmentRepository.save(department);

        return new ApiResponce("Department",true);


    }

    public ApiResponce editDepartment(Integer id,DepartmentDto departmentDto){

        boolean byNameAndIdNot = departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id);

        if (byNameAndIdNot){

            return new ApiResponce("Such department already exist",false);

        }

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());

        if (!optionalCompany.isPresent()){

            return new ApiResponce("Company not found",false);

        }

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (!optionalDepartment.isPresent()){
            return new ApiResponce("Such department not found",false);
        }

        Department department = optionalDepartment.get();

        department.setName(departmentDto.getName());

        department.setCompany(optionalCompany.get());

        departmentRepository.save(department);

        return new ApiResponce("Department edited",true);

    }




    public List<Department> getDepartments(){


        List<Department> departments = departmentRepository.findAll();

        return departments;

    }

    public Department getDepartment(Integer id){

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isPresent()){

            return optionalDepartment.get();

        }else {
            return new Department();
        }

    }

    public ApiResponce deleteDepartment(Integer id){

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isPresent()){

            departmentRepository.deleteById(id);

            return new ApiResponce("Department deleted",true);

        }else {

            return new ApiResponce("Such Department not exist or already deleted",false);
        }

    }


}
