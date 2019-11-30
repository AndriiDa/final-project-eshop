package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Brand;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.BrandDto;
import com.fs7.finalproject.eshop.repositories.BrandRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

  private BrandRepository brandRepository;
  private UserRepository userRepository;
  private ModelMapper modelMapper;

  @Autowired
  public BrandService(BrandRepository brandRepository,
                      ModelMapper modelMapper,
                      UserRepository userRepository) {
    this.brandRepository = brandRepository;
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
  }

  public List<BrandDto> findAll() {
    List<BrandDto> result = new ArrayList<>();

    brandRepository.findAll().forEach(item -> {
      result.add(modelMapper.map(item, BrandDto.class));
    });

    return result;
  }

  public BrandDto findById(long id) {
    Optional<Brand> category = brandRepository.findById(id);
    return category.map(item1 -> modelMapper.map(item1, BrandDto.class)).orElse(null);
  }

  public Long create(BrandDto itemDto) {
    Brand item = modelMapper.map(itemDto, Brand.class);
    return modelMapper.map(brandRepository.save(item), BrandDto.class).getId();
  }

  public int update(Long id, BrandDto itemDto) {
    if (findById(id) != null) {
      Brand item = modelMapper.map(itemDto, Brand.class);
      Long crUserId = itemDto.getCrUserId();
      Long lmUserId = itemDto.getLmUserId();
      User crUser = userRepository.findById(crUserId).orElse(null);
      User lmUser = userRepository.findById(lmUserId).orElse(null);
      item.setCrUser(crUser);
      item.setLmUser(lmUser);
      item.setId(id);
      brandRepository.save(item);
      return 1;
    } else {
      return 0;
    }
  }

  public int deleteById(long id) {
    if (findById(id) != null) {
      brandRepository.deleteById(id);
      return 1;
    } else {
      return 0;
    }
  }
}
