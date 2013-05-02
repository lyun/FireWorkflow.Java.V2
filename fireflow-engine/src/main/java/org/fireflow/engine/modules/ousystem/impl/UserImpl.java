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
package org.fireflow.engine.modules.ousystem.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.fireflow.engine.modules.ousystem.User;

/**
 * 
 * 
 * @author 非也
 * @version 2.0
 */
@XmlRootElement(name="user")
@XmlType(name="userType",propOrder={"deptId","deptName"})
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImpl extends AbsActor implements User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8220516987293658799L;

	@XmlElement(name="deptId")
	private String deptId = null;
	
	@XmlElement(name="deptName")
	private String deptName = null;

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.ousystem.User#getDeptId()
	 */
	public String getDeptId() {
		return deptId;
	}
	
	public void setDeptId(String argDeptId){
		this.deptId = argDeptId;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.ousystem.User#getDeptName()
	 */
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String argDeptName){
		this.deptName = argDeptName;
	}
}
