package com.example.schedule.controller;


import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody Map<String, String> loginRequest,
            HttpServletRequest request) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        User user = userRepository.findByEmail(email);
        if (user == null
                || !user.getPassword().equals(password)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("이메일 또는 비밀번호가 틀렸습니다.");
        }

        // 세션에 사용자 정보 저장
        request.getSession().setAttribute("user", user);
        return ResponseEntity.ok("로그인 성공");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok("로그아웃 성공");
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
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("삭제완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 유저입니다.");
        }
    }
    }







