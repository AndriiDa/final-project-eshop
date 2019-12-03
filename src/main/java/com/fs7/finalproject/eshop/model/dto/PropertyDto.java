package com.fs7.finalproject.eshop.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PropertyDto extends AbstractDto {
  @Id
  private Long id;

  private String name;

  private String description;

  //private List<PropertyValueDto> propertyValues;

  @JsonIgnore
  LocalDateTime crTime;

  @JsonIgnore
  LocalDateTime lmTime;

}
