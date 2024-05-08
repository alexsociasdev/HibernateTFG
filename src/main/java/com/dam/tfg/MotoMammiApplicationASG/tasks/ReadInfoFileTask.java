package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.Provider;
import com.dam.tfg.MotoMammiApplicationASG.MotoMammiApplicationAsgApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class ReadInfoFileTask {
    private static MotoMammiApplicationAsgApplication Mn = new MotoMammiApplicationAsgApplication();
    //Ejecutar cada dia y hacer metodo para ejecutar una vez y no alterar el primero
    /*public void task(){
        try{
            System.out.println("\n Esta tarea se lanza cada " +
                    "5 segundos");
        }catch (Exception ex){
            System.err.println("Error "+ ex);
        }
    }*/
}

