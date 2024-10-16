package assignment03;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CourseDecorator extends AbstractStudent {
	protected AbstractStudent delegate;	
	
	private String courseRubric; // such as "CS", "MATH", "CHEM", "ISE"
	private String courseNumber; // such as "350", "520", "480A", "580T"
	private String courseTitle;  // such as "Design Patterns"
	private int credits;		// such as 3, 4
	private String section;		// such as "01", "90", "A52"
	private LocalTime startTime;          
	private LocalTime endTime;          
	public CourseDecorator(AbstractStudent s, String courseRubricIn, 
			String courseNumberIn, String courseTitleIn, int creditsIn,
			String sectionIn, LocalTime startTimeIn, LocalTime endTimeIn) {
		super(s.getInnerSelf());
		delegate = s;
		courseRubric = courseRubricIn;
		courseNumber = courseNumberIn;
		courseTitle = courseTitleIn;
		credits = creditsIn;
		section = sectionIn;
		startTime = startTimeIn;
		endTime = endTimeIn;
	}
	public Person getInnerSelf() {
		return delegate.getInnerSelf();
	}
	public String getCourses() 
	{
		// TODO
		// MODIFY this code so it returns a list (as a new-line-separated string)
		// of all the courses from all the CourseDecorators 
		// attached to the test object. The code combines the
		// delegate.getCourses() and this.toString().
		String delegateCourses = delegate.getCourses();
		if (!delegateCourses.isEmpty()) 
		{
			delegateCourses += "\n";
		}
		return delegateCourses + this.toString() + "\n";
	}
	public List<String> getCourseList() {
		// TODO
		// Write this code to add "this.toString()" to the List
		// DO NOT make a List here, rely on the delegate to return
		// its list and add to that.
		List<String> courseList = delegate.getCourseList();
		courseList.add(this.toString());
		return courseList;
	}
	public boolean betweenStartAndEnd(LocalTime time) {
		// TODO
		// This method is only in CourseDecorator. It is not in 
		// the AbstractMethod interface.

		// Return true of time equals startTime.minusMinutes(10)
		// or time is after startTime.minusMinutes(10)
		// and before endTime.plusMinutes(10), 
		// using the methods time.isAfter and time.isBefore 
		// from the class LocalTime
		// see https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/LocalTime.html
		LocalTime startBuffer = startTime.minusMinutes(10); // Start time minus 10 minutes
		LocalTime endBuffer = endTime.plusMinutes(10);      // End time plus 10 minutes

		// Check if the time is within the range [startBuffer, endBuffer]
		return !time.isBefore(startBuffer) && !time.isAfter(endBuffer);
	}
	public boolean timeConflict(LocalTime time) {
		// TODO
		// Return true if betweenStartAndEnd is true for one
		// or other of time and time.plusHours(1)
		// If you do not have a true value, return
		// delegate.timeConflict(time)
		if (betweenStartAndEnd(time) || betweenStartAndEnd(time.plusHours(1))) 
		{
        	return true;
		}
		// Delegate to other courses
		return delegate.timeConflict(time);
	}
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
        String fStartTime = startTime.format(formatter).toLowerCase();
        String fEndTime = endTime.format(formatter).toLowerCase();
		return courseRubric + courseNumber + "-" + section + " "
				+ courseTitle + "(" + credits + "cr), meets " + fStartTime + "-" + fEndTime;
	}
	
	// TODO
	// implement the other abstract methods in AbstractStudent
	// using calls to the delegate
	@Override
	public String getUniversity() 
	{
		return delegate.getUniversity();
	}

	@Override
	public void setUniversity(String university) 
	{
		delegate.setUniversity(university);
	}

	@Override
	public String getMajor() 
	{
		return delegate.getMajor();
	}

	@Override
	public void setMajor(String major) 
	{
		delegate.setMajor(major);
	}
}
