import java.util.Date;

import java.sql.Blob;

//ѧ���࣬�־û���
//Java bean�����ԭ��
//1.���е���
//2.�ṩ���еĲ��������Ĺ��췽��
//3.����˽��
//4/����setter/getter��װ
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
