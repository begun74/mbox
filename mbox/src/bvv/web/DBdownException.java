package bvv.web;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;

public class DBdownException extends  ExceptionHandlerWrapper{

	private ExceptionHandler wrapped;
	
		public DBdownException(String msg) {
			//super( msg);
			
			this.wrapped = wrapped;
			
		}

		@Override
		public ExceptionHandler getWrapped() {
			// TODO Auto-generated method stub
			return wrapped;
		}

		
		@Override
		public void handle() throws FacesException {
			 //Throwable throwable = context.getException();
		      
		      //FacesContext fc = FacesContext.getCurrentInstance();
			
		}
}
