/**
 * 
 */
package com.capsule.prg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capsule.prg.cargo.Tasks;

/**
 * @author Admin
 *
 */
@Service
public interface TaskService {
	
	public List<Tasks> findAll() throws Exception;

	public Tasks addTasks(Tasks tasks);
	
	//public Tasks updateTasks(Tasks tasks, Integer taskId);
	
	public Tasks updateTasks(Tasks tasks);
	
	public void deleteTasks(Integer tasks);
	
	public Tasks findTasks(Integer tasks);
}
