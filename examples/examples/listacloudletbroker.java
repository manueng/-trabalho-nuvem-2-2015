package org.cloudbus.cloudsim.examples;
import org.cloudbus.cloudsim.DatacenterBroker;

import java.util.ArrayList;

import org.cloudbus.cloudsim.Cloudlet;
public class listacloudletbroker {
   private DatacenterBroker broker;
   private ArrayList<Cloudlet> cloudlet;
 
  public  void setBroker(DatacenterBroker broker){
	  this.broker=broker;
  }
  public DatacenterBroker getBroker(){
	  return broker;
  }
  public void setcloudlet(Cloudlet cloudlet){
	  this.cloudlet.add(cloudlet);
  }
  public ArrayList<Cloudlet>  getcloudlet(){
	  return cloudlet;
  }
}
