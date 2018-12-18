/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.borntodev.interfaces;

import java.util.List;

/**
 *
 * @author Hamza FAIZA
 */
public interface IServices<T1> {
    
    public void add(T1 t);
    public void remove(int id);
    public void update(int id,T1 t);
    public T1 get(int id);
    public List<T1> getAll();  
    
}
