package com.abhiesa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Farewell {

  private String message;
  private Integer remainingMinutes;
}
