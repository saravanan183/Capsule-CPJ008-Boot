/**
 * 
 */
package com.capsule.prg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.capsule.prg.cargo.Tasks;
import com.capsule.prg.service.TaskService;

/**
 * @author Admin
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/capsuleproject")
@RestController
public class TaskController {
	
	
	TaskService taskService;
	

	/**
	 * @param taskService the taskService to set
	 */
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/print")
	@ResponseBody
	public String printTask()
	{
		return "Yes the service is up and running";
	}

	/**
	 * find all tasks in DB
	 * @return taskList
	 */
	@GetMapping("/printalltasks")
	@ResponseBody
	public List<Tasks> printAllTasks() throws Exception {
		return taskService.findAll();
		//return new ResponseEntity<List<Tasks>>(tasks, HttpStatus.OK);
	}
	
	/**
	 * @param tasks
	 * @return
	 */
	@PostMapping(value= "/addtasks")
	public ResponseEntity<Tasks> addTasks(@RequestBody Tasks tasks, UriComponentsBuilder builder) {
		//return ResponseEntity.ok().body(taskService.addTasks(tasks));
		taskService.addTasks(tasks);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/task/{id}").buildAndExpand(tasks.getTaskId()).toUri());
        return new ResponseEntity<Tasks>(headers, HttpStatus.CREATED);
	}
	
	
	/**
	 * 
	 * @param taskId
	 * @param taskDetails
	 * @return
	 */
	/*@PutMapping(value= "/updatetasks/{id}", consumes = "application/json")
	@ResponseBody
	public ResponseEntity updateTasks(@PathVariable(value = "id") Integer taskId, @RequestBody Tasks taskDetails) {
		return ResponseEntity.ok().body(taskService.updateTasks(taskDetails,taskId));
	}*/
	
	/**
	 * 
	 * @param taskId
	 * @param taskDetails
	 * @return
	 */
	@PutMapping(value= "/updatetasks", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Tasks> updateTasks(@RequestBody Tasks taskDetails) {
		//return ResponseEntity.ok().body(taskService.updateTasks(taskDetails));
		taskService.updateTasks(taskDetails);
		return new ResponseEntity<Tasks>(taskDetails, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param taskId
	 * @return
	 */
	@DeleteMapping(value= "/deletetasks/{id}")
	@ResponseBody
	public ResponseEntity deleteTasks(@PathVariable(value = "id") Integer tasksId) {
		taskService.deleteTasks(tasksId);
		return ResponseEntity.ok().body("Tasks: "+tasksId+" delete successfully");
	}
	
	/**
	 * 
	 * @param taskId
	 * @return
	 */
	@GetMapping(value= "/findtasks/{id}")
	@ResponseBody
	public ResponseEntity<Tasks> findTasks(@PathVariable(value = "id") Integer tasksId) {
		Tasks task = taskService.findTasks(tasksId);
		return new ResponseEntity<Tasks>(task, HttpStatus.OK);
	}
	
}
