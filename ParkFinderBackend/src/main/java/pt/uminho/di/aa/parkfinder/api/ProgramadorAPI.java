package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgramadorAPI {
    ApplicationContext context;

    @Autowired
    public ProgramadorAPI(ApplicationContext context) {
        this.context = context;
    }
}
