package com.tenpo.math.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @Autowired
    AuditRepository auditRepository;

    public Page<Audit> list(Pageable pageable){
        return auditRepository.findAll(pageable);
    }
}
