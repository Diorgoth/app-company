package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.payload.AddressDto;
import com.example.demo.payload.ApiResponce;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;



    public ApiResponce addAddress(AddressDto addressDto){

        boolean existsByHomeNumberAndStreet = addressRepository.existsByHomeNumberAndStreet(addressDto.getHomeNumber(),addressDto.getStreet());

        if (existsByHomeNumberAndStreet){

            return new ApiResponce("Such Address already exist",false);

        }

        Address address = new Address();

        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);

        return new ApiResponce("Address added",true);

    }

    public ApiResponce editAddress(Integer id,AddressDto addressDto){

        boolean existsByHomeNumberAndStreetAndIdNot = addressRepository.existsByHomeNumberAndStreetAndIdNot(addressDto.getHomeNumber(), addressDto.getStreet(), id);

        if (existsByHomeNumberAndStreetAndIdNot){

            return new ApiResponce("Such address already exist",false);

        }

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent()){
            Address address = optionalAddress.get();;

            address.setHomeNumber(addressDto.getHomeNumber());
            address.setStreet(addressDto.getStreet());
            addressRepository.save(address);

            return new ApiResponce("Address edited",true);


        }

        return new ApiResponce("Such address not found",false);

    }

    public List<Address> getAddresses(){

        List<Address> addressList = addressRepository.findAll();

        return addressList;

    }

    public Address getAddress(Integer id){

        Optional<Address> optionalAddress = addressRepository.findById(id);

        return optionalAddress.orElseGet(Address::new);

    }

    public ApiResponce deleteAddress(Integer id){

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent()) {
            addressRepository.deleteById(id);
            return new ApiResponce("Address deleted", true);
        }else {
            return new ApiResponce("Such Address not found",false);
        }

    }



}
