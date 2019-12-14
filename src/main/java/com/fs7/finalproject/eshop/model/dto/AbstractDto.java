package com.fs7.finalproject.eshop.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractDto implements Serializable {

  private Long id;

}
