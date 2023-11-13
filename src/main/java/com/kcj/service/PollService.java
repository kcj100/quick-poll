package com.kcj.service;

import com.kcj.domain.Poll;
import com.kcj.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public Optional<Poll> getPoll(Long pollId) {
        return pollRepository.findById(pollId);
    }

    public Poll updatePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public void deletePoll(Long pollId) {
        pollRepository.deleteById(pollId);
    }
}
