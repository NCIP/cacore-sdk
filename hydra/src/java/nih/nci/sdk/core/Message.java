package gov.nih.nci.sdk.core; 

public interface Message
{
	public String getName();
	public String getMessage();
	public String getCategory();

	public void setName(String _name);
	public void setMessage(String _message);
	public void setCategory(String _category);
}