/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test;

public class TestSuite {

	public final static String TEST_SUITE_TAG = "testsuite";
	public final static String TEST_SUITE_NAME = "name";
	public final static String TEST_SUITE_ERRORS = "errors";
	public final static String TEST_SUITE_FAILURES = "failures";
	public final static String TEST_SUITE_TIME = "time";
	public final static String TEST_SUITE_TIME_STAMP = "timestamp";
	public final static String TEST_SUITE_HOST_NAME = "hostname";
	public final static String TEST_SUITE_TESTS = "tests";
	public final static String TEST_CASE_TAG = "testcase";
	public final static String TEST_CASE_NAME = "name";
	public final static String TEST_CASE_CLASS_NAME = "classname";

	private String errors = "0";
	private String failures = "0";
	private String hostName = "";
	private String name = "";
	private String tests = "0";
	private String time = "0";
	private String timeStamp = "0";

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = ""+(Integer.parseInt(this.errors)+Integer.parseInt(errors));
	}

	public String getFailures() {
		return failures;
	}

	public void setFailures(String failures) {
		this.failures = ""+(Integer.parseInt(this.failures)+Integer.parseInt(failures));
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = ""+(Integer.parseInt(this.tests)+Integer.parseInt(tests));
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = ""+(Float.parseFloat(this.time)+Float.parseFloat(time));
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return
		"<testsuite errors=\"" + errors + "\" failures=\"" + failures
				+ "\" hostname=\"" + hostName + "\" name=\"" + name
				+ "\" tests=\"" + tests + "\" time=\"" + time
				+ "\" timestamp=\"" + timeStamp + "\">";
	}
}
