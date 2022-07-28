package com.example.cardiacrecorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CardiacArrayList {
    public List<cardiaclist> cardiacs=new ArrayList<>();

    public void addCardiac(cardiaclist c){
        if(cardiacs.contains(c)){
            throw new IllegalArgumentException();
        }
        cardiacs.add(c);
    }
    /**
     * sorting the list
     * @return
     */
    public List<cardiaclist>getCardiacs(){
        List<cardiaclist>  newcardiaclist=cardiacs;
        Collections.sort(newcardiaclist);
        return newcardiaclist;
    }

    /**
     *  return the list for unit testing purpose
     * @param tmp
     * @return
     */
    public List<cardiaclist>getCardiacs(int tmp){
        List<cardiaclist>  newcardiaclist=cardiacs;
        return newcardiaclist;
    }
    /**
     * deletion checking and throw exception if null
     * @param c
     */
    public void delete(cardiaclist c){
        List<cardiaclist> newcardiaclist= cardiacs;
        if(newcardiaclist.contains(c)){
            cardiacs.remove(c);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     *  count the size
     * @return
     */

    public int count(){
        return cardiacs.size();
    }
}