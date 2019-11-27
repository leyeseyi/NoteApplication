/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leye.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Leyeseyi
 */
public interface NoteRepository extends JpaRepository<Note, Integer>{
    
    List<Note> findByUserIdOrderByUpdatedAtDesc(Integer id);
}
