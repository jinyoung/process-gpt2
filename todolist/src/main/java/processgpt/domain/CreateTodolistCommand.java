package processgpt.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateTodolistCommand {

    private String activityId;
    private String activityName;
    private Date startDate;
    private Date endDate;
    private Date dueDate;
    private String processDefinitionId;
    private String processInstanceId;
    private String userId;
}
