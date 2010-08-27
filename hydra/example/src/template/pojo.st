package $packageName$;

import java.io.Serializable;

$importSt;separator=";"$

public class $className$ implements Serializable{

	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;
	
	$pojoAttributes()$

	$pojoOperations()$
}
