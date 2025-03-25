package org.baopen753.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(name = "created_at", updatable = false)   // createAt field should maintain even changing in record
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "updated_at", insertable = false)  //  this field should not be included in INSERT statement. Instead, using trigger in database
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", insertable = false)  // this field should not be included in INSERT statement. Instead, using trigger in database
    @LastModifiedBy
    private String updatedBy;
}
