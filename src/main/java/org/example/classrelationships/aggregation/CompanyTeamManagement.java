package org.example.classrelationships.aggregation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String role;
    private List<Team> teams = new ArrayList<>();

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }

    public List<String> getTeamNames() {
        return teams.stream().map(Team::getName).collect(Collectors.toList());
    }

    public String getName() { return name; }
    public String getRole() { return role; }
}

class Team {
    private String name;
    private List<Employee> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addMember(Employee employee) {
        members.add(employee);
        employee.addTeam(this);
    }

    public void dissolve() {
        for (Employee employee : members) {
            employee.removeTeam(this);
        }
        members.clear();
    }

    public String getName() { return name; }
    public List<Employee> getMembers() { return members; }
    public int getMemberCount() { return members.size(); }
}

class Company {
    private String name;
    private List<Employee> employees = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void dissolveTeam(Team team) {
        team.dissolve();
        teams.remove(team);
    }

    public String getName() { return name; }
    public int getEmployeeCount() { return employees.size(); }
    public int getTeamCount() { return teams.size(); }
}

public class CompanyTeamManagement {
    public static void main(String[] args) {
        Company company = new Company("TechCorp");

        Employee alice = new Employee("Alice", "Engineer");
        Employee bob = new Employee("Bob", "Designer");
        Employee charlie = new Employee("Charlie", "Engineer");

        company.addEmployee(alice);
        company.addEmployee(bob);
        company.addEmployee(charlie);

        Team backend = new Team("Backend");
        Team frontend = new Team("Frontend");

        company.addTeam(backend);
        company.addTeam(frontend);

        // Alice is on both teams
        backend.addMember(alice);
        backend.addMember(charlie);
        frontend.addMember(alice);
        frontend.addMember(bob);

        System.out.println("Before dissolving:");
        System.out.println("  " + alice.getName() + "'s teams: " + alice.getTeamNames());
        System.out.println("  Backend has " + backend.getMemberCount() + " members");
        System.out.println("  Company has " + company.getTeamCount() + " teams, "
                + company.getEmployeeCount() + " employees");

        company.dissolveTeam(backend);

        System.out.println("\nAfter dissolving Backend:");
        System.out.println("  " + alice.getName() + "'s teams: " + alice.getTeamNames());
        System.out.println("  " + charlie.getName() + "'s teams: " + charlie.getTeamNames());
        System.out.println("  Company has " + company.getTeamCount() + " teams, "
                + company.getEmployeeCount() + " employees");
        System.out.println("  " + alice.getName() + " still exists: " + alice.getRole());
    }
}