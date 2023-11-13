package com.kcj.repository;

import com.kcj.domain.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

}
