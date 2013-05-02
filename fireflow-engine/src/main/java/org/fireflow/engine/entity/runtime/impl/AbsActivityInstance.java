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
package org.fireflow.engine.entity.runtime.impl;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.fireflow.client.WorkflowSession;
import org.fireflow.client.WorkflowStatement;
import org.fireflow.client.impl.WorkflowStatementLocalImpl;
import org.fireflow.engine.entity.AbsWorkflowEntity;
import org.fireflow.engine.entity.repository.ProcessKey;
import org.fireflow.engine.entity.runtime.ActivityInstance;
import org.fireflow.engine.entity.runtime.ActivityInstanceState;
import org.fireflow.engine.entity.runtime.ProcessInstance;
import org.fireflow.engine.exception.InvalidOperationException;
import org.fireflow.model.InvalidModelException;
import org.fireflow.server.support.DateTimeXmlAdapter;


/**
 * @author 非也
 * @version 2.0
 */
@XmlType(name="absActivityInstanceType")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ActivityInstanceImpl.class,ActivityInstanceHistory.class})
public abstract class AbsActivityInstance extends AbsWorkflowEntity implements ActivityInstance {

	protected String procInstCreatorId = null;
	protected String procInstCreatorName = null;
	
	@XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
	protected Date procInstCreatedTime = null;
	protected String name = null;
	protected String displayName = null;
	protected String nodeId = null;
    
	protected String processId = null;
	protected Integer version = null;
	protected String processType = null;   
	protected String subflowId = null;
	protected String processName = null;
	protected String processDisplayName = null;
	protected String subflowName = null;
	protected String subflowDisplayName = null;
	protected String bizCategory = null;
	protected String serviceId = null;
	protected String serviceVersion = null;
	protected String serviceType = null;
    
	protected String bizId = null;
	protected String subBizId = null;

	protected ActivityInstanceState state = ActivityInstanceState.INITIALIZED;
	protected Boolean suspended = Boolean.FALSE;
	
	@XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
	protected Date createdTime = null;
	
	@XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
	protected Date startedTime = null;
	
	@XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
	protected Date expiredTime = null;
	
	@XmlJavaTypeAdapter(DateTimeXmlAdapter.class)
	protected Date endTime = null;

	protected String processInstanceId = null;
	protected String parentScopeId = null;
	protected String tokenId = null;
	protected Integer stepNumber = null;
    

	protected String targetActivityId = null;
	protected String fromActivityId = null;
	protected Boolean canBeWithdrawn = true;

	protected String note = null;


//	/* (non-Javadoc)
//	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getActivity(org.fireflow.engine.WorkflowSession)
//	 */
//	@Override
//	public Object getActivity(WorkflowSession session) throws EngineException {
//
//		return null;
//	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getActivityId()
	 */
	public String getNodeId() {
		
		return this.nodeId;
	}
	
	public void setNodeId(String nodeid){
		this.nodeId = nodeid;
	}



	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getBizId()
	 */
	public String getBizId() {
		return this.bizId;
	}
	
	public void setBizId(String bizId){
		this.bizId = bizId;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getCreatedTime()
	 */
	public Date getCreatedTime() {
		return this.createdTime;
	}
	
	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getDisplayName()
	 */
	public String getDisplayName() {
		
		return this.displayName;
	}
	
	public void setDisplayName(String dispName){
		this.displayName = dispName;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getEndTime()
	 */
	public Date getEndTime() {
		
		return this.endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getExpiredTime()
	 */
	public Date getExpiredTime() {
		
		return this.expiredTime;
	}
	
	public void setExpiredTime(Date expiredTime){
		this.expiredTime = expiredTime;
	}


	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getName()
	 */
	public String getName() {
		
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getNote()
	 */
	public String getNote() {
		
		return this.note;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getProcessId()
	 */
	public String getProcessId() {
		
		return this.processId;
	}
	
	public void setProcessId(String processId){
		this.processId = processId;
	}
	
	public String getSubProcessId(){
		return this.subflowId;
	}
	
	public void setSubProcessId(String subflowId){
		this.subflowId = subflowId;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getProcessInstance(org.fireflow.engine.WorkflowSession)
	 */
	public ProcessInstance getProcessInstance(WorkflowSession session) {
		WorkflowStatementLocalImpl statement = (WorkflowStatementLocalImpl)session.createWorkflowStatement(this.getProcessType());
		return statement.getEntity(this.getProcessInstanceId(), ProcessInstance.class);
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getProcessInstanceId()
	 */
	public String getProcessInstanceId() {
		
		return this.processInstanceId;
	}
	
	public void setProcessInstanceId(String processInstanceId){
		this.processInstanceId = processInstanceId;
	}
	
	public String getProcessType(){
		return this.processType;
	}
	
	public void setProcessType(String processType){
		this.processType = processType;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getServiceId()
	 */
	public String getServiceId() {
		
		return this.serviceId;
	}

	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	
	
	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getServiceType()
	 */
	public String getServiceType() {
		
		return this.serviceType;
	}
	
	public void setServiceType(String serviceType){
		this.serviceType = serviceType;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getStartedTime()
	 */
	public Date getStartedTime() {
		return this.startedTime;
	}
	
	public void setStartedTime(Date startedTime){
		this.startedTime = startedTime;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getState()
	 */
	public ActivityInstanceState getState() {
		return this.state;
	}
	
	public void setState(ActivityInstanceState state){
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getStepNumber()
	 */
	public Integer getStepNumber() {
		return this.stepNumber;
	}
	
	public void setStepNumber(Integer i){
		this.stepNumber = i;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getSubBizId()
	 */
	public String getSubBizId() {
		return this.subBizId;
	}
	
	public void setSubBizId(String s){
		this.subBizId = s;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getTargetActivityId()
	 */
	public String getTargetActivityId() {
		return this.targetActivityId;
	}
	
	public void setTargetActivityId(String s){
		this.targetActivityId = s;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getVersion()
	 */
	public Integer getVersion() {
		
		return this.version;
	}
	
	public void setVersion(Integer v){
		this.version = v;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#getWorkflowProcess(org.fireflow.engine.WorkflowSession)
	 */
	public Object getWorkflowProcess(WorkflowSession session)
	throws InvalidModelException{
		WorkflowStatement stmt = session.createWorkflowStatement(this.getProcessType());
		ProcessKey pk = new ProcessKey(this.processId,this.version,this.processType);
		return stmt.getWorkflowProcess(pk);
	}
	


	/* (non-Javadoc)
	 * @see org.fireflow.engine.entity.runtime.ActivityInstance#isSuspended()
	 */
	public Boolean isSuspended() {
		
		return this.suspended;
	}
	
	public void setSuspended(Boolean b){
		this.suspended = b;
	}

	public String getScopeId(){
		return this.id;
	}
	
	public String getProcessElementId(){
		return this.nodeId;
	}
	
	public String getParentScopeId(){
		return this.parentScopeId;
	}
	
	public void setParentScopeId(String pscopeId){
		this.parentScopeId = pscopeId;
	}
	
	public Object getVariableValue(WorkflowSession session,String name){
		WorkflowStatement stmt = session.createWorkflowStatement(this.getProcessType());
		return stmt.getVariableValue(this, name);
	}
	public void setVariableValue(WorkflowSession session ,String name ,Object value)throws InvalidOperationException{
		WorkflowStatement stmt = session.createWorkflowStatement(this.getProcessType());
		stmt.setVariableValue(this, name,value);
	}
	public void setVariableValue(WorkflowSession session ,String name ,Object value,Properties headers)throws InvalidOperationException{
		WorkflowStatement stmt = session.createWorkflowStatement(this.getProcessType());
		stmt.setVariableValue(this, name,value,headers);
	}
	
	public Map<String,Object> getVariableValues(WorkflowSession session){
		WorkflowStatement stmt = session.createWorkflowStatement(this.getProcessType());
		return stmt.getVariableValues(this);
	}	
	
	public void setTokenId(String tokenId){
		this.tokenId = tokenId;
	}
	
	public String getTokenId(){
		return this.tokenId;
	}

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @param processName the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * @return the processDisplayName
	 */
	public String getProcessDisplayName() {
		return processDisplayName;
	}

	/**
	 * @param processDisplayName the processDisplayName to set
	 */
	public void setProcessDisplayName(String processDisplayName) {
		this.processDisplayName = processDisplayName;
	}
	
	public String getSubProcessName(){
		return this.subflowName;
	}
	
	public void setSubProcessName(String subflowName){
		this.subflowName = subflowName;
	}
	
	public String getSubProcessDisplayName(){
		return this.subflowDisplayName;
	}
	
	public void setSubProcessDisplayName(String subflowDisplayName){
		this.subflowDisplayName = subflowDisplayName;
	}

	/**
	 * @return the canBeWithdrawn
	 */
	public Boolean getCanBeWithdrawn() {
		return canBeWithdrawn;
	}

	/**
	 * @param canBeWithdrawn the canBeWithdrawn to set
	 */
	public void setCanBeWithdrawn(Boolean canBeWithdrawn) {
		this.canBeWithdrawn = canBeWithdrawn;
	}

	public String getBizType() {
		return bizCategory;
	}

	public void setBizType(String bizCategory) {
		this.bizCategory = bizCategory;
	}

	public String getProcInstCreatorId() {
		return procInstCreatorId;
	}

	public void setProcInstCreatorId(String creatorId) {
		this.procInstCreatorId = creatorId;
	}

	public String getProcInstCreatorName() {
		return procInstCreatorName;
	}

	public void setProcInstCreatorName(String creatorName) {
		this.procInstCreatorName = creatorName;
	}

	public Date getProcInstCreatedTime() {
		return procInstCreatedTime;
	}

	public void setProcInstCreatedTime(Date processInstanceCreatedTime) {
		this.procInstCreatedTime = processInstanceCreatedTime;
	}
	
	
}
