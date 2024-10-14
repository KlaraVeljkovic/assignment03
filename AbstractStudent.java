package assignment03;

import java.time.LocalTime;
import java.util.List;

abstract public class AbstractStudent {
	private String major;
	private Person innerSelf;
	public AbstractStudent (Person p) {
		innerSelf = p;
	}
	public Person getInnerSelf() {
		return innerSelf;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	abstract public String getCourses();
	abstract public List<String> getCourseList();
	abstract public boolean timeConflict(LocalTime time);
	abstract public String getUniversity();
	abstract public void setUniversity(String university);
}
