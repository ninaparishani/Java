
//Create a concrete class SalaryEmployee that extends Employee and implements Payroll

class SalaryEmployee extends Employee implements Payroll {
	public String name;
	public double salary;
	
	
	public SalaryEmployee(String name, double salary) {
		this.name=name;
		this.salary=salary;
	}
	
	@Override
	public void generatePayroll() {
		double weeklySalary = salary/26;
		grossPay = weeklySalary;
		taxes = weeklySalary*0.15;
		netPay = grossPay-taxes;
	}
	@Override
	public String toString() {
		  return String.format("Salary Employee: %s\n" +
                  "Gross Pay: $%.2f\n" +
                  "Taxes: $%.2f\n" +
                  "Net Pay: $%.2f",
                  name, grossPay, taxes, netPay);
	}

}
