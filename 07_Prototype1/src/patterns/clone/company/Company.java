package patterns.clone.company;

import java.util.ArrayList;

public class Company {
	private String name;
	private ArrayList<Person> employees;

	public Company(String name, ArrayList<Person> employees) {
		this.name = name;
		this.employees = employees;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}
	
	public int getSize() {
		return employees.size();
	}
	
	public void addEmployee(Person p) {
		this.employees.add(p);
	}

	public boolean equals(Object o) {
		if (o instanceof Company) {
			Company c = (Company) o;
			return name.equals(c.name) 
					&& employees.equals(c.employees);
		} else {
			return false;
		}
	}

	public Company clone() {
		ArrayList<Person> copyEmployees = new ArrayList<Person>();
		for(Person p: employees){
			copyEmployees.add(p.clone());
		}
		return new Company(name, copyEmployees);
	}
}
