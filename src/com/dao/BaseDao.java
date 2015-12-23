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
 * @1.dao����pojo ����ǰ׺Ҫһ��
 * @2.UserDao ����pojo ��User
 * @3.pojo����������Ҫ������ֶ�����һ��
 * @4.�������ļ������ǰ׺
 * @id�����ǵ�һ��
 * @author Administrator
 * 
 */
@SuppressWarnings("all")
public class BaseDao extends JdbcDaoSupport {

	/**
	 * ��ǰ׺
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
	 * �Զ���װ
	 * 
	 * @param rs
	 * @return
	 */
	private Object AutoSetter(Map<String, Object> rs) {
		Object pojo = null;
		Class daoClass = this.getClass();
		// �Ѱ�����dao�滻��pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");
		// ��������Daoȥ��
		pojoName = pojoName.substring(0, pojoName.length() - 3);

		try {
			Class pojoClass = Class.forName(pojoName);
			// ���÷���õ�����
			pojo = pojoClass.newInstance();
			// �õ��������
			Field[] fields = pojoClass.getDeclaredFields();
			// ��������
			for (int i = 0; i < fields.length; i++) {
				// ���������������޸ĳ�set������
				String setMethonName = "set" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1, fields[i].getName().length());
				// System.out.println(setMethonName);
				// �õ�����
				Method setMethod = pojoClass.getMethod(setMethonName, fields[i].getType());
				// �õ��������ֵ
				Object values = rs.get(fields[i].getName());

				if (values != null) {
					// ִ��Setter����
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
	 * ����һ��
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
	 * ���Ҷ��
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
	 * ����
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
	 * �ں�
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
	 * ͨ��ɾ��
	 * 
	 * @param id
	 *            ��ID
	 * @return
	 */
	public int Delete(int id) {
		int reslutInt = 0;

		Class daoClass = this.getClass();
		// �Ѱ�����dao�滻��pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");

		// ��������Daoȥ��
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		// ͨ��pojo·���õ�����
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
	 * ͨ��update
	 * 
	 * @param pojoObject
	 * @return
	 */
	public int Update(Object pojoObject) {
		int resultInt = 0;
		Class daoClass = this.getClass();
		// �Ѱ�����dao�滻��pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");

		// ��������Daoȥ��
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		// ͨ��pojo·���õ�����
		String tableName = tableprefix + pojoName.substring(pojoName.indexOf("pojo") + 5);
		// ��̬ƴ��SQL
		StringBuilder sql = new StringBuilder("update   " + tableName + " set  ");

		try {
			Class pojoClass = Class.forName(pojoName);

			Field[] fis = pojoClass.getDeclaredFields();

			// ƴ�� update t_User set name = ?,sex = ?,age = ?,pw = ?
			for (int i = 1; i < fis.length; i++) {
				sql.append(fis[i].getName() + " = ?");
				// ���һ��������
				if (i != fis.length - 1) {
					sql.append(",");
				}
			}
			// update t_User set name = ?,sex = ?,age = ?,pw = ? where id= ?
			sql.append(" where " + fis[0].getName() + "   =?");

			// ��̬����getter���������Ұ�ֵ�ŵ�params���鵱��
			Object[] params = new Object[fis.length]; // UPDATE����ID���Բ�-1
			for (int i = 1; i < fis.length; i++) {
				// ��̬ƴ��getter����
				String getMethodName = "get" + fis[i].getName().substring(0, 1).toUpperCase() + fis[i].getName().substring(1);
				// ��ȡget����
				Method getMethod = pojoClass.getMethod(getMethodName);
				// ����getter�����ŵ����鵱��
				params[i - 1] = getMethod.invoke(pojoObject);
			}

			// ���id
			// ��̬ƴ��getter����
			String getMethodName = "get" + fis[0].getName().substring(0, 1).toUpperCase() + fis[0].getName().substring(1);
			// ��ȡget����
			Method getMethod = pojoClass.getMethod(getMethodName);
			// ����getter�����ŵ����鵱��
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
	 * ͨ�ò���
	 * 
	 * @param pojoObject
	 * @return
	 */
	public int Insert(Object pojoObject) {
		int reslutInt = 0;

		Class daoClass = this.getClass();
		// �Ѱ�����dao�滻��pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");

		// ��������Daoȥ��
		pojoName = pojoName.substring(0, pojoName.length() - 3);
		// ͨ��pojo·���õ�����
		String tableName = tableprefix + pojoName.substring(pojoName.indexOf("pojo") + 5);
		// ��̬ƴ��SQL
		StringBuilder sql = new StringBuilder("insert  into  " + tableName + " ( ");
		try {
			Class pojoClass = Class.forName(pojoName);

			Field[] fis = pojoClass.getDeclaredFields();

			for (int i = 1; i < fis.length; i++) {
				sql.append(fis[i].getName());
				// ���һ��������
				if (i != fis.length - 1) {
					sql.append(",");
				}
			}
			sql.append(")");
			sql.append(" values( ");
			for (int i = 1; i < fis.length; i++) {
				sql.append(" ? ");
				// ���һ��������
				if (i != fis.length - 1) {
					sql.append(",");
				}
			}
			sql.append(")");

			// ��̬����getter���������Ұ�ֵ�ŵ�params���鵱��
			Object[] params = new Object[fis.length - 1];
			for (int i = 1; i < fis.length; i++) {
				// ��̬ƴ��getter����
				String getMethodName = "get" + fis[i].getName().substring(0, 1).toUpperCase() + fis[i].getName().substring(1);
				// ��ȡget����
				Method getMethod = pojoClass.getMethod(getMethodName);
				// ����getter�����ŵ����鵱��
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
	 * ͨ�ò���ID
	 * 
	 * @param id
	 * @return
	 */
	public Object findById(int id) {
		// ȡ���Լ�
		Class daoClass = this.getClass();
		String tableName = daoClass.getName().substring(daoClass.getName().indexOf("dao") + 4);
		tableName = tableprefix + tableName.substring(0, tableName.length() - 3);
		// �Ѱ�����dao�滻��pojo
		String pojoName = daoClass.getName().replace("dao", "pojo");

		// ��������Daoȥ��
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
	 * ��̬select * from t_xxx
	 * 
	 * @return
	 */
	public String getSelectAll() {
		// ȡ���Լ�
		Class daoClz = this.getClass();
		String tableName = daoClz.getName().substring(daoClz.getName().indexOf("dao") + 4);
		tableName = tableprefix + tableName.substring(0, tableName.length() - 3);

		String sql = "select * from " + tableName;
		return sql;
	}

	/**
	 * ����pojoTitle
	 */
	public void autoGenPojoTitle() {
		Object pojo = null;
		// ȡ���Լ�
		Class daoClz = this.getClass();

		String pojoName = daoClz.getName().substring(daoClz.getName().indexOf("dao") + 4);

		// ȥclasspath����������pojo
		StringBuilder toCopy = new StringBuilder("//");
		StringBuilder title = new StringBuilder("import java.io.Serializable;\n\n");
		title.append("public class ");
		title.append(pojoName.substring(0, pojoName.length() - 3)); // ȥ����Dao��
		title.append(" implements Serializable {\n\n");
		String name;
		// д���� ---����sql��ѯ��ȡ�ر���

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
					title.append(" �Լ����� ");
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
