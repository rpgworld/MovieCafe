package bbs;

import java.sql.Date;
import java.sql.Time;

public class BbsDTO {
	private int num;
	private String name;
	private String subject;
	private String content;
	private Date writeDate;
	private Time writeTime;
	private int ref;		// �亯 ���� ���� ���� �� ��ȣ
	private int step;		// �亯 �� ��� ����
	private int lev;		// �亯�� �鿩���� ����
	private int readCnt; 	// ��ȸ��
	private int childCnt;	// ���� �亯 �� ��
	
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
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public Time getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Time writeTime) {
		this.writeTime = writeTime;
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
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public int getChildCnt() {
		return childCnt;
	}
	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}
	
	
	
}
