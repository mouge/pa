/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.fyg.pa.bpmn;

import java.util.List;

import javax.annotation.Resource;



import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



/**
 * @author Joram Barrez
 */
@Component
public class MyTask {

	@Resource(name="identityService")
	private IdentityService identityService;
	
	@Resource(name="taskService")
	private TaskService taskService;
	

	public void init() {
		identityService.saveUser(identityService.newUser("kermit"));
        identityService.saveUser(identityService.newUser("gonzo"));
	}

	public String getName() {
		return("activiti");
	}

	public void setIdentityService(IdentityService is) {
		identityService = is;

	}
	
	public void setTaskService(TaskService ts) {
        taskService = ts;
	}


	public IdentityService getIdentityService() {
		return identityService;
	}
	
	public TaskService getTaskService() {
		return taskService;
	}
 
	public String getTask() {

    // Create and save task
    Task task = taskService.newTask();
    task.setName("test.....Task......(mouge)");
    taskService.saveTask(task);
    String taskId = task.getId();

    // Add user as candidate user
    taskService.addCandidateUser(taskId, "kermit");
    taskService.addCandidateUser(taskId, "gonzo");

    // Retrieve task list for jbarrez
    List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("kermit").list();
    System.out.printf("kermit has %d task\n",tasks.size());
    return(tasks.get(0).getName());

    // Retrieve task list for tbaeyens
    /*tasks = taskService.createTaskQuery().taskCandidateUser("gonzo").list();
    System.out.printf("gonzo has %d task", tasks.size());
    System.out.printf("gonzo'task is %s", tasks.get(0).getName());

    // Claim task
    taskService.claim(taskId, "kermit");

    // Tasks shouldn't appear in the candidate tasklists anymore
    if (taskService.createTaskQuery().taskCandidateUser("kermit").list().isEmpty())
    	System.out.print("kermit's task is empty");
    if (taskService.createTaskQuery().taskCandidateUser("gonzo").list().isEmpty())
    	System.out.print("gonzo's task is empty");

    // Complete task
    taskService.deleteTask(taskId, true);

    // Task should be removed from runtime data
    // TODO: check for historic data when implemented!
    if (taskService.createTaskQuery().taskId(taskId).singleResult() == null)
    	System.out.print("task is empty");*/
  }

}
