package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.UserDto;
import com.fs7.finalproject.eshop.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

  private AddressRepository addressRepository;

  private ModelMapper mapper;

  @Autowired
  public UserMapper(AddressRepository addressRepository, ModelMapper mapper) {
    super(User.class, UserDto.class);
    this.addressRepository = addressRepository;
    this.mapper = mapper;
  }

  private Long getAddressId(User source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAddress().getId();
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(User.class, UserDto.class)
        .addMappings(m -> m.skip(UserDto::setAddressId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(UserDto.class, User.class)
        .addMappings(m -> m.skip(User::setAddress))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(User source, UserDto destination) {
    destination.setAddressId(getAddressId(source));

  }

  @Override
  void mapSpecificFields(UserDto source, User destination) {
    destination.setAddress(addressRepository.findById(source.getAddressId()).orElse(null));
  }
}