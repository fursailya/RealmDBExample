package realm.test.fursa.realmdbexample.models;

import io.realm.RealmObject;

/**
 * Created by Иван on 14.02.2017.
 */

public class EmployeeObject extends RealmObject {
    private String name;
    private String surname;
    private long phone;

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
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
}
