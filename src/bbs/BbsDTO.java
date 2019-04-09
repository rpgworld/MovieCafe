package bbs;

import java.sql.Date;
import java.sql.Time;

public class BbsDTO {
	private int num;
	private String name;
	private String subject;
	private String content;
	private Date write_date;
	private Time write_time;
	private int ref;		// �亯 ���� ���� ���� �� ��ȣ
	private int step;		// �亯 �� ��� ����
	private int lev;		// �亯�� �鿩���� ����
	private int read_cnt; 	// ��ȸ��
	private int child_cnt;	// ���� �亯 �� ��
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public Time getWrite_time() {
		return write_time;
	}
	public void setWrite_time(Time write_time) {
		this.write_time = write_time;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}
	public int getChild_cnt() {
		return child_cnt;
	}
	public void setChild_cnt(int child_cnt) {
		this.child_cnt = child_cnt;
	}
	
	
	
}
