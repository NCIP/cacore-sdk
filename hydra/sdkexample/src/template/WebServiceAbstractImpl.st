package $packageName$;

import javax.jws.WebService;

$importSt;separator=";"$

@WebService
public abstract class $interfaceName$ImplAbstract implements $interfaceName$
{

$ECOREElement:{
	public $it.type$ $it.name$($it.param$)
	{
			$if(!it.isVoid)$ 		return null;$endif$
	}
}$
}
