package org.tuxdevelop.spring.batch.lightmin.server.fe.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "events", havingValue = "true", matchIfMissing = true)
public class EventsController extends CommonController {

    @GetMapping(value = "/events")
    public RedirectView init(final HttpServletRequest request) {
        //make the job-execution-events page the default one, until an events dashboard will be usefull
        return createRedirectView("journals", request);
    }
}
