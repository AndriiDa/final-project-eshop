package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Order;
import com.fs7.finalproject.eshop.model.dto.OrderDto;
import com.fs7.finalproject.eshop.repositories.AddressRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

  private UserRepository userRepository;
  private AddressRepository addressRepository;

  private ModelMapper mapper;

  @Autowired
  public OrderMapper(ModelMapper mapper,
                     UserRepository userRepository,
                     AddressRepository addressRepository) {
    super(Order.class, OrderDto.class);
    this.userRepository = userRepository;
    this.addressRepository = addressRepository;
    this.mapper = mapper;
  }

  private Long getUserId(Order source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUser().getId();
  }

  private Long getCrUserId(Order source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCrUser().getId();
  }

  private Long getLmUserId(Order source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLmUser().getId();
  }

  private Long getReceiptAddressId(Order source) {
    return Objects.isNull(source)
        || Objects.isNull(source.getReceiptAddress().getId()) ? null : source.getReceiptAddress().getId();
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Order.class, OrderDto.class)
        .addMappings(m -> m.skip(OrderDto::setUserId))
        .addMappings(m -> m.skip(OrderDto::setCrUserId))
        .addMappings(m -> m.skip(OrderDto::setLmUserId))
        .addMappings(m -> m.skip(OrderDto::setReceiptAddressId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(OrderDto.class, Order.class)
        .addMappings(m -> m.skip(Order::setUser))
        .addMappings(m -> m.skip(Order::setCrUser))
        .addMappings(m -> m.skip(Order::setLmUser))
        .addMappings(m -> m.skip(Order::setReceiptAddress))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Order source, OrderDto destination) {
    destination.setUserId(getUserId(source));
    destination.setCrUserId(getCrUserId(source));
    destination.setLmUserId(getLmUserId(source));
    destination.setReceiptAddressId(getReceiptAddressId(source));
  }

  @Override
  void mapSpecificFields(OrderDto source, Order destination) {
    destination.setUser(userRepository.findById(source.getCrUserId()).orElse(null));
    destination.setCrUser(userRepository.findById(source.getCrUserId()).orElse(null));
    destination.setLmUser(userRepository.findById(source.getLmUserId()).orElse(null));
    destination.setReceiptAddress(addressRepository.findById(source.getReceiptAddressId()).orElse(null));
  }
}