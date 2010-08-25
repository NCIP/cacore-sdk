package gov.nih.nci.sdk.core.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GeneratorManager {

	public static final String propertiesFile = "C:/Prasad/Next/hydra/example/generator.properties";

	public void run(String script) {
		GeneratorContext context = getContext();
		// ScriptEngineManager factory = new ScriptEngineManager();
		// ScriptEngine engine = factory.getEngineByName("JavaScript");
		ScriptEngine engine = getScriptEngine();
		// InputStream is =
		// this.getClass().getResourceAsStream(script);
		try {
			FileReader reader = new FileReader(new File(script));
			// Reader reader = new InputStreamReader(is);
			engine.eval(reader);
			Invocable invocableEngine = (Invocable) engine;
			try {
				invocableEngine.invokeFunction("generate", context);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ScriptException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private GeneratorContext getContext() {
		//EPackage ePackage = EcoreUtil.getRootEPackage("C:/Prasad/Next/Project/model/sdkexample.ecore");
		ECOREDomain domain = new ECOREDomain();
		domain.setName("Person");
		domain.setPackageName("gov.nih.nci.sdk.example");
		List<ECOREAttribute> attributes = new ArrayList<ECOREAttribute>();
		attributes.add(new ECOREAttribute("name", "String"));
		attributes.add(new ECOREAttribute("addressId", "String"));
		domain.setAttributes(attributes);
		return new GeneratorContext("PojoContext", propertiesFile, domain);
	}

	private ScriptEngine getScriptEngine() {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = null;

		List<ScriptEngineFactory> scriptFactories = mgr.getEngineFactories();
		for (ScriptEngineFactory factory : scriptFactories) {
			String langName = factory.getLanguageName();
			String langVersion = factory.getLanguageVersion();
			if (langName.equals("ECMAScript") && langVersion.equals("1.6")) {
				engine = factory.getScriptEngine();
				break;
			}
		}
		return engine;
	}

	public static void main(String args[]) {
		GeneratorManager manager = new GeneratorManager();
		manager.run("C:/Prasad/Next/Project/example/scripts/WebService.js");

	}

}
