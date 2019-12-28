package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

  private ModelMapper mapper;

  @Autowired
  public UserMapper(ModelMapper mapper) {
    super(User.class, UserDto.class);
    this.mapper = mapper;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(User.class, UserDto.class)
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(UserDto.class, User.class)
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(User source, UserDto destination) {

  }

  @Override
  void mapSpecificFields(UserDto source, User destination) {

  }
}