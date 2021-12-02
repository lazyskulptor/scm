package com.hyeonjun.scm.repo;

import com.hyeonjun.scm.domain.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
