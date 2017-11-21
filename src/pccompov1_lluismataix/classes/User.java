/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.classes;

/**
 *
 * @author lluis
 */
public class User {
    
    private int id;
    private String email;
    private String pass;
    private String name;
    private String surname;
    private String dni;
    private String home;
    private String city;
    private String province;
    private String mobile;
    private String creditCard;
    
    
    public User() {
        
    }
    
    public User(int id,String email,String pass,String name,String surname,String dni,String home,String city,String province,String mobile,String creditCard){
        
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.home = home;
        this.city = city;
        this.province = province;
        this.mobile = mobile;
        this.creditCard = creditCard; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "usuario{" + "id=" + id + ", email=" + email + ", pass=" + pass + ", name=" + name + ", surname=" + surname + ", dni=" + dni + ", home=" + home + ", city=" + city + ", province=" + province + ", mobile=" + mobile + ", creditCard=" + creditCard + '}';
    }
    
    
    
}
