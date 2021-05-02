package com.example.logicalgames;

import java.time.OffsetDateTime;
import java.util.Random;

public class RandomNumber {
    int a;
    boolean bol = false;
    Random random = new Random();
    public RandomNumber(int a){
        this.a = a;
    }


    public int generat(){
        int min = 1, max = 1;
        for (int i = 0; i < a; i++) {
            min *= 10;
        }
        max = min * 10 -1;
        int diff = max - min;
        int n = random.nextInt(diff + 1);
        n += min;
        //while (){

        //}
        return n;
    }
}
