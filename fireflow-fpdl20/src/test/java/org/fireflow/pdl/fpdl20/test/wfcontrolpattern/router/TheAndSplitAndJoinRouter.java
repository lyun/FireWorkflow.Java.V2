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
package org.fireflow.pdl.fpdl20.test.wfcontrolpattern.router;

import java.util.List;

import org.fireflow.FireWorkflowJunitEnviroment;
import org.fireflow.client.WorkflowQuery;
import org.fireflow.client.WorkflowSession;
import org.fireflow.client.WorkflowSessionFactory;
import org.fireflow.client.WorkflowStatement;
import org.fireflow.client.query.Order;
import org.fireflow.client.query.Restrictions;
import org.fireflow.engine.entity.runtime.ActivityInstance;
import org.fireflow.engine.entity.runtime.ActivityInstanceProperty;
import org.fireflow.engine.entity.runtime.ProcessInstance;
import org.fireflow.engine.entity.runtime.ProcessInstanceState;
import org.fireflow.engine.exception.InvalidOperationException;
import org.fireflow.engine.exception.WorkflowProcessNotFoundException;
import org.fireflow.engine.modules.ousystem.impl.FireWorkflowSystem;
import org.fireflow.model.InvalidModelException;
import org.fireflow.model.misc.Duration;
import org.fireflow.pdl.fpdl20.misc.FpdlConstants;
import org.fireflow.pdl.fpdl20.process.SubProcess;
import org.fireflow.pdl.fpdl20.process.WorkflowProcess;
import org.fireflow.pdl.fpdl20.process.impl.ActivityImpl;
import org.fireflow.pdl.fpdl20.process.impl.EndNodeImpl;
import org.fireflow.pdl.fpdl20.process.impl.RouterImpl;
import org.fireflow.pdl.fpdl20.process.impl.StartNodeImpl;
import org.fireflow.pdl.fpdl20.process.impl.TransitionImpl;
import org.fireflow.pdl.fpdl20.process.impl.WorkflowProcessImpl;
import org.fireflow.pvm.kernel.Token;
import org.fireflow.pvm.kernel.TokenProperty;
import org.fireflow.pvm.kernel.TokenState;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

/**
 * 
 * 
 * @author 非也
 * @version 2.0
 */
public class TheAndSplitAndJoinRouter extends FireWorkflowJunitEnviroment {

	protected static final String processName = "TheAndSplitAndJoinRouterProcess";
	protected static final String processDisplayName = "And分支And汇聚流程";
	protected static final String bizId = "ThisIsAJunitTest";
	@Test
	public void testStartProcess(){
		final WorkflowSession session = WorkflowSessionFactory.createWorkflowSession(runtimeContext,FireWorkflowSystem.getInstance());
		final WorkflowStatement stmt = session.createWorkflowStatement(FpdlConstants.PROCESS_TYPE_FPDL20);
		transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus arg0) {
				
				
				//构建流程定义
				WorkflowProcess process = getWorkflowProcess();
				
				//启动流程
				try {
					ProcessInstance processInstance = stmt.startProcess(process, bizId, null);
					
					if (processInstance!=null){
						processInstanceId = processInstance.getId();
					}
					
				} catch (InvalidModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WorkflowProcessNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidOperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				return null;
			}
		});
		
		assertResult(session);
	}
	
	/**
	 * Start-->Router1-->Activity1--|
	 *            |---->Activity2--Router2-->End
	 */
	public WorkflowProcess createWorkflowProcess(){
		WorkflowProcessImpl process = new WorkflowProcessImpl(processName,processDisplayName);
		
		SubProcess subflow = process.getMainSubProcess();
		subflow.setDuration(new Duration(5,Duration.MINUTE));
		
		StartNodeImpl startNode = new StartNodeImpl(subflow,"Start");
		RouterImpl router1 = new RouterImpl(subflow,"Router1");
		
		
		ActivityImpl activity1 = new ActivityImpl(subflow,"Activity1");
		activity1.setDuration(new Duration(6,Duration.DAY));
		
		ActivityImpl activity2 = new ActivityImpl(subflow,"Activity2");

		RouterImpl router2 = new RouterImpl(subflow,"Router2");		
		EndNodeImpl endNode = new EndNodeImpl(subflow,"End");

		
		subflow.setEntry(startNode);
		subflow.getStartNodes().add(startNode);
		subflow.getRouters().add(router1);
		subflow.getActivities().add(activity1);
		subflow.getActivities().add(activity2);
		subflow.getRouters().add(router2);
		subflow.getEndNodes().add(endNode);

		
		TransitionImpl transition1 = new TransitionImpl(subflow,"start_router1");
		transition1.setFromNode(startNode);
		transition1.setToNode(router1);
		startNode.getLeavingTransitions().add(transition1);
		router1.getEnteringTransitions().add(transition1);
		
		TransitionImpl transition2 = new TransitionImpl(subflow,"router1_activity1");
		transition2.setFromNode(router1);
		transition2.setToNode(activity1);
		router1.getLeavingTransitions().add(transition2);
		activity1.getEnteringTransitions().add(transition2);
		
		TransitionImpl transition3 = new TransitionImpl(subflow,"router1_activity2");
		transition3.setFromNode(router1);
		transition3.setToNode(activity2);
		router1.getLeavingTransitions().add(transition3);
		activity2.getEnteringTransitions().add(transition3);
		
		TransitionImpl transition4 = new TransitionImpl(subflow,"activity1_router2");
		transition4.setFromNode(activity1);
		transition4.setToNode(router2);
		activity1.getLeavingTransitions().add(transition4);
		router2.getEnteringTransitions().add(transition4);
		
		TransitionImpl transition5 = new TransitionImpl(subflow,"activity2_router2");
		transition5.setFromNode(activity2);
		transition5.setToNode(router2);
		activity2.getLeavingTransitions().add(transition5);
		router2.getEnteringTransitions().add(transition5);
		
		TransitionImpl transition6 = new TransitionImpl(subflow,"router2_end");
		transition6.setFromNode(router2);
		transition6.setToNode(endNode);
		router2.getLeavingTransitions().add(transition6);
		endNode.getEnteringTransitions().add(transition6);
		
		subflow.getTransitions().add(transition1);
		subflow.getTransitions().add(transition2);
		subflow.getTransitions().add(transition3);
		subflow.getTransitions().add(transition4);
		subflow.getTransitions().add(transition5);
		subflow.getTransitions().add(transition6);
		
		return process;
	}
	
	public void assertResult(WorkflowSession session){
		super.assertResult(session);
		
		//验证ProcessInstance信息
		WorkflowQuery<ProcessInstance> q4ProcInst = session.createWorkflowQuery(ProcessInstance.class);
		ProcessInstance procInst = q4ProcInst.get(processInstanceId);
		Assert.assertNotNull(procInst);
		
		Assert.assertEquals(bizId,procInst.getBizId());
		Assert.assertEquals(processName, procInst.getProcessId());
		Assert.assertEquals(FpdlConstants.PROCESS_TYPE_FPDL20, procInst.getProcessType());
		Assert.assertEquals(new Integer(1), procInst.getVersion());
		Assert.assertEquals(processName, procInst.getProcessName());//name 为空的情况下默认等于processId,
		Assert.assertEquals(processDisplayName, procInst.getProcessDisplayName());//displayName为空的情况下默认等于name
		Assert.assertEquals(ProcessInstanceState.COMPLETED, procInst.getState());
		Assert.assertEquals(Boolean.FALSE, procInst.isSuspended());
		Assert.assertEquals(FireWorkflowSystem.getInstance().getId(),procInst.getCreatorId());
		Assert.assertEquals(FireWorkflowSystem.getInstance().getName(), procInst.getCreatorName());
		Assert.assertEquals(FireWorkflowSystem.getInstance().getDeptId(), procInst.getCreatorDeptId());
		Assert.assertEquals(FireWorkflowSystem.getInstance().getDeptName(),procInst.getCreatorDeptName());
		Assert.assertNotNull(procInst.getCreatedTime());
		Assert.assertNotNull(procInst.getEndTime());
		Assert.assertNotNull(procInst.getExpiredTime());
		Assert.assertNull(procInst.getParentActivityInstanceId());
		Assert.assertNull(procInst.getParentProcessInstanceId());
		Assert.assertNull(procInst.getParentScopeId());
		Assert.assertNull(procInst.getNote());
		
		//验证Token信息
		WorkflowQuery<Token> q4Token = session.createWorkflowQuery(Token.class);
		q4Token.add(Restrictions.eq(TokenProperty.PROCESS_INSTANCE_ID, processInstanceId))
				.addOrder(Order.asc(TokenProperty.STEP_NUMBER));
		
		List<Token> tokenList = q4Token.list();
		Assert.assertNotNull(tokenList);
		Assert.assertEquals(14, tokenList.size());
		
		Token procInstToken = tokenList.get(0);
		Assert.assertEquals(processName+"."+WorkflowProcess.MAIN_PROCESS_NAME,procInstToken.getElementId() );
		Assert.assertEquals(processInstanceId,procInstToken.getElementInstanceId());
		Assert.assertEquals(processName,procInstToken.getProcessId());
		Assert.assertEquals(FpdlConstants.PROCESS_TYPE_FPDL20, procInstToken.getProcessType());
		Assert.assertEquals(new Integer(1), procInstToken.getVersion());
		Assert.assertEquals(TokenState.COMPLETED, procInstToken.getState());
		Assert.assertNull(procInstToken.getParentTokenId());
		Assert.assertTrue(procInstToken.isBusinessPermitted());
		Assert.assertEquals(procInst.getTokenId(), procInstToken.getId());
		
		Token startNodeToken = tokenList.get(1);
		Assert.assertEquals(processName, startNodeToken.getProcessId());
		Assert.assertEquals(new Integer(1), startNodeToken.getVersion());
		Assert.assertEquals(FpdlConstants.PROCESS_TYPE_FPDL20, startNodeToken.getProcessType());
		Assert.assertEquals(procInstToken.getId(), startNodeToken.getParentTokenId());
		Assert.assertTrue(startNodeToken.isBusinessPermitted());
		
		//检验fromToken的有效性
		for (Token t:tokenList){
			if (t!=procInstToken){
				Assert.assertNotNull(t.getFromToken());
			}
		}
		
		//验证ActivityInstance信息
		WorkflowQuery<ActivityInstance> q4ActInst = session.createWorkflowQuery(ActivityInstance.class);
		q4ActInst.add(Restrictions.eq(ActivityInstanceProperty.PROCESS_INSTANCE_ID, processInstanceId))
				.add(Restrictions.eq(ActivityInstanceProperty.NODE_ID, processName+"."+WorkflowProcess.MAIN_PROCESS_NAME+".Activity1"));
		List<ActivityInstance> actInstList = q4ActInst.list();
		Assert.assertNotNull(actInstList);
		Assert.assertEquals(1, actInstList.size());
		ActivityInstance activityInstance = actInstList.get(0);
		Assert.assertEquals(bizId, activityInstance.getBizId());
		Assert.assertEquals("Activity1", activityInstance.getName());
		Assert.assertEquals("Activity1", activityInstance.getDisplayName());
		Assert.assertEquals(processInstanceId, activityInstance.getParentScopeId());
		Assert.assertNotNull(activityInstance.getCreatedTime());
		Assert.assertNotNull(activityInstance.getStartedTime());
		Assert.assertNotNull(activityInstance.getEndTime());
		Assert.assertNotNull(activityInstance.getExpiredTime());
		Assert.assertNotNull( activityInstance.getTokenId());
		Assert.assertNotNull(activityInstance.getTokenId());
		Assert.assertNotNull(activityInstance.getScopeId());
		
		Assert.assertEquals(new Integer(1),activityInstance.getVersion());
		Assert.assertEquals(FpdlConstants.PROCESS_TYPE_FPDL20,activityInstance.getProcessType());
		Assert.assertEquals(procInst.getProcessName(), activityInstance.getProcessName());
		Assert.assertEquals(procInst.getProcessDisplayName(), activityInstance.getProcessDisplayName());
		
	}	

}
