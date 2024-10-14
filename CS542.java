package assignment03;

import java.time.LocalTime;

public class CS542 extends CourseDecorator {
	public CS542(AbstractStudent s) {
		super(s, "CS", "542", "Design Patterns", 3, "01", 
				LocalTime.of(13,10), LocalTime.of(14, 10));
	}
}
