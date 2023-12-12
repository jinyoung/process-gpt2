package processgpt.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import processgpt.domain.*;
import processgpt.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class TodolistDeletedEvent extends AbstractEvent {

    private String id;

    public TodolistDeletedEvent(Todolist aggregate) {
        super(aggregate);
    }

    public TodolistDeletedEvent() {
        super();
    }
}
//>>> DDD / Domain Event
