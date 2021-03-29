package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Company;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.CompanyDto;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public ApiResponce addCompany(CompanyDto companyDto){

        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());

        if (existsByCorpName){

            return new ApiResponce("Such Company already exist",false);

        }

        Address address = new Address();

        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Company company = new Company();

        company.setAddress(savedAddress);
        company.setDirectorName(companyDto.getDirectorName());
        company.setCorpName(companyDto.getCorpName());

        companyRepository.save(company);

        return new ApiResponce("Company added",true);


    }


    public ApiResponce editCompany(Integer id,CompanyDto companyDto){

        boolean existsByCorpNameAndIdNot = companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id);

        if (existsByCorpNameAndIdNot){

            return new ApiResponce("Such Company already exist",false);

        }

        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()){

            Company company = optionalCompany.get();

            company.setCorpName(companyDto.getCorpName());
            company.setDirectorName(companyDto.getDirectorName());

            Address address = optionalCompany.get().getAddress();
            address.setHomeNumber(companyDto.getHomeNumber());
            address.setStreet(companyDto.getStreet());

            Address address1 = addressRepository.save(address);

            company.setAddress(address1);

             companyRepository.save(company);

             return new ApiResponce("Company edited",true);

        }

        return new ApiResponce("Company not found",false);


    }

    public List<Company> getCompanies(){

        List<Company> companyList = companyRepository.findAll();

        return companyList;

    }

    public Company getCompany(Integer id){

        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()){

            return optionalCompany.get();

        }else {

            return new Company();
        }

    }

    public ApiResponce deleteCompany(Integer id){

        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()){

            companyRepository.deleteById(id);

            return new ApiResponce("Company deleted",true);

        }else {

            return new ApiResponce("Such company not found",false);

        }

    }


}
