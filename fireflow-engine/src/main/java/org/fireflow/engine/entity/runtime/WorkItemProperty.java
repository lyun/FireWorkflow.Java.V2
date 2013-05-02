/**
 * Copyright 2007-2010 非也
 * All rights reserved. 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation。
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses. *
 */
package org.fireflow.engine.entity.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.fireflow.engine.entity.EntityProperty;
import org.fireflow.engine.entity.WorkflowEntity;

/**
 * @author 非也
 * @version 2.0
 */
public enum WorkItemProperty implements EntityProperty {
	ID("id"),
	STATE("state"),
	WORKITEM_NAME("workItemName"),
	SUBJECT("subject"),
	OWNER_ID("ownerId"),
	OWNER_NAME("ownerName"),
	OWNER_DEPT_ID("ownerDeptId"),
	OWNER_DEPT_NAME("ownerDeptName"),
	CREATED_TIME("claimedTime"),
	CLAIMED_TIME("startedTime"),
	END_TIME("endTime"),
	ATTACHMENT_ID("attachmentId"),
	ATTACHMENT_TYPE("attachmentType"),
	NOTE("note"),
	RESPONSIBLE_PERSON_ID("responsiblePersonId"),
	RESPONSIBLE_PERSON_NAME("responsiblePersonName"),
	RESPONSIBLE_PERSON_DEPT_ID("responsiblePersonDeptId"),
	RESPONSIBLE_PERSON_DEPT_NAME("responsiblePersonDeptName"),
	PARENT_WORKITEM_ID("parentWorkItemId"),
	REASSIGN_TYPE("reassignType"),
	ASSIGNMENT_STRATEGY("assignmentStrategy"),
	FORM_RUL("formURL"),
	
	BIZ_ID("bizId"),
	
	ACTIVITY_INSTANCE_$_ID("activityInstance.id"), 
	ACTIVITY_INSTANCE_$_PROCESSINSTANCE_ID(	"activityInstance.processInstanceId"),
	ACTIVITY_INSTANCE_$_BIZ_ID(	"activityInstance.bizId"), 
	ACTIVITY_INSTANCE_$_ACTIVITY_ID("activityInstance.nodeId"),

	ACTIVITY_INSTANCE_$_SUSPENDED("activityInstance.suspended"),

	ACTIVITY_INSTANCE_$_PROCESSS_ID("activityInstance.processId"),
	ACTIVITY_INSTANCE_$_PROCESS_NAME("activityInstance.processName"),
	ACTIVITY_INSTANCE_$_PROCESS_DISPLAY_NAME("activityInstance.processDisplayName"),
	ACTIVITY_INSTANCE_$_STEP_NUMBER("activityInstance.stepNumber"),
	ACTIVITY_INSTANCE_$_PROCINST_CREATOR_ID("activityInstance.procInstCreatorId"),
	ACTIVITY_INSTANCE_$_PROCINST_CREATOR_NAME("activityInstance.procInstCreatorName"),
	ACTIVITY_INSTANCE_$_PROCINST_CREATED_TIME("activityInstance.procInstCreatedTime");
	;
	
	
	private String propertyName = null;
	private WorkItemProperty(String propertyName){
		this.propertyName = propertyName;
	}
	
	public String getPropertyName(){
		return this.propertyName;
	}
	
	public String getColumnName(){
		return this.name();
	}
	
	public String getDisplayName(Locale locale){
		ResourceBundle resb = ResourceBundle.getBundle("myres", locale);
		return resb.getString(this.name());
	}
	
	public String getDisplayName(){
		return this.getDisplayName(Locale.getDefault());
	}
	
	public List<EntityProperty> getAllProperties(){
		List<EntityProperty> all = new ArrayList<EntityProperty>();
		all.add(ID);
		return all;
	}
	
    public static WorkItemProperty fromValue(String v) {
        for (WorkItemProperty c: WorkItemProperty.values()) {
            if (c.getPropertyName().equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	public String getEntityName(){
		return WorkflowEntity.ENTITY_NAME_WORKITEM;
	}

}
