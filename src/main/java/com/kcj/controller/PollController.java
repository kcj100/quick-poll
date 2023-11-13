package com.kcj.controller;

import com.kcj.domain.Poll;
import com.kcj.exception.ResourceNotFoundException;
import com.kcj.repository.PollRepository;
import com.kcj.service.PollService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("")
public class PollController {

    @Autowired
    private PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/polls")
    public ResponseEntity<Iterable<?>> getAllPolls() {
        return new ResponseEntity<>(pollService.getAllPolls(), HttpStatus.OK);
    }

    @PostMapping("/polls")
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        poll = pollService.createPoll(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollService.getPoll(pollId);
        if(poll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

    @GetMapping("/polls/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        return new ResponseEntity<> (pollService.getPoll(pollId), HttpStatus.OK);
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        verifyPoll(pollId);
        pollService.updatePoll(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollService.deletePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
