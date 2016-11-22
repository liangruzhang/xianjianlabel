package com.barcode.model.common;

import java.security.AccessController;
import java.security.Principal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.security.auth.Subject;


@MappedSuperclass
public abstract class AuditableEntity extends AbstractEntity {

    @Column(name = "created_by_opr_unit", length = 50,nullable=false)
    private String createdByOprUnit;

    @Column(name = "created_by_user", length = 50)
    private String createdByUser;

    @Column(name = "updated_by_user", length = 50)
    private String updatedByUser;

    @Column(name = "created_date_time")
    private Date createdDateTime;
    
    @Column(name = "DELETED_FLAG",length =10)
    private String deleteFlag;

    public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Column(name = "updated_date_time")
    private Date updatedDateTime;

    @PrePersist
    protected void prePersist() {
        Date dateTime = new Date();
        createdDateTime = updatedDateTime = dateTime;
        String user = getCurrentUser();
        if (user != null) {
            createdByUser = updatedByUser = user;
        }
        createdByOprUnit= getOperateUnit();
        
    }

    @PreUpdate
    protected void preUpdate() {
        updatedDateTime = new Date();
        String user = getCurrentUser();
        if (user != null) {
            updatedByUser = user;
        }
        createdByOprUnit= getOperateUnit();
    }

    protected String getOperateUnit(){
    	String optUnit =System.getProperty("opt_unit", "web");
    	if (optUnit == null) {
    		return "web";
    	}
    	return optUnit;
    }
    protected String getCurrentUser() {
        Subject subject = Subject.getSubject(AccessController.getContext());
        if (subject != null && !subject.getPrincipals().isEmpty()) {
            Principal principal = subject.getPrincipals().iterator().next();
            return principal.getName();
        }
        return "alan";
    }

    public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	public String getCreatedByUser() {
        return createdByUser;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public String getCreatedByOprUnit() {
        return createdByOprUnit;
    }

    public void setCreatedByOprUnit(String createdByOprUnit) {
        this.createdByOprUnit = createdByOprUnit;
    }

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
