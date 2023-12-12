package processgpt.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import processgpt.domain.*;

@Component
public class TodolistHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Todolist>> {

    @Override
    public EntityModel<Todolist> process(EntityModel<Todolist> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//todolist/{id}")
                .withRel("/todolist/{id}")
        );

        return model;
    }
}
