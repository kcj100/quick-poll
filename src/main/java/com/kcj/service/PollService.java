package com.kcj.service;

import com.kcj.domain.Option;
import com.kcj.domain.Poll;
import com.kcj.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public Poll editPoll(Poll poll, Long pollId) {
        Poll existingPoll = pollRepository.findById(pollId).orElse(null);

        if (existingPoll != null) {
            existingPoll.setQuestion(poll.getQuestion()); // Update other fields as needed

            // Synchronize the options
            Map<Long, Option> existingOptionsMap = existingPoll.getOptions().stream()
                    .collect(Collectors.toMap(Option::getId, opt -> opt));

            Set<Option> newOptions = poll.getOptions();

            for (Option newOption : newOptions) {
                Option matchingExistingOption = existingOptionsMap.get(newOption.getId());

                if (matchingExistingOption != null) {
                    matchingExistingOption.setValue(newOption.getValue()); // Update other option fields as needed
                }
            }
        }
            return pollRepository.save(existingPoll);
    }

    public void deletePoll(Long pollId) {
        pollRepository.deleteById(pollId);
    }
}
