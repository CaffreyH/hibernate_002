//测试类
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class StudentsTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@Before
	public void init()
	{
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建会话
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction();
	}
	@After
	public void destory()
	{
		System.out.println("destoy");
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("销毁");
	}
	@Test
	public void testSaveStudents()
	{
		//生成学生对象
		students s = new students(2,"兔兔",new Date(),"订单");
		System.out.println("建表");
		session.save(s);//保存对象到数据库
		System.out.println("完成");
	}
	@Test
	public void readBlob() throws Exception
	{
		students s = (students) session.get(students.class,4);
		Blob image = s.getPicture();
		InputStream input = image.getBinaryStream();
		File f = new File("D://dest.jpg");
		OutputStream output = new FileOutputStream(f);
		//创建缓冲区
		byte[] buff = new byte[input.available()];
		output.write(buff);
		input.close();
		output.close();
	}
}
