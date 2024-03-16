package io.camunda.onboarding.process.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.onboarding.process.dto.ProcessDTO;
import io.camunda.onboarding.process.service.CamundaProcessService;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.exception.TaskListException;

@RestController
public class CamundaProcessController {

    Logger logger = LoggerFactory.getLogger(CamundaProcessController.class);
    
    @Autowired
    private CamundaProcessService processService;
    
    @PostMapping(value = "startProcess")
    public ResponseEntity<Long> startProcessInstance(@RequestBody ProcessDTO processDTO){
    	System.out.println("BPM Process Started with process Id ::" +processDTO.getBpmnProcessId());
    	HashMap<String,Object> hm=new HashMap();
        hm.put("name","rakesh");
        processDTO.setVariables(hm);
    	return ResponseEntity.ok(processService.startProcessInstance(processDTO.getBpmnProcessId(), processDTO.getVariables()));
    }
    
    @GetMapping(value = "getTasks")
    public ResponseEntity<List<Task>> getTasks(@RequestParam(value = "processDefKey")String processDefKey) throws TaskListException{
    	
    	return ResponseEntity.ok(processService.getTasksbyProcessInstanceKey(processDefKey));
    }
    
    @GetMapping(value = "sayHello")
    public ResponseEntity<String> sayHello(){
    	System.out.println("Say Hello Methhod ");
    	return ResponseEntity.ok("Chal Gaya BHai Nagar Raod");
    }
}
