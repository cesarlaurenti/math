package com.tenpo.math.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuditController {

    @Autowired
    AuditService auditService;

    @RequestMapping(value = "/audit", method = RequestMethod.GET)
    @ResponseBody
    public Page<Audit> list(Pageable pageable) {
        return auditService.list(pageable);
    }
}