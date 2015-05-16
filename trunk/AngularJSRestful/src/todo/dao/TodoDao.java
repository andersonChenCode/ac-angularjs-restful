/**
 * 
 */
package todo.dao;

import java.util.ArrayList;
import java.util.List;


import todo.model.Todo;

/**
 * @author allen
 *
 */
public class TodoDao {
	private static int idSeq = 1;
	private static List<Todo> todos;
	
	static{
		final Todo todo = new Todo(){{
			setId(idSeq++);
			setName("Complete a Todo list");
			setOwner("Allen");
			setPriority("High");
		}};
		todos = new ArrayList<Todo>(){{
			add(todo);
		}};
	}
	
	public static List<Todo> queryAll(){
		return todos;
	}
	
	public static Todo getTodo(int id){
		Todo result = null;
		for(Todo todo: todos){
			if(todo.getId() == Integer.valueOf(id)){
				result = todo;
				break;
			}
		}
		return result;
	}
	
	public static Todo addTodo(Todo todo){
		todo.setId(idSeq++);
		todos.add(todo);
		return todo;
	}

	public static void updateTodo(Todo todo) {
		Todo target = getTodo(todo.getId());
		target.setName(todo.getName());
		target.setOwner(todo.getOwner());
		target.setPriority(todo.getPriority());
		
		System.out.println(" com.sap.model.ServiceGetter.getInstance() is " + com.sap.model.ServiceGetter.getInstance());
		
		com.sap.model.service.UserService UserService = com.sap.model.ServiceGetter.getInstance().getUserService();
		int rowCount = UserService.getObjectQty("from User");
		System.out.println("UserService >> " + rowCount);
		List objList = UserService.getObjectResultList("from User",1,10);
	    for (int i=0; i < objList.size(); i++) {
			 com.sap.model.bo.User obj = (com.sap.model.bo.User) objList.get(i);
		     System.out.println(obj.getUserId() + "  " + obj.getUserName());
	    }	
		
	}

	public static void deleteTodo(Integer id) {
		int index = 0;
		for(int i=0;i<todos.size();i++){
			if(todos.get(i).getId() == id) index = i;
		}
		todos.remove(index);
	}
	
}
