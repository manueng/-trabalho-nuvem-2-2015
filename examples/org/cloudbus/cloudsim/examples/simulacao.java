package org.cloudbus.cloudsim.examples;
import java.sql.Time;

import java.text.DecimalFormat;
import java.util.ArrayList;


import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.cloudbus.cloudsim.Host;

public class simulacao {
	
	 private  static ArrayList<Vm> createVm(DatacenterBroker broker,ArrayList <Vm> vmlist,int vmid, int mips,int pesNumber, int ram,long bw,long size,String vmm) throws Exception{
		 int  brokerId = broker.getId();
		 Vm vm = new Vm(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
		 vmlist.add(vm);
         broker.submitVmList(vmlist);   
		 return vmlist;
	 }
		private static Datacenter createDatacenter(String name) {

			// Here are the steps needed to create a PowerDatacenter:
			// 1. We need to create a list to store
			// our machine
			List<Host> hostList = new ArrayList<Host>();

			// 2. A Machine contains one or more PEs or CPUs/Cores.
			// In this example, it will have only one core.
			List<Pe> peList = new ArrayList<Pe>();

			int mips = 1000;

			// 3. Create PEs and add these into a list.
			peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

			// 4. Create Host with its id and list of PEs and add them to the list
			// of machines
			int hostId = 0;
			int ram = 2048; // host memory (MB)
			long storage = 1000000; // host storage
			int bw = 10000;

			hostList.add(
				new Host(
					hostId,
					new RamProvisionerSimple(ram),
					new BwProvisionerSimple(bw),
					storage,
					peList,
					new VmSchedulerTimeShared(peList)
				)
			); // This is our machine

			// 5. Create a DatacenterCharacteristics object that stores the
			// properties of a data center: architecture, OS, list of
			// Machines, allocation policy: time- or space-shared, time zone
			// and its price (G$/Pe time unit).
			String arch = "x86"; // system architecture
			String os = "Linux"; // operating system
			String vmm = "Xen";
			double time_zone = 10.0; // time zone this resource located
			double cost = 3.0; // the cost of using processing in this resource
			double costPerMem = 0.05; // the cost of using memory in this resource
			double costPerStorage = 0.001; // the cost of using storage in this
											// resource
			double costPerBw = 0.0; // the cost of using bw in this resource
			LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
														// devices by now

			DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
					arch, os, vmm, hostList, time_zone, cost, costPerMem,
					costPerStorage, costPerBw);

			// 6. Finally, we need to create a PowerDatacenter object.
			Datacenter datacenter = null;
			try {
				datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return datacenter;
		}
	 private static DatacenterBroker createBroker(String name) {	
		DatacenterBroker broker = null;
		System.out.println("1");
		System.out.println(name);
			try {
				System.out.println("2");
				System.out.println(name);
				broker = new DatacenterBroker(name);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return broker;
		}
	 private static  ArrayList<Cloudlet> createcloudlet(ArrayList<Cloudlet> cloudletlist, int id, long length, long fileSize, long outputSize, int pesNumber, int brokerId,int vmid) throws Exception{   
			UtilizationModel utilizationModel = new UtilizationModelFull();
			Cloudlet cloudlet = new Cloudlet(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
			cloudlet.setUserId(brokerId);
			cloudlet.setVmId(vmid);
			cloudletlist.add(cloudlet);
			return cloudletlist;
		}
	  private static   Datacenter createDatacenter( List<? extends Host> hostList, String arch,String os, String vmm,double time_zone, double cost, double costPerMem, double costPerStorage, double costPerBw, String name){
			LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
														// devices by now

			DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
					arch, os, vmm, hostList, time_zone, cost, costPerMem,
					costPerStorage, costPerBw);

			Datacenter datacenter = null;
			try {
				datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return datacenter;
	  }
	  
	  private static ArrayList<Pe> createpe(int mips,int n, ArrayList<Pe> pelist){
		pelist.add(new Pe(n, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
	    return pelist;
	  }
	  private static List<? extends Host> createhost( List< ? extends Pe> peList,List<Host> hostList, int mips, int hostId, int ram, long storage,int bw){

			// 2. A Machine contains one or more PEs or CPUs/Cores.
			// In this example, it will have only one core.
			
			hostList.add(
				new Host(
					hostId,
					new RamProvisionerSimple(ram),
					new BwProvisionerSimple(bw),
					storage,
					peList,
					new VmSchedulerTimeShared(peList)
				)
			);
			return hostList;
	  }
	   private static  Double geraretorno(DatacenterBroker  broker,List<Cloudlet> list, int  ncloudlets){
		     Cloudlet cloudlet;
		     Double sTime=0.00;
		     Double Time;
		     Double rTime;
		     DecimalFormat dft = new DecimalFormat("###.##");
		     CloudSim.startSimulation(); 
		     CloudSim.stopSimulation();
		     List<Cloudlet> newList = broker.getCloudletReceivedList();
	         //Final step: Print results when simulation is over
				//List<Cloudlet> newList = broker.getCloudletReceivedList();
			   cloudlet = newList.get(-1);	
			   System.out.println(dft.format(cloudlet.getExecStartTime()));			   
	           System.out.println(dft.format(cloudlet.getFinishTime()));
	           sTime= cloudlet.getFinishTime()+sTime;
	           return sTime;
	   }  
	     private static  void bindvm( Cloudlet cloudlet, int vmid, int brokerId){
	    		cloudlet.setUserId(brokerId);
				cloudlet.setVmId(vmid);
	     }
	 public static void main(String[] args) throws Exception{
		 int num_user = 1; // number of cloud users
		 Double Time; 
		 Calendar calendar = Calendar.getInstance();
		 boolean trace_flag = false; // mean trace events
		 CloudSim.init(num_user, calendar, trace_flag);
		 ArrayList<Vm> vmlist= new ArrayList<Vm>();
		 String name="xen";
		 Datacenter datacenter=createDatacenter("xen");
		 DatacenterBroker broker= createBroker(name);
		 vmlist=createVm(broker,vmlist,2,3,10,2,256,500,name);
		 ArrayList<Cloudlet> cloudletlist= new ArrayList<Cloudlet>();
		 cloudletlist=createcloudlet(cloudletlist,1,10000,10000,100000,1000,1,1);
		 Time=geraretorno(broker,cloudletlist,0);
		 ArrayList<Pe> pelist= new ArrayList<Pe>();
		 pelist= createpe( 2,2,pelist);
		 //System.out.println(Time);
   
		 //System.out.println(pelist.get(0));
		 ArrayList<Host>  hostlist=   new ArrayList<Host>();
		 Cloudlet cloudlet;
		 hostlist= (ArrayList<Host>) createhost(pelist,hostlist, 1,2,256,1000,2);
         
         
		 
	 }
	

}
