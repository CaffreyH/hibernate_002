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
		//������ö���
		Configuration config  = new Configuration().configure();
		//��÷���ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//��ûỰ��������
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//��ûỰ����
		Session session = sessionFactory.openSession();
		
		
		if(session != null)
		{
			System.out.println("Session�����ɹ�");
		}
		else
		{
			System.out.println("Session����ʧ��");
		}
		
	}
	@Test
	public void testGetCurrentSession() {
		// ������ö���
		Configuration config = new Configuration().configure();
		// ��÷���ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// ��ûỰ��������
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// ��ûỰ����
		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			System.out.println("Session2�����ɹ�");
		} else {
			System.out.println("Session2����ʧ��");
		}
	}
	@Test
	public void testSaveStudentsWithOpenSession()
	{
		Configuration config  = new Configuration().configure();
		//��÷���ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//��ûỰ��������
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//��ûỰ����
		Session session1 = sessionFactory.getCurrentSession();
		//��������
		Transaction transaction = session1.beginTransaction();
		students s = new students(4,"����",new Date(),"����"); 
		session1.doWork(new Work(){

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("connection1 hashcode is :"+connection.hashCode());
			}
		}
		);
		session1.flush();//---------��ʱ��Ҫ flush
		session1.save(s);//����������ݿ�
		transaction.commit();
		//session1.close(); ���رնԻ�
		
		Session session2 = sessionFactory.openSession();
		transaction = session2.beginTransaction();
		s = new students(3,"����",new Date(),"�Ϻ�"); 
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
