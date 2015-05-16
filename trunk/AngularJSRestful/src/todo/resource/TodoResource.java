/**
 * 
 */
package todo.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.SapUtilities;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import todo.dao.TodoDao;
import todo.model.Todo;

/**
 * @author allen
 *
 */
@Path("/todos")
public class TodoResource {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getTodos() {
		//System.out.println("1234!!");
		return TodoDao.queryAll();
    }
	
	@GET
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Todo getTodo(@PathParam("id") String id) {
		return TodoDao.getTodo(Integer.valueOf(id));
    }
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    public Todo getTodos(Todo todo) {
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
		
		
	    for (int i = 0; i < returnStructure.getNumRows(); i++){ 
	    	 returnStructure.setRow(i); 
	    	 String COMP_CODE = returnStructure.getString("COMP_CODE");
	    	 String COMP_NAME = returnStructure.getString("COMP_NAME");
	    	 
	    	 if (COMP_CODE.equals("TW01")) System.out.println(COMP_CODE +"   " + COMP_NAME); 
	    	 if (COMP_CODE.equals("CN01")) System.out.println(COMP_CODE +"   " + COMP_NAME); 
	    	 if (COMP_CODE.equals("CN02")) System.out.println(COMP_CODE +"   " + COMP_NAME); 
	    	 if (COMP_CODE.equals("CN03")) System.out.println(COMP_CODE +"   " + COMP_NAME); 
	    	 if (COMP_CODE.equals("CN04")) System.out.println(COMP_CODE +"   " + COMP_NAME); 
	    }
		
		return TodoDao.addTodo(todo);
    }
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void updateTodos(Todo todo) {
		TodoDao.updateTodo(todo);
    }
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void updateTodos(@PathParam("id") String id) {
		TodoDao.deleteTodo(Integer.valueOf(id));
    }
}
