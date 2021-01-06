package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        String s = "2019-09-03T10:32:28Z";
        s = s.replace("T", " ");
        s = s.replace("Z", "");
        Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);

        System.out.println(from);
    }
}
