package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.AbstractEntity;
import com.fs7.finalproject.eshop.model.dto.AbstractDto;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

  E toEntity(D dto);

  D toDto(E entity);
}
