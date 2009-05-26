package test.writable;


import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor;

public class TwoLevelInheritanceWritableApiTest extends SDKWritableApiBaseTest {
	private static Logger log = Logger.getLogger(TwoLevelInheritanceWritableApiTest.class);
	public static String getTestCaseName() {
		return "Two Level Inheritance WritableApi Test Case";
	}
	
	public void testSaveObjectWithTwoLevelInheritance(){
		log.debug("\n-------testSaveObjectWithTwoLevelInheritance()-------");
		Display display=new Display();
		display.setHeight(20);
		display.setWidth(30);
		Monitor monitor=new Monitor();
		monitor.setHeight(20);
		monitor.setWidth(30);
		monitor.setBrand("lcd");
		CRTMonitor crtMonitor=new CRTMonitor();
		crtMonitor.setHeight(20);
		crtMonitor.setWidth(30);
		crtMonitor.setBrand("lcd");
		crtMonitor.setRefreshRate(20);
		LCDMonitor lcdMonitor=new LCDMonitor();
		lcdMonitor.setHeight(20);
		lcdMonitor.setWidth(30);
		lcdMonitor.setBrand("lcd");
		lcdMonitor.setDpiSupported(20);
		
		save(display);
		save(monitor);
		save(crtMonitor);
		save(lcdMonitor);
		
		Display resultDisplay=(Display)getObject(Display.class, display.getId());
		Monitor resultMonitor=(Monitor)getObject(Monitor.class, monitor.getId());
		CRTMonitor resultCRTMonitor=(CRTMonitor)getObject(CRTMonitor.class, crtMonitor.getId());
		LCDMonitor resultLCDMonitor=(LCDMonitor)getObject(LCDMonitor.class, lcdMonitor.getId());
		Assert.assertEquals(display.getHeight(), resultDisplay.getHeight());
		Assert.assertEquals(monitor.getBrand(), resultMonitor.getBrand());
		Assert.assertEquals(crtMonitor.getRefreshRate(), resultCRTMonitor.getRefreshRate());
		Assert.assertEquals(lcdMonitor.getDpiSupported(), resultLCDMonitor.getDpiSupported());
	}
	
	public void testUpdateObjectWithTwoLevelInheritance(){
		log.debug("\n-------testUpdateObjectWithTwoLevelInheritance()-------");
		Display display=new Display();
		display.setHeight(20);
		display.setWidth(30);
		Monitor monitor=new Monitor();
		monitor.setHeight(20);
		monitor.setWidth(30);
		monitor.setBrand("lcd");
		CRTMonitor crtMonitor=new CRTMonitor();
		crtMonitor.setHeight(20);
		crtMonitor.setWidth(30);
		crtMonitor.setBrand("lcd");
		crtMonitor.setRefreshRate(20);
		LCDMonitor lcdMonitor=new LCDMonitor();
		lcdMonitor.setHeight(20);
		lcdMonitor.setWidth(30);
		lcdMonitor.setBrand("lcd");
		lcdMonitor.setDpiSupported(20);
		
		save(display);
		save(monitor);
		save(crtMonitor);
		save(lcdMonitor);
		
		Display updateDisplay=(Display)getObject(Display.class, display.getId());
		updateDisplay.setHeight(20);
		Monitor updateMonitor=(Monitor)getObject(Monitor.class, monitor.getId());
		updateMonitor.setBrand("lcd");
		CRTMonitor updateCRTMonitor=(CRTMonitor)getObject(CRTMonitor.class, crtMonitor.getId());
		updateCRTMonitor.setRefreshRate(20);
		LCDMonitor updateLCDMonitor=(LCDMonitor)getObject(LCDMonitor.class, lcdMonitor.getId());
		updateLCDMonitor.setDpiSupported(20);
		
		update(updateDisplay);
		update(updateMonitor);
		update(updateCRTMonitor);
		update(updateLCDMonitor);
		
		Display resultDisplay=(Display)getObject(Display.class, display.getId());
		Monitor resultMonitor=(Monitor)getObject(Monitor.class, monitor.getId());
		CRTMonitor resultCRTMonitor=(CRTMonitor)getObject(CRTMonitor.class, crtMonitor.getId());
		LCDMonitor resultLCDMonitor=(LCDMonitor)getObject(LCDMonitor.class, lcdMonitor.getId());
		Assert.assertEquals(updateDisplay.getHeight(), resultDisplay.getHeight());
		Assert.assertEquals(updateMonitor.getBrand(), resultMonitor.getBrand());
		Assert.assertEquals(updateCRTMonitor.getRefreshRate(), resultCRTMonitor.getRefreshRate());
		Assert.assertEquals(updateLCDMonitor.getDpiSupported(), resultLCDMonitor.getDpiSupported());
	}
	
	public void testDeleteObjectWithTwoLevelInheritance(){
		log.debug("\n-------testDeleteObjectWithTwoLevelInheritance()-------");
		Display display=new Display();
		display.setHeight(20);
		display.setWidth(30);
		Monitor monitor=new Monitor();
		monitor.setHeight(20);
		monitor.setWidth(30);
		monitor.setBrand("lcd");
		CRTMonitor crtMonitor=new CRTMonitor();
		crtMonitor.setHeight(20);
		crtMonitor.setWidth(30);
		crtMonitor.setBrand("lcd");
		crtMonitor.setRefreshRate(20);
		LCDMonitor lcdMonitor=new LCDMonitor();
		lcdMonitor.setHeight(20);
		lcdMonitor.setWidth(30);
		lcdMonitor.setBrand("lcd");
		lcdMonitor.setDpiSupported(20);
		
		save(display);
		save(monitor);
		save(crtMonitor);
		save(lcdMonitor);
		
		Display deleteDisplay=(Display)getObject(Display.class, display.getId());
		Monitor deleteMonitor=(Monitor)getObject(Monitor.class, monitor.getId());
		CRTMonitor deleteCRTMonitor=(CRTMonitor)getObject(CRTMonitor.class, crtMonitor.getId());
		LCDMonitor deleteLCDMonitor=(LCDMonitor)getObject(LCDMonitor.class, lcdMonitor.getId());
	
		delete(deleteDisplay);
		delete(deleteMonitor);
		delete(deleteCRTMonitor);
		delete(deleteLCDMonitor);
		
		Display resultDisplay=(Display)getObject(Display.class, display.getId());
		Monitor resultMonitor=(Monitor)getObject(Monitor.class, monitor.getId());
		CRTMonitor resultCRTMonitor=(CRTMonitor)getObject(CRTMonitor.class, crtMonitor.getId());
		LCDMonitor resultLCDMonitor=(LCDMonitor)getObject(LCDMonitor.class, lcdMonitor.getId());
		
		Assert.assertNull(resultDisplay);
		Assert.assertNull(resultMonitor);
		Assert.assertNull(resultCRTMonitor);
		Assert.assertNull(resultLCDMonitor);
	}
}
