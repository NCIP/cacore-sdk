package test.writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;


public class SDKWritableCleanUpTest extends AbstractDependencyInjectionSpringContextTests {

	private JdbcTemplate jdbcTemplate;
	private static Logger log = Logger.getLogger(SDKWritableCleanUpTest.class);
	
	protected String[] getConfigLocations() {
		return new String[] { "classpath*:application-config-cleanup-writableapi-test.xml" };
	}

	final String ORACLE_ALL_TABLES_SQL = "select TABLE_NAME from ALL_TABLES where tablespace_name like 'CACORESDK_ANT'";
	final String ORACLE_FOREIGN_TABLES_SQL = "select table_name from all_constraints where constraint_type='R' and r_constraint_name in (select constraint_name from all_constraints where constraint_type in ('P','U') and OWNER='CACORESDK_ANT')";
	final String PK_KEY_TABLE_SQL = "SELECT cols.column_name FROM all_constraints cons, all_cons_columns cols WHERE cols.table_name = ? AND cons.constraint_type = 'P' AND cons.constraint_name = cols.constraint_name AND cons.owner = cols.owner";

	final String MYSQL_ALL_TABLES_SQL = "SHOW TABLES FROM cacoresdk";

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void testCleanUpWritableApiDatabase() throws Exception{
		
		String driverName=jdbcTemplate.getDataSource().getConnection().getMetaData().getDriverName();
		int index = driverName.indexOf("MySQL");
		if (index == 0) {
			cleanMySQLWritableApiDatabase();
		}
		index = driverName.indexOf("Oracle");
		if (index == 0) {
			cleanOracleWritableApiDatabase();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void cleanOracleWritableApiDatabase() {
		List<Map<String, Object>> fktableNames = jdbcTemplate.queryForList(ORACLE_FOREIGN_TABLES_SQL);
		for (Map<String, Object> tableNameMap : fktableNames) {
			String tableName = (String)tableNameMap.get("TABLE_NAME");
			log.info(tableName);
			Object args[] = new Object[1];
			args[0] = tableName;
			List pkColumnNames = jdbcTemplate.queryForList(PK_KEY_TABLE_SQL,args);
			if (pkColumnNames != null && pkColumnNames.size() > 0) {
				Map<String, String> pkColumnNameMap = (Map) pkColumnNames.get(0);
				String pkColumnName = pkColumnNameMap.get("column_name");
				try {
					jdbcTemplate.execute("DELETE from " + tableName + " where "+ pkColumnName + " > 10000");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		log.info("non foreign key dependent tables ");
		List<Map<String, Object>> tableNames = jdbcTemplate.queryForList(ORACLE_ALL_TABLES_SQL);
		for (Map<String, Object> tableNameMap : tableNames) {
			String tableName = (String)tableNameMap.get("TABLE_NAME");
			log.info(tableName);
			Object args[] = new Object[1];
			args[0] = tableName;
			List pkColumnNames = jdbcTemplate.queryForList(PK_KEY_TABLE_SQL,args);
			if (pkColumnNames != null && pkColumnNames.size() > 0) {
				Map<String, String> pkColumnNameMap = (Map) pkColumnNames.get(0);
				String pkColumnName = pkColumnNameMap.get("column_name");
				try {
					jdbcTemplate.execute("DELETE from " + tableName + " where "+ pkColumnName + " > 10000");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void cleanMySQLWritableApiDatabase() {

		List<Map<String, Object>> tableNames = jdbcTemplate.queryForList(MYSQL_ALL_TABLES_SQL);

		List<Map<String, Object>> nonFkTableMap = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> tableNameMap : tableNames) {
			String tableName = (String)tableNameMap.get("TABLE_NAME");
			if(tableName==null) return;
			List<Map<String, Object>> indexList = jdbcTemplate.queryForList("SHOW index FROM " + tableName);
			if (indexList.size() > 1) {
				log.info(tableName);
				try {
					for (Map<String, Object> pkColumnNameMap : indexList) {
						if(pkColumnNameMap.get("INDEX_NAME").equals("PRIMARY")){
							String pkColumnName = (String)pkColumnNameMap.get("Column_name");
							jdbcTemplate.execute("DELETE from " + tableName + " where "+ pkColumnName + " > 10000");
						}
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				if(!indexList.isEmpty()) nonFkTableMap.add(tableNameMap);
			}
		}

		log.info("\n non foreign key dependent tables \n");
		for (Map<String, Object> tableNameMap : nonFkTableMap) {
			String tableName = (String)tableNameMap.get("TABLE_NAME");
			log.info(tableName);
			List<Map<String, Object>> indexList = jdbcTemplate.queryForList("SHOW index FROM " + tableName);
			try {
				Map<String, Object> pkColumnNameMap = indexList.get(0);
				String pkColumnName = (String)pkColumnNameMap.get("Column_name");
				jdbcTemplate.execute("DELETE from " + tableName + " where "+ pkColumnName + " > 10000");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
