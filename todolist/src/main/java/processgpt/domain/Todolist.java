package processgpt.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import processgpt.TodolistApplication;
import processgpt.domain.TodolistCreatedEvent;
import processgpt.domain.TodolistDeletedEvent;

@Entity
@Table(name = "Todolist_table")
@Data
//<<< DDD / Aggregate Root
public class Todolist {

    @Id
    private String id;

    private String activityId;

    private String activityName;

    private Date startDate;

    private Date endDate;

    private Date dueDate;

    private String processDefinitionId;

    private String processInstanceId;

    private String userId;

    @PostPersist
    public void onPostPersist() {
        TodolistCreatedEvent todolistCreatedEvent = new TodolistCreatedEvent(
            this
        );
        todolistCreatedEvent.publishAfterCommit();

        TodolistDeletedEvent todolistDeletedEvent = new TodolistDeletedEvent(
            this
        );
        todolistDeletedEvent.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static TodolistRepository repository() {
        TodolistRepository todolistRepository = TodolistApplication.applicationContext.getBean(
            TodolistRepository.class
        );
        return todolistRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateTodolist(UpdateTodolistCommand updateTodolistCommand) {
        //implement business logic here:

        TodolistUpdatedEvent todolistUpdatedEvent = new TodolistUpdatedEvent(
            this
        );
        todolistUpdatedEvent.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
