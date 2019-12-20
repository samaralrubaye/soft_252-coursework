/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author engsa
 */
public class medicine {
     Scanner reeder = new Scanner(System.in);
    String name;
    private final String pmedicine;
    medicine(String pmedicine){
        this.pmedicine = pmedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "medicine{" + "name=" + name + '}';
    }
}
    

