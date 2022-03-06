package com.example.fundingapi.repository;

import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
