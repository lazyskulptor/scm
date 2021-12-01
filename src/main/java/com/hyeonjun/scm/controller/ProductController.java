package com.hyeonjun.scm.controller;

import java.util.stream.Collectors;

import com.hyeonjun.scm.domain.errors.ErrorCode;
import com.hyeonjun.scm.domain.errors.FormSyntaxException;
import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.dto.ListProductDTO;
import com.hyeonjun.scm.dto.ProductDTO;
import com.hyeonjun.scm.dto.SingleProductDTO;
import com.hyeonjun.scm.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService svc;

    @PostMapping
    public ResponseEntity<SingleProductDTO> postProduct(@RequestBody SingleProductDTO dto) {
        Product reqBody = dto.getProduct().toEntity();
        if (reqBody.getId() == null) {
            svc.saveProduct(reqBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SingleProductDTO(new ProductDTO(reqBody)));
        }
        throw new FormSyntaxException(ErrorCode.ENTITY_DUPLICATE, "중복된 Product가 존재합니다");
    }

    @GetMapping
    public ResponseEntity<ListProductDTO> getProductList(@RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "20") Integer limit) {
        Page<Product> found = svc.getProductList(PageRequest.of(offset, limit));

        return ResponseEntity.ok(new ListProductDTO(found.get().map(ProductDTO::new).collect(Collectors.toList())));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<SingleProductDTO> getProduct(@PathVariable Integer productId) {
        Product found = svc.getProduct(productId);

        return ResponseEntity.ok(new SingleProductDTO(new ProductDTO(found)));

    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> getProduct(@PathVariable Integer productId,
            @RequestBody SingleProductDTO dto) {
        Product reqBody = dto.getProduct().toEntity();
        Product found = svc.getProduct(productId);
        found.setName(reqBody.getName());
        found.setPrice(reqBody.getPrice());
        svc.saveProduct(found);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> delProduct(@PathVariable Integer productId) {
        svc.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
