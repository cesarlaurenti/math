package com.tenpo.math.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuditService implements IAuditService{

    private final AuditRepository auditRepository;

    public  AuditService(AuditRepository auditRepository){
        this.auditRepository = auditRepository;
    }

    @Override
    public Page<Audit> list(Pageable pageable){
        return auditRepository.findAll(pageable);
    }

    @Override
    public void save(Audit audit){
        auditRepository.save(audit);
    }
}
