package com.hyeonjun.scm.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserStatisticsDTO {

    List<UserStatsDTO> users;

    public UserStatisticsDTO(List<UserStatsDTO> users) {
        this.users = users;
    }
}
