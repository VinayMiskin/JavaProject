package com.vinay.Test.StudentManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
	
	static List<Student> studentList;
	static String path;
	public static void main(String[] args) {
		
		try {
			if(args[0]==null)
				throw new IllegalArgumentException("The given path is empty");
			path=args[0];
			studentList=new ArrayList<>();
			StudentFileManager.getList(path);
			System.out.println("The Student List contains "+studentList.size()+" students");
				for (Student student : studentList) {
					System.out.println(student);
				}
				System.out.println();
				while (true) {
					
					
					Scanner sc1=new Scanner(System.in);
					
					System.out.println("Enter 1 to add Student");
					System.out.println("Enter 2 to remove Student");
					System.out.println("Enter 3 to search for student");
					System.out.println("Enter 4 to get the list");
					
					int input=sc1.nextInt() ;
					switch(input) {
					case 1:
						add();
						break;
					case 2:
						delete();
						break;
					case 3:
						search();
						break;
					case 4:
						System.out.println("The Student List contains "+studentList.size()+" students");
						for (Student student : studentList) {
							
							System.out.println(student);
						}
						System.out.println();
						System.out.println("Enter 1 to sort the student by their name");
						System.out.println("Enter 2 to sort the student by their birth");
						System.out.println("Enter 0 to go back to main menu");
						int in=sc1.nextInt();
						if(in==0)
							break;
						else if(in==1) {
							Collections.sort(studentList, new StudentNameComparator());
							for (Student student : studentList) {
								System.out.println(student);
							}
							System.out.println();
						}
						else if(in==2) {
							Collections.sort(studentList, new StudentAgeComparator());
							for (Student student : studentList) {
								System.out.println(student);
							}
							System.out.println();
						}
						else
							throw new IllegalArgumentException("Invalid Input");
						
						Collections.sort(studentList);
						break;
						default:
							System.out.println("Please enter valid data");
							break;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	private static void search() {
		while(true) {
			try {
				Scanner sc1=new Scanner(System.in);
				System.out.println("Enter the student name that needs to be searched");
				String name=sc1.nextLine().toLowerCase();
				List<Student> list=new ArrayList<>();
				for (Student student : studentList) {
					if(student.getName().toLowerCase().contains(name))
						System.out.println(student);
				}
				System.out.println();
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
	}
	
	private static void delete() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Scanner sc1=new Scanner(System.in);
				System.out.println("Enter the student name that needs to deleted");
				String name=sc1.nextLine().toLowerCase();
				List<Student> list=new ArrayList<>();
				for (Student student : studentList) {
					if(student.getName().toLowerCase().contains(name))
						list.add(student);
				}
				if(list.size()==0) {
					System.out.println("List doesn't contain student named "+name);
				}
				else if(list.size()==1) {
					Scanner sc2=new Scanner(System.in);
					System.out.println("the student details as follows");
					System.out.println(list.get(0));
					System.out.println("Enter 'y' to remove this student");
					System.out.println("Or you can enterr any other key to exit without deleting the student");
					char c=sc2.next().charAt(0);
					if(c!='y')
						return;
					else {
						studentList.remove(list.get(0).getId()-1);
						rearrangeId(list.get(0).getId());
						StudentFileManager.updateList(path,studentList);
						return;
					}
				}
				else {
					for (Student student : list) {
						System.out.println(student);
					}
					System.out.println("There multiple students named "+name);
					System.out.println("Please specify the student id to delete");
					int id=sc1.nextInt();
					studentList.remove(id-1);
					rearrangeId(id);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static void rearrangeId(int id) {
		for (int i = id-1 ; i < studentList.size(); i++) {
			studentList.get(i).setId(i+1);
			
		}
		
	}
	private static void add() {
		while (true) {
			try {
				Scanner sc1=new Scanner(System.in);
				Scanner sc2=new Scanner(System.in);
				Student student=new Student();
				System.out.println("Enter Student Name ");
				student.setName(sc1.nextLine());
				System.out.println("Enter date of birth as shown format");
				System.out.println("dd/mm/yyyy");
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				String s=sc2.nextLine();
				Date date=sdf.parse(s);
				
				student.setDob(date);
				student.setId(studentList.size()+1);
				System.out.println(student);
				studentList.add(student);
				StudentFileManager.addStudent(student,path);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
