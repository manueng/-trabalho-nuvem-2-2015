package org.cloudbus.cloudsim.examples;
 import org.cloudbus.cloudsim.Vm;

import java.util.ArrayList;

import org.cloudbus.cloudsim.DatacenterBroker;
 
public class listavmeBroker{
	 private DatacenterBroker broker;
	 private ArrayList<Vm> vmList; 
	
 public  void setvmList(Vm vm){
	 this.vmList.add(vm);
 };
 
 public  ArrayList<Vm> getVm(){
	  return vmList;
 }
 public void setBroker(DatacenterBroker broker){
	 this.broker=broker;
 }
 public  DatacenterBroker getDatacenterBroker(){
	 return broker;
 }
}