package gov.nih.nci.sdk.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

/**
 * ModelConverterUtilTest
 * 
 * @author John Chen
 *
 */
public class SDKUtilTest {
	@Test
	public void loadTags() {
		Set<String> tagNames = SDKUtil.getAllSDKTagNames();
		assertEquals("All tags loaded", 56, tagNames.size());
	}
	
	@Test
	public void isSDKTag() {
		assertFalse(SDKUtil.isSDKTag("this.is.not.sdk.tag"));
		
		assertTrue(SDKUtil.isSDKTag("package.prop.sec.encrypt"));
		assertTrue(SDKUtil.isSDKTag("package.oper.sec.role"));
		assertTrue(SDKUtil.isSDKTag("oper.sec.role"));
		assertTrue(SDKUtil.isSDKTag("oper.mea.desc"));
		assertTrue(SDKUtil.isSDKTag("rel.mea.desc"));
		assertTrue(SDKUtil.isSDKTag("class.per.table.name"));
		assertTrue(SDKUtil.isSDKTag("prop.per.primary.key"));
		assertTrue(SDKUtil.isSDKTag("prop.val.required"));
		assertTrue(SDKUtil.isSDKTag("prop.val.max.length"));
		assertTrue(SDKUtil.isSDKTag("prop.val.min.length"));
	}
}
