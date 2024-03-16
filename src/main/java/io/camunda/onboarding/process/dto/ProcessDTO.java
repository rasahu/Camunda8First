package io.camunda.onboarding.process.dto;

import java.util.HashMap;

public class ProcessDTO {

	private String bpmnProcessId;	
	private   HashMap<String, Object> variables;
	
	public String getBpmnProcessId() {
		return bpmnProcessId;
	}
	public void setBpmnProcessId(String bpmnProcessId) {
		this.bpmnProcessId = bpmnProcessId;
	}
	public HashMap<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(HashMap<String, Object> variables) {
		this.variables = variables;
	}
	
}
