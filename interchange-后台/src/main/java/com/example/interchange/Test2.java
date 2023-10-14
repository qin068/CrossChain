package com.example.interchange;

import com.example.interchange.controller.EncryptController;

public class Test2 {
    public static void main(String args[]){
        EncryptController test = new EncryptController();
        test.encrypt("DES","12345678","您好，我是CompanyA");
//        test.decrypt("PBE","1111","/17T2jTrIBU7wod1L/HcWAL5rIRLwSAS");
    }
}
