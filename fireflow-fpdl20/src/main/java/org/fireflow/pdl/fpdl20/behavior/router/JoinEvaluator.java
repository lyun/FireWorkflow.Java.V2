/**
 * Copyright 2007-2010 非也
 * All rights reserved. 
 * 
 * This library is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License v3 as published by the Free Software
 * Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, see http://www.gnu.org/licenses/lgpl.html.
 *
 */
package org.fireflow.pdl.fpdl20.behavior.router;

import java.util.List;

import org.fireflow.client.WorkflowSession;
import org.fireflow.pdl.fpdl20.process.Synchronizer;
import org.fireflow.pvm.kernel.Token;

/**
 *
 * @author 非也 nychen2000@163.com
 * Fire Workflow 官方网站：www.firesoa.com 或者 www.fireflow.org
 *
 */
public interface JoinEvaluator {
	/**
	 * 汇聚逻辑描述信息
	 * @return
	 */
	public String getJoinDescription();
	/**
	 * 判断汇聚是否完成，如果完成则返回step_number最大的token,其他token置为completed状态;
	 * 否则返回null
	 * @param session
	 * @param current_token_for_router
	 * @param siblingTokens TODO
	 * @param router
	 * @return
	 */
	public int canBeFired(WorkflowSession session, Token current_token_for_router,
			List<Token> siblingTokens, Synchronizer router);
}
