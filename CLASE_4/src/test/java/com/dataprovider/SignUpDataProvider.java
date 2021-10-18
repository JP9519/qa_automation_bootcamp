package com.dataprovider;

import com.autopractice.utilities.StringFunctions;
import org.testng.annotations.DataProvider;

public class SignUpDataProvider {

    int n = 10;

    @DataProvider(name = "valid data")
    public Object[][] validData() {
        return new Object[][]{
                {"Juan", "Perez" ,AuthenticationDataProvider.USER_EMAIL, "12345", "19", "April", "1990", "Oktana", "5th Avenue", "1st Avenue", "Miami",
                        "Florida", "30301", "United States","Bayside", "123456789", "4364555","Home"}
        };
    }

    @DataProvider(name = "missing fields")
    public Object[][] missingFields() {
        return new Object[][]{
                {null, "Perez" ,AuthenticationDataProvider.USER_EMAIL, "12345", "19", "April", "1998", "Oktana", "5th Avenue", "Apartment", "Miami",
                        "Florida", "30301", "United States","Bayside", "123456789", "4364555","Home"},
                {"Raul", null ,AuthenticationDataProvider.USER_EMAIL, "12345", "19", "May", "1997", "Google", "5th Avenue", "Suite", "Montgomery",
                        "Alabama", "30301", "United States","Bayside", "123456789", "4364555","Home"},
                {"Alexis", "Garcia" ,"", "12345", "19", "November", "1996", "MSI", "5th Avenue", "House", "Miami",
                        "Florida", "30301", "United States","Bayside", "123456789", "4364555","Home"},
                {"Sergio", "Guerrero" ,AuthenticationDataProvider.USER_EMAIL, null, "19", "january", "1999", "Dell", "5th Avenue", "Building", "Miami",
                        "Florida", "30301", "United States","Bayside", "123456789", "4364555","Home"},
                {"Juan", "Ugarte" ,AuthenticationDataProvider.USER_EMAIL, "12345", "19", "December", "1990", "CSL", "5th Avenue", "Floor", null,
                        "Florida", "30301", "United States","Bayside", "123456789", "4364555","Home"}
        };
    }

    @DataProvider(name = "missing phone")
    public Object[][] missingPhone() {
        return new Object[][]{
                {"Juan", "Perez" ,AuthenticationDataProvider.USER_EMAIL, "12345", "19", "April", "1990", "Oktana", "5th Avenue", "1st Avenue", "Miami",
                        "Florida", "30301", "United States","Bayside", null, null,"Home"}
        };
    }

    @DataProvider(name = "missing zipcode")
    public Object[][] missingZipCode() {
        return new Object[][]{
                {"Juan", "Perez" ,AuthenticationDataProvider.USER_EMAIL, "12345", "19", "April", "1990", "Oktana", "5th Avenue", "1st Avenue", "Miami",
                        "Florida", null, "United States","Bayside", "123456789", "4364555","Home"},
                {"Juan", "Perez" ,AuthenticationDataProvider.USER_EMAIL, "12345", "19", "Abril", "1990", "Oktana", "5th Avenue", "1st Avenue", "Miami",
                        "Florida", "123456", "United States","Bayside", "123456789", "4364555","Home"}// caso Zipcode mayor a 5 digitos
        };
    }

    @DataProvider(name = "missing state")
    public Object[][] missingState() {
        return new Object[][]{
                {"Juan", "Perez" ,AuthenticationDataProvider.USER_EMAIL, "12345", "19", "April", "1990", "Oktana", "5th Avenue", "1st Avenue", "Miami",
                        null, "30301", "United States","Bayside", "123456789", "4364555","Home"},
        };
    }

    @DataProvider(name = "adding product")
    public Object[][] addingProduct() {
        return new Object[][]{
                {"0"},{"4"},{"5"}
        };
    }


}
