package com.sandip.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private String eventName;

    private String EventSpeaker;

    private String eventDate;

    private String eventTime;

    private String participant;

    @OneToOne
    private User user;

    public Event(Long eventId, String eventName, String eventSpeaker, String eventDate, String eventTime,
            String participant, User user) {
        this.eventId = eventId;
        this.eventName = eventName;
        EventSpeaker = eventSpeaker;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.participant = participant;
        this.user = user;
    }

    public Event() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventSpeaker() {
        return EventSpeaker;
    }

    public void setEventSpeaker(String eventSpeaker) {
        EventSpeaker = eventSpeaker;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    @Override
    public String toString() {
        return "Event [EventSpeaker=" + EventSpeaker + ", eventDate=" + eventDate + ", eventId=" + eventId
                + ", eventName=" + eventName + ", eventTime=" + eventTime + ", participant=" + participant + ", user="
                + user + "]";
    }

}
