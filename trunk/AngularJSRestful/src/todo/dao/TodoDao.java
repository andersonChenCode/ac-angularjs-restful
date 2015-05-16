/**
 * 
 */
package todo.dao;

import java.util.ArrayList;
import java.util.List;



import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.SapUtilities;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

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
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_COMPANYCODE_GETLIST");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	JCoTable returnStructure = function.getTableParameterList().getTable("COMPANYCODE_LIST"); 
    	
		try {
			connect.executeNTX(function);
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		com.sap.model.service.UserService UserService = com.sap.model.ServiceGetter.getInstance().getUserService();


	    for (int i = 0; i < returnStructure.getNumRows(); i++){ 
	    	 returnStructure.setRow(i); 
	    	 String COMP_CODE = returnStructure.getString("COMP_CODE");
	    	 String COMP_NAME = returnStructure.getString("COMP_NAME");
	    	 
	 		 com.sap.model.bo.User user =new com.sap.model.bo.User();
		 	 user.setUserName(COMP_CODE);
			 user.setAge(COMP_NAME);
			 
	    	 if (COMP_CODE.equals("TW01")) UserService.saveObject(user);
	    	 if (COMP_CODE.equals("CN01")) UserService.saveObject(user);
	    	 if (COMP_CODE.equals("CN02")) UserService.saveObject(user);
	    	 if (COMP_CODE.equals("CN03")) UserService.saveObject(user);
	    	 if (COMP_CODE.equals("CN04")) UserService.saveObject(user);
	    	 
	    }
		//int rowCount = UserService.getObjectQty("from User");
		//System.out.println("UserService >> " + rowCount);
		//List objList = UserService.getObjectResultList("from User",1,10);
	    // for (int i=0; i < objList.size(); i++) {
		//	 com.sap.model.bo.User obj = (com.sap.model.bo.User) objList.get(i);
		//     System.out.println(obj.getUserId() + "  " + obj.getUserName());
	    //}	
		
	}

	public static void deleteTodo(Integer id) {
		int index = 0;
		for(int i=0;i<todos.size();i++){
			if(todos.get(i).getId() == id) index = i;
		}
		todos.remove(index);
	}
	
}
