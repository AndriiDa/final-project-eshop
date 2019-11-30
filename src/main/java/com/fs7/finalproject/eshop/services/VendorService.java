package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Vendor;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.VendorDto;
import com.fs7.finalproject.eshop.repositories.VendorRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
  private VendorRepository vendorRepository;
  private UserRepository userRepository;
  private ModelMapper modelMapper;

  @Autowired
  public VendorService(VendorRepository vendorRepository,
                      ModelMapper modelMapper,
                      UserRepository userRepository) {
    this.vendorRepository = vendorRepository;
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
  }

  public List<VendorDto> findAll() {
    List<VendorDto> result = new ArrayList<>();

    vendorRepository.findAll().forEach(item -> {
      result.add(modelMapper.map(item, VendorDto.class));
    });

    return result;
  }

  public VendorDto findById(long id) {
    Optional<Vendor> category = vendorRepository.findById(id);
    return category.map(item1 -> modelMapper.map(item1, VendorDto.class)).orElse(null);
  }

  public Long create(VendorDto itemDto) {
    Vendor item = modelMapper.map(itemDto, Vendor.class);
    return modelMapper.map(vendorRepository.save(item), VendorDto.class).getId();
  }

  public int update(Long id, VendorDto itemDto) {
    if (findById(id) != null) {
      Vendor item = modelMapper.map(itemDto, Vendor.class);
      Long crUserId = itemDto.getCrUserId();
      Long lmUserId = itemDto.getLmUserId();
      User crUser = userRepository.findById(crUserId).orElse(null);
      User lmUser = userRepository.findById(lmUserId).orElse(null);
      item.setCrUser(crUser);
      item.setLmUser(lmUser);
      item.setId(id);
      vendorRepository.save(item);
      return 1;
    } else {
      return 0;
    }
  }

  public int deleteById(long id) {
    if (findById(id) != null) {
      vendorRepository.deleteById(id);
      return 1;
    } else {
      return 0;
    }
  }
}
