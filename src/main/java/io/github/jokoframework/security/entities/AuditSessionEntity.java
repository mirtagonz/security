package io.github.jokoframework.security.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by afeltes on 07/09/16.
 */
@Entity
@Table(name = "audit_session")
@SequenceGenerator(name = "audit_session_id_seq", sequenceName = "audit_session_id_seq", initialValue = 1, allocationSize = 1)
public class AuditSessionEntity {

    public static final String USER_DATE = "userDate";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_session_id_seq")
    private Long id;

    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "user_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userDate;
    @Column(name = "remote_ip")
    private String remoteIp;

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String pUserAgent) {
        userAgent = pUserAgent;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date pSessionDate) {
        userDate = pSessionDate;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String pHost) {
        remoteIp = pHost;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        AuditSessionEntity rhs = (AuditSessionEntity) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.userAgent, rhs.userAgent)
                .append(this.userDate, rhs.userDate)
                .append(this.remoteIp, rhs.remoteIp)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(userAgent)
                .append(userDate)
                .append(remoteIp)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userAgent", userAgent)
                .append("userDate", userDate)
                .append("remoteIp", remoteIp)
                .toString();
    }

    @PrePersist
    public void setDefaultData() {
        setUserDate(new Date());
    }
}