package org.cloudbus.cloudsim.examples;

import java.util.ArrayList;

import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.Host;

public class listabrokerehost {
 DatacenterBroker broker;
 ArrayList<Host> hostlist;
 public void setDatacenterBroker( DatacenterBroker broker){
	  this.broker=broker;
 }
 public DatacenterBroker getDatacenterBroker(){
	 return broker;
 }
 public void sethostlist(Host host ){
	 this.hostlist.add(host);
 }
 public ArrayList<Host> gethostlist(){
	 return this.hostlist;  
 }
 
 
}
