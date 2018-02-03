package com.sxd.util;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ResultSetUtil {
	
	//获取行数
	public static int getRowCount(ResultSet rs) {
		int rowNum = 0;
		try {
			//int oldRowIndex = rs.getRow();
			rs.last();
			rowNum = rs.getRow();
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowNum;
	}
	
	//获取列数
	public static int getColumnCount(ResultSet rs) {
		if(rs == null) {
			return 0;
		}
		int columnNum = 0;
		try {
			columnNum = rs.getMetaData().getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnNum;
	}
	
	public static String getColumnName(ResultSet rs, int index) throws SQLException {
		if(index < 1 || index > getColumnCount(rs)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return rs.getMetaData().getColumnName(index);
	}
	
	public static <T> List<T> ConvertToBeanList(ResultSet rs, Class<?> clazz) throws SQLException {
		List<T> results = new ArrayList<T>();
        if (!rs.next()) {
            return results;
        }
        do {
        	T bean = createBean(clazz);
        	for(int i = 1; i <= getColumnCount(rs); i++) {
        		String fieldName = getColumnName(rs, i);
        		JavaBeanUtil.setProperty(bean, fieldName, rs.getObject(i));
        	}
        	results.add(bean);
        	
           // results.add(createBean(rs, type, 0);
        } while (rs.next());
        return results;
	}
	
	private static <T> T createBean(Class<?> clazz) {
		try {
			Class.forName(clazz.getName());
			return  (T) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
