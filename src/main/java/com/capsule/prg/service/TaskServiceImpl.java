/**
 * 
 */
package com.capsule.prg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capsule.prg.Exceptions.TasksNotFoundException;
import com.capsule.prg.cargo.Tasks;
import com.capsule.prg.dao.TaskDao;

/**
 * @author Admin
 *
 */
@Transactional
@Service
public class TaskServiceImpl implements TaskService{


	@Autowired
	TaskDao taskDao;
	
	@Override
	public List<Tasks> findAll() throws Exception{
		return taskDao.findAll();
	}

	@Override
	public Tasks addTasks(Tasks tasks) {
		return taskDao.addTasks(tasks);
	}

	@Override
	public Tasks findTasks(Integer tasks) {
		Optional<Tasks> task= taskDao.findTasks(tasks);
		Tasks returnTasks = null;
		 if (!task.isPresent())
		      throw new TasksNotFoundException("tasks id not found :" + tasks);
		 
		if(task.get()!=null) 
		{
			returnTasks = new Tasks();
			returnTasks = task.get();
		}
		return returnTasks;
	}
	
	/*@Override
	public Tasks updateTasks(Tasks tasks, Integer taskId) {
		Optional<Tasks> task= taskDao.findTasks(taskId);
		Tasks updateTasks = null;
		 if (task.isPresent())
		 {
			 updateTasks = taskDao.updateTasks(tasks);
		 }
		 else
		 {
			 throw new TasksNotFoundException("UpdateTasks failed due to ID not found:"+taskId);
		 }
		return updateTasks;
	}*/
	@Override
	public Tasks updateTasks(Tasks tasks) {
		
		return taskDao.updateTasks(tasks);
		
	}
	@Override
	public void deleteTasks(Integer tasksId) {
		Optional<Tasks> task= taskDao.findTasks(tasksId);
		 if (task.isPresent())
		 {
			 taskDao.deleteTasks(tasksId);
		 }
		 else
		 {
			 throw new TasksNotFoundException("DeleteTasks failed due to Id not found:"+tasksId);
		 }
	}



}
