package com.example.schedule.controller;


import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserRepository userRepository;

    // 전체 유저 조회
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    // 유저 생성
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        return ResponseEntity.ok(userRepository.save(user));
    }

    // 유저 수정
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updateData){
        return userRepository.findById(id)
        .map(user -> {
            user.setUsername(updateData.getUsername());
            user.setEmail(updateData.getEmail());
            user.setModifiedAt(LocalDateTime.now());
            return ResponseEntity.ok(userRepository.save(user));
        })
                .orElse(ResponseEntity.notFound().build());
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }






}
