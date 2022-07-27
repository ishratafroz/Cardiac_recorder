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

    public List<cardiaclist>getCardiacs(){
        List<cardiaclist>  newcardiaclist=cardiacs;
        Collections.sort(newcardiaclist);
        return newcardiaclist;
    }
    public List<cardiaclist>getCardiacs(int tmp){
        List<cardiaclist>  newcardiaclist=cardiacs;
        return newcardiaclist;
    }
    public void delete(cardiaclist c){
        List<cardiaclist> newcardiaclist= cardiacs;
        if(newcardiaclist.contains(c)){
            cardiacs.remove(c);
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    public int count(){
        return cardiacs.size();
    }
}
