/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.mytodolist.domain;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.azrul.langkuik.annotations.AutoIncrementConfig;
import org.azrul.langkuik.annotations.Choice;
import org.azrul.langkuik.annotations.EntityUserMap;
import org.azrul.langkuik.annotations.FieldUserMap;
import org.azrul.langkuik.annotations.WebEntity;
import org.azrul.langkuik.annotations.WebField;
import org.azrul.langkuik.security.role.EntityOperation;
import org.azrul.langkuik.security.role.FieldOperation;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

/**
 *
 * @author azrulm
 */
@Entity
@Table(schema = "TODO1")
@XmlRootElement
@Indexed
@WebEntity(name = "task", isRoot = false)
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TASK_ID")
    @Field(analyze = Analyze.NO)
    @WebField(name = "task id", rank = 0, displayInTable = true,
    autoIncrement = @AutoIncrementConfig(generator = IdGenerator.class))
    private Long taskId;

    @Column(name = "DEADLINE")
    @Temporal(TemporalType.TIMESTAMP)
    @WebField(name = "deadline", rank = 1,displayInTable = true, required = true)
    private Date deadline;

    @Size(max = 16)
    @Column(name = "TASK_DESCRIPTION")
    @WebField(name = "description", rank = 2,displayInTable = true, required = true)
    private String description;

   @Size(max = 1)
    @WebField(name="status", rank=3,displayInTable = true,required =false,
            choices={
                @Choice(display="Pending",textValue="P"),
                @Choice(display="Done",textValue="D")
            }
    )
    @Column(name = "STATUS")
    @Audited
    private String status;

    @WebField(name = "attachment", rank = 4)
    @OneToMany(/*mappedBy = "taskId", */fetch = FetchType.LAZY)
    @JoinColumn()
    private Collection<Attachment> attachmentCollection;

    public Task() {
    }

    public Task(Long taskId) {
        this.taskId = taskId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.azrul.todolist[ taskId=" + taskId + " ]";
    }

    @PrePersist
    void prePersist() {
       updateDeadline();
    }

    void updateDeadline() {
        if (deadline == null) {
            GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
            cal.setTime(new Date());
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            deadline= cal.getTime();
        }
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Attachment> getAttachmentCollection() {
        return attachmentCollection;
    }

    public void setAttachmentCollection(Collection<Attachment> attachmentCollection) {
        this.attachmentCollection = attachmentCollection;
    }

   

}
