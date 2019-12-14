package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Vendor;
import com.fs7.finalproject.eshop.model.dto.VendorDto;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class VendorMapper extends AbstractMapper<Vendor, VendorDto> {

  private UserRepository userRepository;

  private ModelMapper mapper;

  @Autowired
  public VendorMapper(ModelMapper mapper, UserRepository userRepository) {
    super(Vendor.class, VendorDto.class);
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  private Long getCrUserId(Vendor source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCrUser().getId();
  }

  private Long getLmUserId(Vendor source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLmUser().getId();
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Vendor.class, VendorDto.class)
        .addMappings(m -> m.skip(VendorDto::setCrUserId))
        .addMappings(m -> m.skip(VendorDto::setLmUserId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(VendorDto.class, Vendor.class)
        .addMappings(m -> m.skip(Vendor::setCrUser))
        .addMappings(m -> m.skip(Vendor::setLmUser))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Vendor source, VendorDto destination) {
    destination.setCrUserId(getCrUserId(source));
    destination.setLmUserId(getLmUserId(source));
  }

  @Override
  void mapSpecificFields(VendorDto source, Vendor destination) {
    destination.setCrUser(userRepository.findById(source.getCrUserId()).orElse(null));
    destination.setLmUser(userRepository.findById(source.getLmUserId()).orElse(null));
  }

}