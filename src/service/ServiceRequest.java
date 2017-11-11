package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceRequest{
        public ArrayList<Service> services;
        public Map<String, Staff> staff;

        public ServiceRequest(){

        }

        public static ArrayList<Staff> getStaffForServiceType(String ServiceType){
                //STUB
                ArrayList<Staff> temp = new ArrayList<Staff>();
                Staff john = new Staff("username","password","Translator","John Smith",1234);
                john.setFullName("John Smith");
                temp.add(john);
                return temp;
        }

}
