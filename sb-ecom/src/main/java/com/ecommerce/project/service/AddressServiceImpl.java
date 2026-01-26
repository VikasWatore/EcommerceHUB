package com.ecommerce.project.service;

import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address = modelMapper.map(addressDTO, Address.class);
        List<Address> addressList = user.getAddresses();

        addressList.add(address);
        user.setAddresses(addressList);

        address.setUser(user);
        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddress() {

        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOS = addresses.stream().map(
                        address -> modelMapper.map(address, AddressDTO.class))
                .toList();
        return addressDTOS;
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }

    @Override
    public List<AddressDTO> getUserAddress(User user) {
        List<Address> addressList = user.getAddresses();
        List<AddressDTO> addressDTOList = addressList.stream().map(add -> modelMapper.map(add, AddressDTO.class)).toList();
        return addressDTOList;
    }

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setBuildingName(addressDTO.getBuildingName());
        address.setState(addressDTO.getState());
        address.setPincode(addressDTO.getPincode());
        address.setStreet(addressDTO.getStreet());

        Address updateAddress = addressRepository.save(address);
        User user = address.getUser();
        user.getAddresses().removeIf(add -> add.getAddressId().equals(addressId));
        user.getAddresses().add(updateAddress);
        userRepository.save(user);


        return modelMapper.map(updateAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDatabase=addressRepository.findById(addressId)
                .orElseThrow( () -> new ResourceNotFoundException("Address","addressId",addressId));
        addressRepository.deleteById(addressId);
        User user=addressFromDatabase.getUser();
        user.getAddresses().removeIf(add->add.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.delete(addressFromDatabase);
        return "Address deleted successfully with addressId: "+addressId;
    }


}
