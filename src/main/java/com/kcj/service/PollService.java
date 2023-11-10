package com.kcj.service;

import com.kcj.domain.Poll;
import com.kcj.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }


    public Iterable<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public void createPoll(Poll poll) {
        this.pollRepository.save(poll);
    }
}
