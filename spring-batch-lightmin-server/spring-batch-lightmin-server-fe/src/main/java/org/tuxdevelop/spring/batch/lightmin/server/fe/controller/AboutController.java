package org.tuxdevelop.spring.batch.lightmin.server.fe.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "about", havingValue = "true", matchIfMissing = true)
public class AboutController {

    @GetMapping(value = "/about")
    public void init() {
    }
}
