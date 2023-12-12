package processgpt.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeleteTodolistCommand {

    private String id;
    private String activityId;
}
