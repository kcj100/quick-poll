package com.kcj.repository;

import com.kcj.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

    @Query(value = "SELECT v.* FROM vote v JOIN poll_option po ON v.option_id = po.option_id WHERE po.poll_id = ?1", nativeQuery = true)
    public Iterable<Vote> findByPoll(Long pollId);
}
