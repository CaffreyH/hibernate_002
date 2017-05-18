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
		//获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//获得会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//获得会话对象
		Session session = sessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		students s = new students(5,"照片哥",new Date(),"北京");
		//获得图片文件
		File f = new File("C:\\Users\\huyan\\Pictures\\pica.jpg");
		//获得问价的输入流
		InputStream input = new FileInputStream(f);
		//创建Blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());//读取的长度
		s.setPicture(image);
		session.save(s);
		transaction.commit();
	}
	@Test
	public void readBlob() throws Exception
	{
		Configuration config  = new Configuration().configure();
		//获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//获得会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//获得会话对象
		Session session = sessionFactory.openSession();
		students s = (students) session.get(students.class,4);
		Blob image = s.getPicture();
		
		InputStream input = image.getBinaryStream();
		File f = new File("D://dest.jpg");
		OutputStream output = new FileOutputStream(f);
		//创建缓冲区
		byte[] buff = new byte[input.available()];
		input.read(buff);
		output.write(buff);
		input.close();
		output.close();
	}
}
