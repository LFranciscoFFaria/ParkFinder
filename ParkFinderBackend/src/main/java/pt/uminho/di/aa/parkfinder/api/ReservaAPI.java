package pt.uminho.di.aa.parkfinder.api;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.uminho.di.aa.parkfinder.logicaReservas.ReservaServiceBean;

@RestController
@RequestMapping("/apiV1/reservas")
public class ReservaAPI {
    private final ApplicationContext context;
    private final ReservaServiceBean reservaServiceBean;

    @Autowired
    public ReservaAPI(ApplicationContext context, ReservaServiceBean reservaServiceBean) {
        this.context = context;
        this.reservaServiceBean = reservaServiceBean;
    }
}
