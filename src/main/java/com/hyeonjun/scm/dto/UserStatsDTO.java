package com.hyeonjun.scm.dto;

import com.hyeonjun.scm.domain.models.Purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatsDTO {
    private Integer id;

    private String name;

    private int sum;

    public UserStatsDTO(Purchase purchase) {
        this.id = purchase.getUser().getId();
        this.name = purchase.getUser().getName();
        this.sum = purchase.getPrice();
    }
}
