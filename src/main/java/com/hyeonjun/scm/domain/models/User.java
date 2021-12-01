package com.hyeonjun.scm.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hyeonjun.scm.domain.errors.ErrorCode;
import com.hyeonjun.scm.domain.errors.FormSyntaxException;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public User(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    protected void setId(Integer id) {
        if (id != null && id < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "ID must be positive number");
        }
        this.id = id;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "Name Can't be empty");
        }
        this.name = name;
    }
    
}
