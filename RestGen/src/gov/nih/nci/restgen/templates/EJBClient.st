        $HomeInterface$ ejbHome; 
        $RemoteInterface$ ejbRemote;
 
        Properties props = new Properties();
		props.load(new FileInputStream("$JNDIProperties$"));

        InitialContext initContext = null; 

        try { 
            initContext = new javax.naming.InitialContext(props); 
        }  
        catch (Exception e) { 
        	e.printStackTrace();
          return; 
        } 

        try { 
            String JNDIName = "$JNDIName$"; 
            Object objref = initContext.lookup(JNDIName); 
            ejbHome = ($HomeInterface$)PortableRemoteObject.narrow(objref,
                                             $HomeInterface$.class);
        }  
        catch(Exception e) { 
        e.printStackTrace(); 
        return; 
        } 
        try { 
            ejbRemote = ejbHome.create();  
        } 
        catch(CreateException e) {
        	e.printStackTrace();
            return; 
        }  
        catch(RemoteException e) {
        	e.printStackTrace();
            return; 
        }  

		return ejbRemote.$ejbOperation$
