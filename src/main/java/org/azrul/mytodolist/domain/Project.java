/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.mytodolist.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.azrul.langkuik.annotations.AutoIncrementConfig;
import org.azrul.langkuik.security.role.EntityOperation;
import org.azrul.langkuik.annotations.Choice;
import org.azrul.langkuik.annotations.EntityUserMap;
import org.azrul.langkuik.annotations.FieldUserMap;
import org.azrul.langkuik.annotations.WebField;
import org.azrul.langkuik.annotations.WebEntity;
import org.azrul.langkuik.security.role.FieldOperation;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author azrulm
 */
@Entity
@Table(schema = "TODO1")
@XmlRootElement
@Indexed
@WebEntity(name="project",isRoot = true)

public class Project implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @Basic(optional = false)
    @Column(name = "PROJECT_ID")
    @WebField(name="project id", rank=0, displayInTable=false,
            autoIncrement = @AutoIncrementConfig(generator=IdGenerator.class)
    )
    private Long projectId;
    
    //-----------Project info---------------------------------------------//
    @Field(analyze = Analyze.YES)
    @Size(max = 30)
    @WebField(name="project name",rank=0, displayInTable=true, required=true)
    private String projectName;  
      
    @Size(max = 30)
    @Field(analyze = Analyze.YES)
    @WebField(name="project description",  rank=1, displayInTable=true, required=true)
    private String projectDescription;
    
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn()
    @WebField(name = "task", rank = 6, required=false)
    private Collection<Task> tasksCollection;
 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getProjectId() != null ? getProjectId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.getProjectId() == null && other.getProjectId() != null) || (this.getProjectId() != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.azrul.todolist[ projectId=" + getProjectId() + " ]";
    }

    /**
     * @return the projectId
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the projectDescription
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * @param projectDescription the projectDescription to set
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * @return the tasksCollection
     */
    public Collection<Task> getTasksCollection() {
        return tasksCollection;
    }

    /**
     * @param tasksCollection the tasksCollection to set
     */
    public void setTasksCollection(Collection<Task> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    
}
