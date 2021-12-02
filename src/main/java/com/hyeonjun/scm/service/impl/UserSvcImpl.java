package com.hyeonjun.scm.service.impl;

import java.util.List;
import java.util.Optional;

import com.hyeonjun.scm.domain.errors.ErrorCode;
import com.hyeonjun.scm.domain.errors.NoEntityException;
import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.domain.models.Purchase;
import com.hyeonjun.scm.domain.models.User;
import com.hyeonjun.scm.repo.PurchaseRepo;
import com.hyeonjun.scm.repo.UserRepo;
import com.hyeonjun.scm.service.PurchaseService;
import com.hyeonjun.scm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSvcImpl implements UserService {

    private UserRepo repo;

    public UserSvcImpl(@Autowired UserRepo  repo) {
        this.repo = repo;
    }

    @Override
    public User getUser(Integer pk) {
        Optional<User> persisted =  repo.findById(pk);
        if (persisted.isPresent()) {
            return persisted.get();
        }
        throw new NoEntityException(ErrorCode.ENTITY_NOT_FOUND, "User(" + pk + ")가 존재하지 않습니다.");
    }
}
