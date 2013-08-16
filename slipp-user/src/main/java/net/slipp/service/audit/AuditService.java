package net.slipp.service.audit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.slipp.dao.audit.AuditDao;
import net.slipp.domain.audit.AuditObject;

@Service
@Transactional
public class AuditService {

	@Resource(name="auditDao")
	private AuditDao auditdao;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void log(AuditObject audit) {
		auditdao.log(audit);
	}
	
	public void setAuditDao (AuditDao auditDao) {
		this.auditdao = auditDao;
	}
}
