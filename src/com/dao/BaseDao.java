package com.dao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.sql.DataSource;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 * @1.dao包和pojo 包的前缀要一致
 * @2.UserDao 它的pojo 是User
 * @3.pojo的属性命名要跟表的字段命名一致
 * @4.在配置文件中配表前缀
 * @id属性是第一个
 * @author Administrator
 * 
 */
@SuppressWarnings("all")
public class BaseDao extends JdbcDaoSupport {

	/**
	 * 表前缀
	 */
	private String tableprefix;

	public BaseDao() {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("DBconfig.properties"));

			tableprefix = properties.getProperty("tableprefix");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 自动封装
	 * 
	 * @param rs
	 * @return
	 */
	private Object AutoSetter(Map<String, Object> rs) {
		Object pojo = null;
		Class daoClass = this.getClass();
		// 把包名的dao替换成pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");
		// 把类名的Dao去掉
		pojoName = pojoName.substring(0, pojoName.length() - 3);

		try {
			Class pojoClass = Class.forName(pojoName);
			// 利用反射得到对象
			pojo = pojoClass.newInstance();
			// 得到类的属性
			Field[] fields = pojoClass.getDeclaredFields();
			// 迭代属性
			for (int i = 0; i < fields.length; i++) {
				// 根据类属性名字修改成set方法名
				String setMethonName = "set" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1, fields[i].getName().length());
				// System.out.println(setMethonName);
				// 得到方法
				Method setMethod = pojoClass.getMethod(setMethonName, fields[i].getType());
				// 得到结果集的值
				Object values = rs.get(fields[i].getName());

				if (values != null) {
					// 执行Setter方法
					setMethod.invoke(pojo, values);
				}

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pojo;

	}

	/**
	 * 查找一个
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	protected Object findOne(String sql, Object... objects) {

		List<Map<String, Object>> rlist = query(sql, objects);

		if (rlist != null && rlist.size() > 0) {
			return AutoSetter(rlist.get(0));
		}

		return null;

	}

	/**
	 * 查找多个
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	protected List findSome(String sql, Object... objects) {
		List list = null;
		List<Map<String, Object>> rlist = query(sql, objects);

		if (rlist != null && rlist.size() > 0) {
			list = new ArrayList();

			for (Map<String, Object> map : rlist) {
				list.add(AutoSetter(map));
			}
		}

		return list;

	}

	/**
	 * 查找
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	protected List<Map<String, Object>> query(String sql, Object... objects) {

		List<Map<String, Object>> rlist = null;
		if (objects != null) {
			rlist = super.getJdbcTemplate().queryForList(sql, objects);
		} else {
			rlist = super.getJdbcTemplate().queryForList(sql);
		}

		return rlist;
	}

	/**
	 * 融合
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	protected int merge(String sql, Object... objects) {
		int relustInt = 0;
		if (objects != null) {
			relustInt = super.getJdbcTemplate().update(sql, objects);
		} else {
			relustInt = super.getJdbcTemplate().update(sql);
		}

		return relustInt;
	}

	/**
	 * 通用删除
	 * 
	 * @param id
	 *            表ID
	 * @return
	 */
	public int Delete(int id) {
		int reslutInt = 0;

		Class daoClass = this.getClass();
		// 把包名的dao替换成pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");

		// 把类名的Dao去掉
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		// 通过pojo路径拿到表明
		String tableName = tableprefix + pojoName.substring(pojoName.indexOf("pojo") + 5);
		StringBuilder sql = new StringBuilder("delete  from  " + tableName + " where  ");
		try {
			Class pojoClass = Class.forName(pojoName);
			Field[] fis = pojoClass.getDeclaredFields();
			sql.append(fis[0].getName());
			sql.append(" =? ");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		reslutInt = merge(sql.toString(), id);
		return reslutInt;
	}

	/**
	 * 通用update
	 * 
	 * @param pojoObject
	 * @return
	 */
	public int Update(Object pojoObject) {
		int resultInt = 0;
		Class daoClass = this.getClass();
		// 把包名的dao替换成pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");

		// 把类名的Dao去掉
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		// 通过pojo路径拿到表明
		String tableName = tableprefix + pojoName.substring(pojoName.indexOf("pojo") + 5);
		// 动态拼接SQL
		StringBuilder sql = new StringBuilder("update   " + tableName + " set  ");

		try {
			Class pojoClass = Class.forName(pojoName);

			Field[] fis = pojoClass.getDeclaredFields();

			// 拼接 update t_User set name = ?,sex = ?,age = ?,pw = ?
			for (int i = 1; i < fis.length; i++) {
				sql.append(fis[i].getName() + " = ?");
				// 最后一个，不加
				if (i != fis.length - 1) {
					sql.append(",");
				}
			}
			// update t_User set name = ?,sex = ?,age = ?,pw = ? where id= ?
			sql.append(" where " + fis[0].getName() + "   =?");

			// 动态调用getter方法，并且把值放到params数组当中
			Object[] params = new Object[fis.length]; // UPDATE语句带ID所以不-1
			for (int i = 1; i < fis.length; i++) {
				// 动态拼接getter方法
				String getMethodName = "get" + fis[i].getName().substring(0, 1).toUpperCase() + fis[i].getName().substring(1);
				// 获取get方法
				Method getMethod = pojoClass.getMethod(getMethodName);
				// 调用getter方法放到数组当中
				params[i - 1] = getMethod.invoke(pojoObject);
			}

			// 填充id
			// 动态拼接getter方法
			String getMethodName = "get" + fis[0].getName().substring(0, 1).toUpperCase() + fis[0].getName().substring(1);
			// 获取get方法
			Method getMethod = pojoClass.getMethod(getMethodName);
			// 调用getter方法放到数组当中
			params[fis.length - 1] = getMethod.invoke(pojoObject);

			resultInt = merge(sql.toString(), params);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultInt;

	}

	/**
	 * 通用插入
	 * 
	 * @param pojoObject
	 * @return
	 */
	public int Insert(Object pojoObject) {
		int reslutInt = 0;

		Class daoClass = this.getClass();
		// 把包名的dao替换成pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");

		// 把类名的Dao去掉
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		// 通过pojo路径拿到表明
		String tableName = tableprefix + pojoName.substring(pojoName.indexOf("pojo") + 5);
		// 动态拼接SQL
		StringBuilder sql = new StringBuilder("insert  into  " + tableName + " ( ");
		try {
			Class pojoClass = Class.forName(pojoName);

			Field[] fis = pojoClass.getDeclaredFields();

			for (int i = 1; i < fis.length; i++) {
				sql.append(fis[i].getName());
				// 最后一个，不加
				if (i != fis.length - 1) {
					sql.append(",");
				}
			}
			sql.append(")");
			sql.append(" values( ");
			for (int i = 1; i < fis.length; i++) {
				sql.append(" ? ");
				// 最后一个，不加
				if (i != fis.length - 1) {
					sql.append(",");
				}
			}
			sql.append(")");

			// 动态调用getter方法，并且把值放到params数组当中
			Object[] params = new Object[fis.length - 1];
			for (int i = 1; i < fis.length; i++) {
				// 动态拼接getter方法
				String getMethodName = "get" + fis[i].getName().substring(0, 1).toUpperCase() + fis[i].getName().substring(1);
				// 获取get方法
				Method getMethod = pojoClass.getMethod(getMethodName);
				// 调用getter方法放到数组当中
				params[i - 1] = getMethod.invoke(pojoObject);
			}

			reslutInt = this.merge(sql.toString(), params);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reslutInt;
	}

	/**
	 * 通用查找ID
	 * 
	 * @param id
	 * @return
	 */
	public Object findById(int id) {
		// 取得自己
		Class daoClass = this.getClass();
		String tableName = daoClass.getName().substring(daoClass.getName().indexOf("dao") + 4);
		tableName = tableprefix + tableName.substring(0, tableName.length() - 3);
		// 把包名的dao替换成pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");

		// 把类名的Dao去掉
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		String sql = "select * from  " + tableName + " where   ";
		try {
			Class pojoClass = Class.forName(pojoName);
			Field[] fis = pojoClass.getDeclaredFields();
			sql = sql + fis[0].getName();
			sql = sql + " =? ";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.findOne(sql, id);
	}

	public List findAll() {

		return this.findSome(getSelectAll());
	}

	/**
	 * 动态select * from t_xxx
	 * 
	 * @return
	 */
	public String getSelectAll() {
		// 取得自己
		Class daoClz = this.getClass();
		String tableName = daoClz.getName().substring(daoClz.getName().indexOf("dao") + 4);
		tableName = tableprefix + tableName.substring(0, tableName.length() - 3);

		String sql = "select * from " + tableName;
		return sql;
	}

	/**
	 * 生成pojoTitle
	 */
	public void autoGenPojoTitle() {
		Object pojo = null;
		// 取得自己
		Class daoClz = this.getClass();

		String pojoName = daoClz.getName().substring(daoClz.getName().indexOf("dao") + 4);

		// 去classpath搜索并加载pojo
		StringBuilder toCopy = new StringBuilder("//");
		StringBuilder title = new StringBuilder("import java.io.Serializable;\n\n");
		title.append("public class ");
		title.append(pojoName.substring(0, pojoName.length() - 3)); // 去除“Dao”
		title.append(" implements Serializable {\n\n");
		String name;
		// 写属性 ---发起sql查询：取回表定义

		List<Map<String, Object>> rlist = super.getJdbcTemplate().queryForList("desc " + tableprefix + pojoName.substring(0, pojoName.length() - 3));
		String tpyeName = "";
		if (rlist != null && rlist.size() > 0) {
			for (Map<String, Object> map : rlist) {

				title.append("    private ");
				tpyeName = map.get("type").toString().substring(0, 3);

				if ("var".equals(tpyeName)) {
					title.append("  String ");
				} else if ("int".equals(tpyeName)) {
					title.append("  Integer ");
				} else if ("dat".equals(tpyeName)) {
					title.append("  Date ");
				} else if ("dou".equals(tpyeName)) {
					title.append("  Double ");

				} else {
					title.append(" 自己定夺 ");
				}

				title.append("  ");
				name = map.get("field").toString();
				title.append(name);
				toCopy.append(name);
				toCopy.append(",");

				title.append(" ;\n");

			}
		}

		System.out.println(title.toString());
		System.out.println(toCopy.toString());

	}
}
