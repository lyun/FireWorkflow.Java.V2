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
package org.fireflow.engine.modules.persistence.classpath;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fireflow.client.WorkflowQuery;
import org.fireflow.engine.entity.WorkflowEntity;
import org.fireflow.engine.entity.repository.ServiceDescriptor;
import org.fireflow.engine.entity.repository.ServiceDescriptorProperty;
import org.fireflow.engine.entity.repository.ServiceRepository;
import org.fireflow.engine.entity.repository.impl.ServiceDescriptorImpl;
import org.fireflow.engine.entity.repository.impl.ServiceRepositoryImpl;
import org.fireflow.engine.exception.EngineException;
import org.fireflow.engine.modules.persistence.PersistenceService;
import org.fireflow.engine.modules.persistence.ServicePersister;
import org.fireflow.misc.Utils;
import org.fireflow.model.InvalidModelException;
import org.fireflow.model.io.DeserializerException;
import org.fireflow.model.io.service.ServiceParser;
import org.fireflow.model.servicedef.ServiceDef;

/**
 * 
 * 
 * @author 非也
 * @version 2.0
 */
public class ServicePersisterClassPathImpl implements ServicePersister {
	private static Log log = LogFactory.getLog(ServicePersisterClassPathImpl.class);

	PersistenceService persistenceService = null;
	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.persistence.ServicePersister#findServiceRepositoryByFileName(java.lang.String)
	 */
	public ServiceRepository findServiceRepositoryByFileName(
			String serviceFileName) throws DeserializerException{
		if (serviceFileName==null || serviceFileName.trim().equals("")){
			throw new EngineException("The resource file name can NOT be empty!");
		}
		String fileName = serviceFileName;
		if (serviceFileName.startsWith("/") || serviceFileName.startsWith("\\")){
			fileName = serviceFileName.substring(1);
		}
		
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(fileName);				
		return repositoryFromInputStream(serviceFileName,inStream);
	}
	private ServiceRepository repositoryFromInputStream(String serviceFileName,InputStream inStream)throws DeserializerException{

		try {
			
			byte[] bytes = Utils.inputStream2ByteArray(inStream);
			ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
			
			String charset = Utils.findXmlCharset(bytesIn);
			
			List<ServiceDef> services = ServiceParser.deserialize(bytesIn);
			
			ServiceRepositoryImpl repository = new ServiceRepositoryImpl();
			repository.setServiceContent(new String(bytes,charset));
			repository.setFileName(serviceFileName);
			repository.setServices(services);
			
			
			if (services!=null){
				List<ServiceDescriptor> serviceDescriptors = new ArrayList<ServiceDescriptor>();
				for (ServiceDef svc : services){
					ServiceDescriptorImpl desc = new ServiceDescriptorImpl();
					desc.setServiceId(svc.getId());
					desc.setBizType(svc.getBizCategory());
					desc.setName(svc.getName());
					desc.setDisplayName(svc.getDisplayName());
					desc.setDescription(svc.getDescription());
					
					desc.setFileName(serviceFileName);
					
					serviceDescriptors.add(desc);
				}
				
				repository.setServiceDescriptors(serviceDescriptors);
			}
			
			return repository;
		} catch (DeserializerException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw new DeserializerException(e);
		} catch (InvalidModelException e) {
			log.error(e);
			throw new DeserializerException(e);
		}
	}
	

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.persistence.ServicePersister#persistServiceFileToRepository(java.io.InputStream, java.util.Map)
	 */
	public List<ServiceDescriptor> persistServiceFileToRepository(
			InputStream serviceFileInput,
			Map<ServiceDescriptorProperty, Object> properties) {
		throw new UnsupportedOperationException("This method is unsupported");
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.persistence.Persister#count(org.fireflow.engine.WorkflowQuery)
	 */
	public <T extends WorkflowEntity> int count(WorkflowQuery<T> q) {
		throw new UnsupportedOperationException("This method is unsupported");
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.persistence.Persister#find(java.lang.Class, java.lang.String)
	 */
	public <T extends WorkflowEntity> T find(Class<T> entityClz, String entityId) {
		throw new UnsupportedOperationException("This method is unsupported");
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.persistence.Persister#list(org.fireflow.engine.WorkflowQuery)
	 */
	public <T extends WorkflowEntity> List<T> list(WorkflowQuery<T> q) {
		throw new UnsupportedOperationException("This method is unsupported");
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.persistence.Persister#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(Object entity) {
		throw new UnsupportedOperationException("This method is unsupported");
	}
	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.persistence.Persister#getPersistenceService()
	 */
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}
	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.persistence.Persister#setPersistenceService(org.fireflow.engine.modules.persistence.PersistenceService)
	 */
	public void setPersistenceService(PersistenceService persistenceService) {
		this.persistenceService = persistenceService;
		
	}

}
