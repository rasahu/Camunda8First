package io.camunda.onboarding.process.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SaasAuthentication;
import io.camunda.tasklist.auth.SelfManagedAuthentication;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskSearch;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.tasklist.generated.model.TaskSearchRequest;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
@Service
public class CamundaProcessService {

	 @Value("${zeebe.client.cloud.clientId}")
	  private String clientId;

	  @Value("${zeebe.client.cloud.clientSecret}")
	  private String clientSecret;

	  @Value("${zeebe.client.cloud.clusterId}")
	  private String clusterId;

	  @Value("${zeebe.client.cloud.region}")
	  private String region;

  @Autowired
  private ZeebeClient zeebeClient;
  
 
  private CamundaTaskListClient taskListClient;
  
  public long startProcessInstance(String bpmnProcessId, HashMap<String, Object> variables) {

    ProcessInstanceEvent processInstance = zeebeClient.newCreateInstanceCommand() //
            .bpmnProcessId(bpmnProcessId) //
            .latestVersion() //
            .variables(variables) //
            .send().join();
    return processInstance.getProcessInstanceKey();
  }

  public List<Task> getTasksbyProcessInstanceKey(String processInstanceKey) throws TaskListException {
	  TaskSearchRequest searchRequest = new TaskSearchRequest();
	  searchRequest.setProcessInstanceKey(processInstanceKey);
	 
	return  getCamundaTaskListClient().getTasks(searchRequest, true);
	  
  }
  private CamundaTaskListClient getCamundaTaskListClient() throws TaskListException {
	    if (taskListClient == null) {
	        SaasAuthentication sa = new SaasAuthentication(clientId, clientSecret);
	        taskListClient =
	            new CamundaTaskListClient.Builder()
	                .shouldReturnVariables()
	                .taskListUrl("https://" + region + ".tasklist.camunda.io/" + clusterId)
	                .authentication(sa)
	                .build();
	  
	    }
	    return taskListClient;
	  }

}
