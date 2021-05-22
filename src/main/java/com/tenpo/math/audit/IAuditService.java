package com.tenpo.math.audit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAuditService {

    public Page<Audit> list(Pageable pageable);
}
