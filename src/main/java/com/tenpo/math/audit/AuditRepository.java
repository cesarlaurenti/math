package com.tenpo.math.audit;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuditRepository extends PagingAndSortingRepository<Audit, Long> {

}
