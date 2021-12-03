package com.hyeonjun.scm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.domain.models.Purchase;
import com.hyeonjun.scm.domain.models.User;
import com.hyeonjun.scm.dto.ProductStatDTO;
import com.hyeonjun.scm.dto.ProductStatisticsDTO;
import com.hyeonjun.scm.dto.SinglePurchaseDTO;
import com.hyeonjun.scm.dto.UserStatisticsDTO;
import com.hyeonjun.scm.dto.UserStatsDTO;
import com.hyeonjun.scm.service.ProductService;
import com.hyeonjun.scm.service.PurchaseService;
import com.hyeonjun.scm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/purchase", produces = "application/json; charset=UTF-8")
public class PurchaseController {

    @Autowired
    PurchaseService svc;
    @Autowired
    ProductService prodSvc;
    @Autowired
    UserService userSvc;

    @PostMapping
    public ResponseEntity<SinglePurchaseDTO> postPurchase(@RequestBody SinglePurchaseDTO dto) {
        Product product = prodSvc.getProduct(dto.getPurchase().getProductId());
        User user = userSvc.getUser(dto.getPurchase().getUserId());
        Purchase purchase = new Purchase(dto.getPurchase().getId(), user, product, dto.getPurchase().getPrice());
        svc.registerPurchase(purchase);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductStatisticsDTO> getProductList() {
        List<Purchase> list = svc.getPurchaseList();

        HashMap<Integer, ProductStatDTO> merged = list.stream().map(ProductStatDTO::new).collect(HashMap<Integer, ProductStatDTO>::new, (map, ele) -> {
            if (map.get(ele.getId()) != null) {
                int sum = ele.getSum() + map.get(ele.getId()).getSum();
                ele.setSum(sum);
                map.put(ele.getId(), ele);
            }
            map.put(ele.getId(), ele);

        }, HashMap::putAll);

        return ResponseEntity.ok(new ProductStatisticsDTO(new ArrayList<>(merged.values())));
    }

    @GetMapping("/user")
    public ResponseEntity<UserStatisticsDTO> getProduct() {
        List<Purchase> list = svc.getPurchaseList();

        HashMap<Integer, UserStatsDTO> merged = list.stream().map(UserStatsDTO::new).collect(HashMap::new, (map, ele) -> {
            if (map.get(ele.getId()) != null) {
                int sum = ele.getSum() + map.get(ele.getId()).getSum();
                ele.setSum(sum);
                map.put(ele.getId(), ele);
            }
            map.put(ele.getId(), ele);

        }, HashMap::putAll);

        return ResponseEntity.ok(new UserStatisticsDTO(new ArrayList<>(merged.values())));
    }

}
