package com.sandip.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandip.entity.Event;
import com.sandip.entity.User;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

    // fetching event data by user
    public Page<Event> findByUser(User user, Pageable pageable);

}
