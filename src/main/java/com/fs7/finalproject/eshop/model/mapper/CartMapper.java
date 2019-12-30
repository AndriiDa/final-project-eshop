package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Cart;
import com.fs7.finalproject.eshop.model.dto.CartDto;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CartMapper extends AbstractMapper<Cart, CartDto> {

  private final UserRepository userRepository;

  private final ProductRepository productRepository;

  private final ModelMapper mapper;

  @Autowired
  public CartMapper(UserRepository userRepository, ProductRepository productRepository, ModelMapper mapper) {
    super(Cart.class, CartDto.class);
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.mapper = mapper;
  }

  private Long getUserId(Cart source) {
    return Objects.isNull(source) || Objects.isNull(source.getUser()) ? null : source.getUser().getId();
  }

  private Long getProductId(Cart source) {
    return Objects.isNull(source) || Objects.isNull(source.getProduct()) ? null : source.getProduct().getId();
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Cart.class, CartDto.class)
        .addMappings(m -> m.skip(CartDto::setUserId))
        .addMappings(m -> m.skip(CartDto::setProductId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(CartDto.class, Cart.class)
        .addMappings(m -> m.skip(Cart::setUser))
        .addMappings(m -> m.skip(Cart::setProduct))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Cart source, CartDto destination) {
    destination.setUserId(getUserId(source));
    destination.setProductId(getProductId(source));
  }

  @Override
  void mapSpecificFields(CartDto source, Cart destination) {
    destination.setUser(userRepository.findById(source.getUserId()).orElse(null));
    destination.setProduct(productRepository.findById(source.getProductId()).orElse(null));
  }
}