package org.fireflow.service.mock;

import java.io.InputStream;

import org.fireflow.engine.context.RuntimeContext;
import org.fireflow.engine.entity.repository.ProcessDescriptor;
import org.fireflow.engine.entity.repository.ProcessKey;
import org.fireflow.engine.entity.repository.ProcessRepository;
import org.fireflow.engine.entity.runtime.ActivityInstance;
import org.fireflow.engine.exception.EngineException;
import org.fireflow.engine.modules.process.ProcessUtil;
import org.fireflow.model.InvalidModelException;
import org.fireflow.model.binding.ResourceBinding;
import org.fireflow.model.binding.ServiceBinding;
import org.fireflow.model.data.Property;
import org.fireflow.model.resourcedef.ResourceDef;
import org.fireflow.model.servicedef.ServiceDef;

public class ProcessUtilMock implements ProcessUtil {

	public void setRuntimeContext(RuntimeContext ctx) {
		// TODO Auto-generated method stub

	}

	public RuntimeContext getRuntimeContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public String serializeProcess2Xml(Object process)
			throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object deserializeXml2Process(InputStream inStream)
			throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public ProcessRepository serializeProcess2ProcessRepository(Object process)
			throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiceBinding getServiceBinding(ProcessKey processKey,
			String subflowId, String activityId) throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public ResourceBinding getResourceBinding(ProcessKey processKey,
			String subflowId, String activityId) throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findActivity(ProcessKey processKey, String subflow, String activityId)
			throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public Property getProperty(ProcessKey processKey, String processElementId, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.process.ProcessUtil#getProcessEntryElementId(java.lang.String, int, java.lang.String)
	 */
	public String getProcessEntryId(String workflowProcessId,
			int version, String processType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.context.EngineModule#init(org.fireflow.engine.context.RuntimeContext)
	 */
	public void init(RuntimeContext runtimeContext) throws EngineException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.process.ProcessUtil#getServiceDef(org.fireflow.engine.entity.runtime.ActivityInstance, java.lang.Object, java.lang.String)
	 */
	public ServiceDef getServiceDef(ActivityInstance activityInstance,
			Object activity, String serviceId) {
		ActivityMock mock = (ActivityMock)activity;
		return mock.getServiceDef();
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.process.ProcessUtil#getResourceDef(org.fireflow.engine.entity.runtime.ActivityInstance, java.lang.Object, java.lang.String)
	 */
	public ResourceDef getResourceDef(ActivityInstance activityInstance,
			Object activity, String resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.process.ProcessUtil#generateProcessDescriptor(java.lang.Object)
	 */
	public ProcessDescriptor generateProcessDescriptor(Object process) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.process.ProcessUtil#getServiceBinding(java.lang.Object)
	 */
	public ServiceBinding getServiceBinding(Object activity)
			throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.process.ProcessUtil#getResourceBinding(java.lang.Object)
	 */
	public ResourceBinding getResourceBinding(Object activity)
			throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.process.ProcessUtil#getProperty(java.lang.Object, java.lang.String)
	 */
	public Property getProperty(Object workflowDefinitionElement,
			String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fireflow.engine.modules.process.ProcessUtil#findSubProcess(org.fireflow.engine.entity.repository.ProcessKey, java.lang.String)
	 */
	public Object findSubProcess(ProcessKey processKey, String subflowId)
			throws InvalidModelException {
		// TODO Auto-generated method stub
		return null;
	}

}
