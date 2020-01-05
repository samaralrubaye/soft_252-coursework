/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * interface for Objects that can be persisted in readable format
 */
public interface IPersistable {
    public String toPersistableTxtFormat();
}
