package com.finalnm.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private StringProperty name;
    private StringProperty postalCode;

    public Customer(String name, String postalCode) {
        this.name = new SimpleStringProperty(name);
        this.postalCode = new SimpleStringProperty(postalCode);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }
}
