package $packageName$;

import com.sun.xml.bind.CycleRecoverable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

$importSt;separator=";"$

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "$className$" $if(namespace)$, namespace="$namespace$" $endif$ $if(propOrder)$, propOrder = {$propOrder$}$endif$)
@XmlRootElement(name="$className$" $if(namespace)$, namespace="$namespace$"$endif$)

public class $className$ implements Serializable, CycleRecoverable{

	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;
	
	$pojoAttributes()$

	$pojoOperations()$
	
    public Object onCycleDetected(Context arg0) 
    {
		return null;
	}
}
