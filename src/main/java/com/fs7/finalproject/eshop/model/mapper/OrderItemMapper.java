package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.OrderItem;
import com.fs7.finalproject.eshop.model.dto.OrderItemDto;
import com.fs7.finalproject.eshop.repositories.OrderRepository;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class OrderItemMapper extends AbstractMapper<OrderItem, OrderItemDto> {

  private UserRepository userRepository;
  private ProductRepository productRepository;
  private OrderRepository orderRepository;

  private ModelMapper mapper;

  @Autowired
  public OrderItemMapper(ModelMapper mapper,
                         UserRepository userRepository,
                         ProductRepository productRepository,
                         OrderRepository orderRepository) {
    super(OrderItem.class, OrderItemDto.class);
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
    this.mapper = mapper;
  }

  private Long getCrUserId(OrderItem source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCrUser().getId();
  }

  private Long getLmUserId(OrderItem source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLmUser().getId();
  }

  private Long getOrderId(OrderItem source) {
    return Objects.isNull(source)
        || Objects.isNull(source.getOrder().getId()) ? null : source.getOrder().getId();
  }

  private Long getProductId(OrderItem source) {
    return Objects.isNull(source)
        || Objects.isNull(source.getProduct().getId()) ? null : source.getProduct().getId();
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(OrderItem.class, OrderItemDto.class)
        .addMappings(m -> m.skip(OrderItemDto::setCrUserId))
        .addMappings(m -> m.skip(OrderItemDto::setLmUserId))
        .addMappings(m -> m.skip(OrderItemDto::setOrderId))
        .addMappings(m -> m.skip(OrderItemDto::setProductId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(OrderItemDto.class, OrderItem.class)
        .addMappings(m -> m.skip(OrderItem::setCrUser))
        .addMappings(m -> m.skip(OrderItem::setLmUser))
        .addMappings(m -> m.skip(OrderItem::setOrder))
        .addMappings(m -> m.skip(OrderItem::setProduct))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(OrderItem source, OrderItemDto destination) {
    destination.setCrUserId(getCrUserId(source));
    destination.setLmUserId(getLmUserId(source));
    destination.setOrderId(getOrderId(source));
    destination.setProductId(getProductId(source));
  }

  @Override
  void mapSpecificFields(OrderItemDto source, OrderItem destination) {
    destination.setCrUser(userRepository.findById(source.getCrUserId()).orElse(null));
    destination.setLmUser(userRepository.findById(source.getLmUserId()).orElse(null));
    destination.setOrder(orderRepository.findById(source.getOrderId()).orElse(null));
    destination.setProduct(productRepository.findById(source.getProductId()).orElse(null));
  }
}