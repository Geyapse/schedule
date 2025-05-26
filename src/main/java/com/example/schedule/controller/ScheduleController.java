package com.example.schedule.controller;

import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleRepository scheduleRepository;

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedule() {
        return ResponseEntity.ok(scheduleRepository.findAll());
    }

    // 일정 생성
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setModifiedAt(LocalDateTime.now());
        return ResponseEntity.ok(scheduleRepository.save(schedule));
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable Long id) {
        if(scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule updateData) {
        return scheduleRepository.findById(id)
                .map(schedule -> {
                    schedule.setTitle(updateData.getTitle());
                    schedule.setContent(updateData.getContent());
                    schedule.setModifiedAt(LocalDateTime.now());
                    return ResponseEntity.ok(scheduleRepository.save(schedule));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    


}












