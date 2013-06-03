/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.core;

import javax.script.CompiledScript;

public interface ScriptingUtil
{
	public void bind(String _name, Object _value);
	public void loadScript(String _scriptResource);
	public Object executeScript(String[] _scriptResources);
	public Object executeScript(String _scriptResource);
}