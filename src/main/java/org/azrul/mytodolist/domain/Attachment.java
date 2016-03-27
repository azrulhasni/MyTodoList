/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.mytodolist.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.azrul.langkuik.annotations.AutoIncrementConfig;
import org.azrul.langkuik.annotations.EntityUserMap;
import org.azrul.langkuik.annotations.FieldUserMap;
import org.azrul.langkuik.annotations.WebEntity;
import org.azrul.langkuik.annotations.WebField;
import org.azrul.langkuik.framework.customtype.attachment.AttachmentCustomType;
import org.azrul.langkuik.security.role.EntityOperation;
import org.azrul.langkuik.security.role.FieldOperation;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author azrulm
 */
@Entity
@Table(schema = "TODO1")
@XmlRootElement
@Indexed
@WebEntity(name="attachment")
public class Attachment implements AttachmentCustomType,Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ATTACHMENT_ID")
    @WebField(name = "attachment id", rank = 0, displayInTable = false,
    autoIncrement = @AutoIncrementConfig(generator=IdGenerator.class))
    private Long attachmentId;
    
    @Column(name = "FILE_NAME")
    private String fileName;
    
    @Column(name = "MIME_TYPE")
    private String mimeType;
    
    @Column(name = "RELATIVE_LOCATION")
    private String relativeLocation;
    
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public Attachment() {
    }

    public Attachment(Long id) {
        this.attachmentId = id;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    
    public String getFileName() {
        return fileName;
    }

  
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

  
    public String getRelativeLocation() {
        return relativeLocation;
    }

    
    public void setRelativeLocation(String relativeLocation) {
        this.relativeLocation = relativeLocation;
    }

    
    public Date getCreationDate() {
        return creationDate;
    }

    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }



    
    public int hashCode() {
        int hash = 0;
        hash += (attachmentId != null ? attachmentId.hashCode() : 0);
        return hash;
    }

    
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the attachmentId fields are not set
        if (!(object instanceof Attachment)) {
            return false;
        }
        Attachment other = (Attachment) object;
        if ((this.attachmentId == null && other.attachmentId != null) || (this.attachmentId != null && !this.attachmentId.equals(other.attachmentId))) {
            return false;
        }
        return true;
    }

    
    public String toString() {
        return fileName;
    }

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
}
