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
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer productId;

    private int price;

    public Purchase(Integer id, User user, Product product, int price) {
        if (user == null) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "User can't be null");
        } else if (product == null) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "product can't be null");
        }
        this.setId(id);
        this.setUserId(user.getId());
        this.setProductId(product.getId());
        this.setPrice(price);
    }

    protected void setId(Integer id) {
        if (id != null && id < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "ID must be positive number");
        }
        this.id = id;
    }

    protected void setUserId(Integer userId) {
        if (userId == null || userId < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "User ID must be positive number");
        }
        this.userId = userId;
    }

    protected void setProductId(Integer productId) {
        if (productId == null || productId < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "Product ID must be positive number");
        }
        this.productId = productId;
    }

    protected void setPrice(int price) {
        if (price < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "Price must be positive number");
        }
        this.price = price;
    }
}
