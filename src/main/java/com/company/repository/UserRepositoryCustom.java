package com.company.repository;

import com.company.entity.User;

import java.util.List;

public interface UserRepositoryCustom {

    public List<User> getAll(String name, String surname);

}
