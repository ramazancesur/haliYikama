package hali.pro.com.haliyikama.DTO;

import java.util.Date;

import hali.pro.com.haliyikama.Helper.BaseDTO;
import hali.pro.com.haliyikama.Helper.EnumUtil;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class Calisan extends BaseDTO {
    private EnumUtil.EmployeeType employeeType;
    private String nameSurname;
    private String adress;
    private Date birthdate;
    private double payment;
    private String phoneNumber;


    public EnumUtil.EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EnumUtil.EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

}
