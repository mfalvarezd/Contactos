/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.utilidades;

import java.util.Comparator;

/**
 *
 * @author mfalvarez
 * @param <E>
 */
public interface List<E> extends Iterable<E> {
    
    public int size();

    public boolean isEmpty();

    public void clear();

    public boolean addFirst(E element); // inserta el elemento element al inicio

    public boolean add(E element); // inserta el elemento element al final

    public E removeFirst(); // remueve el elemento al inicio de la lista

    public E removeLast(); // remueve el elemento al final de la lista

    public boolean add(int index, E element); // inserta element en la posición index

    public E remove(int index); // remueve y retorna el elemento en la posición index
    
    public E remove(E elemento);

    public E get(int index); // retorna el elemento ubicado en la posición index

    public E set(int index, E element); // setea el element en la posición index
    
    public E getPrevious (E actual);
    
    public E getNext (E actual);

    @Override
    public String toString();
    
    
    public void reverse();
    
    public List<E> subList(int from, int to);
    
    public List<E> removeFirstNElements (int n);
    
    public List<E> findIntersection (List<E> anotherList, Comparator<E> cmp);
}
