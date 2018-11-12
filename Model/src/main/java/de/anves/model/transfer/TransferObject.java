package src.main.java.de.anves.model.transfer;


import java.io.Serializable;

public class TransferObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1337L;

	private TransferAction action;
	private Serializable object;
	private String objectClassName;
	
	public TransferObject(Serializable obj, TransferAction action) {
		this.action = action;
		this.object = obj;
		this.objectClassName = object.getClass().getSimpleName();
	}

	public TransferAction getAction() {
		return action;
	}

	public void setAction(TransferAction action) {
		this.action = action;
	}

	public Serializable getObject() {
		return object;
	}

	public void setObject(Serializable object) {
		this.object = object;
	}

	public String getObjectClassName() {
		return objectClassName;
	}
}
