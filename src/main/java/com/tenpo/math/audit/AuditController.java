package com.tenpo.math.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuditController {

    final IAuditService auditService;

    @Autowired
    public AuditController(IAuditService auditService){
        this.auditService = auditService;
    }

    @GetMapping("/audit")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Audit> list(Pageable pageable) {
        return auditService.list(pageable);
    }
}
