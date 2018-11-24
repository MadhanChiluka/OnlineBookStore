package com.bookstore.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
