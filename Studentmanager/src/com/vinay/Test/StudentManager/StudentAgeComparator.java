package com.vinay.Test.StudentManager;

import java.util.Comparator;

public class StudentAgeComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o1.getDob().compareTo(o2.getDob());
	}

}
