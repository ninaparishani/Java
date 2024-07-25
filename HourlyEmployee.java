
//Create a concrete class HourlyEmployee that extends Employee and implements Payroll
class HourlyEmployee extends Employee implements Payroll {
	public String name;
	public double hourlyWage;
	public double hoursWorked;
	
	public HourlyEmployee(String name, double wage, double hours) {
		this .name=name;
		this.hourlyWage=wage;
		this.hoursWorked=hours;
	}
	
	@Override
	public void generatePayroll() {
		grossPay = hourlyWage*hoursWorked;
		taxes = grossPay*0.15;
		netPay = grossPay-taxes;
	}
	
	@Override
	public String toString() {
		    return String.format("Hourly Employee: %s\n" +
		                         "Gross Pay: $%.2f\n" +
		                         "Taxes: $%.2f\n" +
		                         "Net Pay: $%.2f",
		                         name, grossPay, taxes, netPay);
		}

	}

