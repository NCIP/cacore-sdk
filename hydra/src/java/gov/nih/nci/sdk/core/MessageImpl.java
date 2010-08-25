package gov.nih.nci.sdk.core; 

public class MessageImpl
   implements Message
{
	protected String name = "";
	protected String category = "";
	protected String message = "";

	protected MessageImpl() {}
	
	public MessageImpl(String _name, String _message)
	{
	    this.name = _name;
		this.message = _message;
	}

	public MessageImpl(String _name, String _category, String _message)
	{
		this(_name, _message);
		this.category = _category;
	}

	public String getName() { return name; }
	public String getMessage() { return message; }
	public String getCategory() { return category; }

	public void setName(String _name) { name = _name; }
	public void setMessage(String _message) { message = _message; }
	public void setCategory(String _category) { category = _category; }

	public String toString()
	{
	    return getCategory() + ":" + getName() + ":" + getMessage();
	}
}