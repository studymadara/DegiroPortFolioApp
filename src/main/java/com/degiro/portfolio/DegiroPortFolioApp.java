package com.degiro.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DegiroPortFolioApp
{
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(DegiroPortFolioApp.class,args);
    }
}
