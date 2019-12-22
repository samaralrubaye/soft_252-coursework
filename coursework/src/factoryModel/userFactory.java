/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

public class userFactory {

    public static usersConcrete getAdminUser(String userId, String address, String name, String password, char ID_s, int ID_d) {
        return new adminConcrete(userId, address, name, password, ID_s, ID_d);
    }

    public static usersConcrete getDoctorUser(String notes, medicine medicin, String userId, String addr, String name, String password, int ID_d, char ID_s) {
        return new DoctorsConcrete(notes, medicin, userId, addr, name, password, ID_d, ID_s);
    }

    public static usersConcrete getScretaryUser(String userId, String address, String name, String password, char ID_s, int ID_d) {
        return new SecretaryConcreate(userId, address, name, password, ID_d, ID_s);
    }

    public static usersConcrete getPatientUser(int age, String gender, String userId, String addr, String name, String password, int ID_d, char ID_s) {
        return new PatientConcrete(age, gender, userId, addr, name, password, ID_d, ID_s);
    }
}
