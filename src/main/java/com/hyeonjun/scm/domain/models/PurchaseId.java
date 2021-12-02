package com.hyeonjun.scm.domain.models;

import java.io.Serializable;

import lombok.Setter;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class PurchaseId implements Serializable {
    
    private Integer id;

    private Integer userId;

    private Integer productId;
}
