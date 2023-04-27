package com.microservice.user.service;

import com.microservice.user.entity.User;
import com.microservice.user.exception.ReaderWasNotFoundException;
import com.microservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final UserRepository userRepository;

    public User addReader(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllReaders() {
        return userRepository.findAll();
    }

    public User updateReader(User user) {
        return userRepository.save(user);
    }

    public void deleteReader(Long id) {
        userRepository.deleteUserById(id);
    }

    public User findReaderById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new ReaderWasNotFoundException("Reader with id: " + id + "wa not found"));
    }
}
