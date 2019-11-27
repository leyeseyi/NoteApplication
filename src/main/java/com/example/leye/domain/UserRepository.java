/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leye.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leyeseyi
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    //User findByUser_id(Integer id);
}
