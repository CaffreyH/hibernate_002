import java.util.Date;

import java.sql.Blob;

//学生类，持久化类
//Java bean的设计原则
//1.共有的类
//2.提供共有的不带参数的构造方法
//3.属性私有
//4/属性setter/getter封装
public class students {
	private int sid;
	private String sname;
	private Date birthday;
	private String address;
	private Blob picture;
	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public students() {

	}

	public students(int sid, String sname, Date birthday, String address) {
		// super();
		this.sid = sid;
		this.sname = sname;
		this.birthday = birthday;
		this.address = address;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "students [sid=" + sid + ", sname=" + sname + ", birthday=" + birthday + ", address=" + address + "]";
	}

}
