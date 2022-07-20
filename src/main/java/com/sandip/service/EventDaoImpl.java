package com.sandip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sandip.dao.EventDao;
import com.sandip.entity.Event;
import com.sandip.entity.User;

@Service
public class EventDaoImpl {

    @Autowired
    @Lazy
    private EventDao eventDao;

    // save event
    public void saveEvent(Event event) {
        eventDao.save(event);
    }

    // fetching event data
    public Page<Event> findAllEvent(Pageable pageable) {
        return eventDao.findAll(pageable);
    }

    // fetching event data by user
    public Page<Event> findEventByUser(User user, Pageable pageable) {
        return eventDao.findByUser(user, pageable);
    }

    // deleting event by id
    public void deleteEvent(Long id) {
        eventDao.deleteById(id);
    }

}
