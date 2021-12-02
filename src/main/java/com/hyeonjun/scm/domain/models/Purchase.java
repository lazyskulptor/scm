package com.hyeonjun.scm.domain.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@IdClass(PurchaseId.class)
public class Purchase implements Serializable {

    @Id
    private Integer id;

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private int price;

    public Purchase(Integer id, User user, Product product, int price) {
        this.setId(id);
        this.setUser(user);
        this.setProduct(product);
        this.setPrice(price);
    }

    protected void setId(Integer id) {
        if (id == null || id < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "ID must be positive number");
        }
        this.id = id;
    }

    public void setUser(User user) {
        if (user == null || user.getId() == null || user.getId() < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "User ID must be positive number");
        }
        this.user = user;
    }

    public void setProduct(Product product) {
        if (product == null || product.getId() == null || product.getId() < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "Product ID must be positive number");
        }
        this.product = product;
    }

    protected void setPrice(int price) {
        if (price < 0) {
            throw new FormSyntaxException(ErrorCode.NOT_VALID_PARAMETERS, "Price must be positive number");
        }
        this.price = price;
    }
}
