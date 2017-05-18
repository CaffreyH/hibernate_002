import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class SessionTest {
	@Test
	public void testOpenSession()
	{
		//获得配置对象
		Configuration config  = new Configuration().configure();
		//获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//获得会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//获得会话对象
		Session session = sessionFactory.openSession();
		
		
		if(session != null)
		{
			System.out.println("Session创建成功");
		}
		else
		{
			System.out.println("Session创建失败");
		}
		
	}
	@Test
	public void testGetCurrentSession() {
		// 获得配置对象
		Configuration config = new Configuration().configure();
		// 获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 获得会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// 获得会话对象
		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			System.out.println("Session2创建成功");
		} else {
			System.out.println("Session2创建失败");
		}
	}
	@Test
	public void testSaveStudentsWithOpenSession()
	{
		Configuration config  = new Configuration().configure();
		//获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//获得会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//获得会话对象
		Session session1 = sessionFactory.getCurrentSession();
		//开启事务
		Transaction transaction = session1.beginTransaction();
		students s = new students(4,"张三",new Date(),"北京"); 
		session1.doWork(new Work(){

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("connection1 hashcode is :"+connection.hashCode());
			}
		}
		);
		session1.flush();//---------此时需要 flush
		session1.save(s);//保存对象到数据库
		transaction.commit();
		//session1.close(); 不关闭对话
		
		Session session2 = sessionFactory.openSession();
		transaction = session2.beginTransaction();
		s = new students(3,"李四",new Date(),"上海"); 
		session2.doWork(new Work(){

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("connection2 hashcode is :"+connection.hashCode());
			}
		}
		);
		session2.save(s);
		transaction.commit();
	}
	
}
