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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int price;

    public Product(String name, int price) {
        this.setId(null);
        this.setName(name);
        this.setPrice(price);
    }

    public Product(Integer id, String name, int price) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
    }

    protected void setId(Integer id) {
        if (id != null && id < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "ID must be positive number");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "Name Can't be empty");
        }
        this.name = name;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "Price must be positive number");
        }
        this.price = price;
    }

}
