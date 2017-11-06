package com.danielsandrutski;

import com.danielsandrutski.convert.NumbersWordsConvert;

public class Main {

    public static void main(String[] args) {
        System.out.println(new NumbersWordsConvert(2256.225).ConvertDouble());
        System.out.println(new NumbersWordsConvert("132").ConvertIntegerPartWithLabel("балл", "балла", "баллов", 0));
    }
}
