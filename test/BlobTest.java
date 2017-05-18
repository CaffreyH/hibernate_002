import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import java.sql.Blob;
import java.sql.SQLException;

public class BlobTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@Test
	public void TestWriteBlob() throws IOException
	{
		
		Configuration config  = new Configuration().configure();
		//��÷���ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//��ûỰ��������
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//��ûỰ����
		Session session = sessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		students s = new students(5,"��Ƭ��",new Date(),"����");
		//���ͼƬ�ļ�
		File f = new File("C:\\Users\\huyan\\Pictures\\pica.jpg");
		//����ʼ۵�������
		InputStream input = new FileInputStream(f);
		//����Blob����
		Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());//��ȡ�ĳ���
		s.setPicture(image);
		session.save(s);
		transaction.commit();
	}
	@Test
	public void readBlob() throws Exception
	{
		Configuration config  = new Configuration().configure();
		//��÷���ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//��ûỰ��������
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//��ûỰ����
		Session session = sessionFactory.openSession();
		students s = (students) session.get(students.class,4);
		Blob image = s.getPicture();
		
		InputStream input = image.getBinaryStream();
		File f = new File("D://dest.jpg");
		OutputStream output = new FileOutputStream(f);
		//����������
		byte[] buff = new byte[input.available()];
		input.read(buff);
		output.write(buff);
		input.close();
		output.close();
	}
}
