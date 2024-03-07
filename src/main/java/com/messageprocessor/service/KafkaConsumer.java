package com.messageprocessor.service;

import com.messageprocessor.entity.Employee;
import com.messageprocessor.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final EmployeeRepository employeeRepository;

    @KafkaListener(topics = "kafkamessageprocessor", groupId = "id",
            containerFactory = "kafkaListenerFactory")
    public void consumeJson(Employee employee) {
        log.info("Received new message {}", employee.toString());
        Employee savedEmployee = employeeRepository.saveAndFlush(employee);
        log.info("Employee saved successfully with id: {}", savedEmployee.getId());
    }
}