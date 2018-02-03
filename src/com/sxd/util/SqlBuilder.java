package com.sxd.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class SqlBuilder {
	
	 public static enum FieldType { SELECT_FIELD, WHERE_FIELD }

//字符串拼接

	/**
	 * @param port:MySql端口号
	 * @param database:目标数据名
	 * @return 链接数据库字符串
	 */
	public static String getConnectionString(String port, String database) {
		return StringUtil.Format("jdbc:mysql://localhost:{0}/{1}", port, database);
	}
	
	/**
	 * @param split 分隔串
	 * @param strings 待拼接数组
	 * @return "one, two, three"
	 */
	public static String getFieldString(String split, String... strings) {
		StringBuilder str = new StringBuilder("");
		int argsNum = strings.length;
		for(int i = 0; i < argsNum; i++) {
			if(i != argsNum -1 ) {
				str.append(strings[i]);
				str.append(split);
			} else {
				str.append(strings[i]);
			}
		}
		return str.toString();
	}
	
	/**
	 * @param string 需要拼接的字符串
	 * @param split  分隔符
	 * @param number 拼接个数
	 * @return 根据拼接个数拼接指定字符串
	 * 
	 *  getFieldString("?", ",", 5)
	 *  ret: ?,?,?,?,?
	 */
	public static String getFieldString(String string, String split, int number) {
		StringBuilder str = new StringBuilder("");
		for(int i = 0; i < number; i++) {
			if(i != number -1 ) {
				str.append(string);
				str.append(split);
			} else {
				str.append(string);
			}
		}
		return str.toString();
	}
	
	/**
	 * @param objs:万用拼接对象
	 * @return 根据拼接的不同对象，拼接字符串
	 * 
	 * Ex:  getFieldString(1, 1.2, "Test");
	 * ret: "1, 1.2, 'Test'"
	 */
	public static String getFieldString(Object... objs) {
		int argsNum = objs.length;
		String str = "";
		for(int i = 0; i < argsNum; i++) {
			if(objs[i] instanceof Integer) {
				str += objs[i].toString();
			} else if(objs[i] instanceof Float) {
				str += objs[i].toString();
			} else if(objs[i] instanceof Double) {
				str += objs[i].toString();
			} else if(objs[i] instanceof String) {
				str += StringUtil.getSingleQuoteMark(objs[i].toString());
			}
			if(i != argsNum - 1) {
				str += ", ";
			}
		}
		return str;
	}
	
	
	
//预编译条件拼接  set ， where ， select
	
	//field1 = ? and field2 =? and field3 = ?
	public static String getPreFieldString(String split, String... fields) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < fields.length; i++) {
			builder.append(fields[i]);
			builder.append(" = ? ");
			if(i != fields.length - 1) {
				builder.append(split + " ");
			}
		}
		return builder.toString();
	}
	
	
	//set field1 = ?, field2 = ?
	public static String getPreSetString(String... fields) {
		return " set " + getPreFieldString(",", fields);
	}
	
	//where field1 = ? and field2 = ?
	public static String getPreWhereString(String... fields) {
		return " where " + getPreFieldString("and", fields); 
	}
	
	//field1, field2
	public static String getPreQueryString(String... fields) {
		return getFieldString(",", fields);
	}
	
	// key1 = value1, key2 = value2
	public static String getMapKeyValeString(Map<String, Object> map) {
		return getMapKeyValeString(map, ",");
	}
	
	public static String getMapKeyValeString(Map<String, Object> map, String split) {
		StringBuilder builder = new StringBuilder();
		int num = map.size();
		int index = 1;
		Set<Entry<String, Object>> sets = map.entrySet();
		for(Entry<String, Object> set : sets) {
			builder.append(set.getKey());
			builder.append(" = ");
			if(set.getValue() instanceof String) {
				builder.append(StringUtil.getSingleQuoteMark(set.getValue().toString()));
			} else {
				builder.append(set.getValue().toString());
			}
			if(index != num) {
				builder.append(split);
			}
		}
		return builder.toString();
	}
//预编译SQL语句拼接
		
	//INSERT  获取预编译插入sql语句  insert into tableName values(?, ?, ?)
	public static String getPreInsertString(String tableName, int fieldsNum) {
		String valueString = StringUtil.getParenthesis(getFieldString("?", ", ", fieldsNum));
		return "insert into " + tableName  + " values " + valueString;
	}
	
	//insert into tableName(field1, field2, field3) values(?, ?, ?)
	public static String getPreInsertString(String tableName, String[] insertFields) {
		String insertFieldsString =  StringUtil.getParenthesis(getFieldString(",", insertFields));
		String valueString = StringUtil.getParenthesis(getFieldString("?", ", ", insertFields.length));
		return "insert into " + tableName + insertFieldsString + " values " + valueString; 
	}
	

	//SELECT  获得预编译查询sql语句 select field1, field2 from table where field1 = ? and field2 = ?
	public static String getPreSelectString(String tableName, String[] selectFields, String[] whereFields) {
		//selectField 和 whereField 判断选择相应函数处理
		if(StringUtil.isNull(selectFields)) {
			if(StringUtil.isNull(whereFields)) {
				return getPreSelectString(tableName);
			} else {
				return getPreSelectString(tableName, whereFields, FieldType.WHERE_FIELD);
			}
		} else {
			if(StringUtil.isNull(whereFields)) {
				return getPreSelectString(tableName, selectFields, FieldType.SELECT_FIELD);
			}
		}
		//均不为空
		String selectFieldsString = getPreQueryString(selectFields);
		String whereString = getPreWhereString(whereFields);
		return "select " + selectFieldsString + " from " + tableName + " " + whereString;
	}
	
	//select * from tableName
	public static String getPreSelectString(String tableName) {
		return "select * from " + tableName;
	}
	
	//参数Field字段类型判断
	public static String getPreSelectString(String tableName, String[] Fields, FieldType type) {
		if(type == FieldType.WHERE_FIELD) {
			return getPreSelectString(tableName, Fields, true);
		} else if(type == FieldType.SELECT_FIELD) {
			return getPreSelectString(tableName, Fields);
		}
		return "";
	}
	
	//select field1, field2 from tableName
	public static String getPreSelectString(String tableName, String[] selectFields) {
		return "select " +  getPreQueryString(selectFields) + " from " + tableName;
	}
	
	//select * from tableName where field1 = ? and field2 = ?
	public static String getPreSelectString(String tableName, String[] whereFields, boolean hasCondition) {
		return "select * from " + tableName + getPreWhereString(whereFields);
	}
	
	
	//获取预编译的更新sql语句  update tableName set field1 = 1 where field2 = 2
	public static String getPreUpdateString(String tableName, String[] updateFields, String[] whereFields) {
		if(StringUtil.isNull(whereFields)) {
			return getPreUpdateString(tableName, updateFields);
		}
		return "update " + tableName + " " + getPreSetString(updateFields) + getPreWhereString(whereFields);
	}
	
	//update tableName set field1 = 1
	public static String getPreUpdateString(String tableName, String[] updateFields) {
		return "update " + tableName + " " + getPreSetString(updateFields);
	}
	

	//获取预编译的删除sql语句  delete from tableName where field1 = 1
	public static String getPreDeleteString(String tableName, String[] whereFields) {
		String whereString = getPreWhereString(whereFields);
		return "delete from " + tableName + " " + whereString;
	}
	
//可执行SQL语句拼接
	//field1, field2
	public static String getQueryString(String... fields) {
		return getFieldString(",", fields);
	}
		
	public static String getSelectString(String tableName) {
		return "select * from " + tableName;
	}
	
	public static String getSelectString(String tableName, String[] selectFields) {
		if(StringUtil.isNull(selectFields)) {
			return getSelectString(tableName);
		}
		return  "select " + getQueryString(selectFields) + " from " + tableName;
	}
	
	public static String getSelectString(String tableName, String[] selectFields, Map<String, Object> whereMap) {
		String selectFieldsString = StringUtil.isNull(selectFields) == true ? "*" : getPreQueryString(selectFields);
		String whereString = StringUtil.isNull(whereMap) == true ? "" : "where " + getMapKeyValeString(whereMap);
		return "select " + selectFieldsString + " from " + tableName + " " + whereString;
	}
	
	public static String getSelectString(String tableName, String[] selectFields, String where) {
		String selectFieldsString = StringUtil.isNull(selectFields) == true ? "*" : getPreQueryString(selectFields);
		String whereString = "";
		if(!StringUtil.isNullOrEmpty(where)) {
			whereString = " where " + where;
		}
		return "select " + selectFieldsString + " from " + tableName + " " + whereString;
	}
}
