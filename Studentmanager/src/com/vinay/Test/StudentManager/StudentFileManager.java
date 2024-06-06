package com.vinay.Test.StudentManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class StudentFileManager {
	
	public static void getList(String path) {
		if(path==null)
			throw new IllegalArgumentException();
		File f=new File(path);
		
		if(f.exists() && f.isFile() && f.getName().endsWith(".txt")) {
			BufferedReader br=null;
			
			try {
				br=new BufferedReader(new FileReader(f));
				String s=null;
				while ((s=br.readLine())!=null) {
					StudentManager.studentList.add(studentData(s));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				
					try {
						if(br!=null)
							br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			
		}
		else
			throw new IllegalArgumentException();
	}
	
	public static void addStudent(Student student,String path) {
		if(path==null)
			throw new IllegalArgumentException();
		File f=new File(path);
		List<Student> list;
		if(f.exists() && f.isFile() && f.getName().endsWith(".txt")) {
			BufferedWriter bw=null;
			list=new ArrayList<>();
			try {
				bw=new BufferedWriter(new FileWriter(f,true));
				bw.write(student.saveToFile());
				bw.newLine();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				
					try {
						if(bw!=null)
							bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		}
		else
			throw new IllegalArgumentException();
	}
	public static void updateList(String path,List<Student> studentList) {
		if(path==null)
			throw new IllegalArgumentException();
		File f=new File(path);
		
		if(f.exists() && f.isFile() && f.getName().endsWith(".txt")) {
			BufferedWriter bw=null;
			
			try {
				bw=new BufferedWriter(new FileWriter(f));
				for (Student student : studentList) {
					bw.write(student.saveToFile());
					bw.newLine();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				
					try {
						if(bw!=null)
							bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		}
		else
			throw new IllegalArgumentException();
	}
	
	public static Student studentData(String s) {
		String[] data=s.split(":");
		
		Student student=new Student();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		student.setId(Integer.parseInt(data[0]));
		student.setName(data[1]);
		try {
			student.setDob(sdf.parse(data[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
		
	}
	
}
