package processgpt.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import processgpt.domain.*;
import processgpt.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class TodolistCreatedEvent extends AbstractEvent {

    private String id;
    private String activityId;
    private String activityName;
    private Date startDate;
    private Date endDate;
    private Date dueDate;
    private String processDefinitionId;
    private String processInstanceId;
    private String userId;

    public TodolistCreatedEvent(Todolist aggregate) {
        super(aggregate);
    }

    public TodolistCreatedEvent() {
        super();
    }
}
//>>> DDD / Domain Event
