package GroupProject;


public class Manager extends UserProfile {

   public Manager(String name, String id, String password){
      super(name, id, password);
   }

   public String getId()
   {
      return super.getId();
   }

   public String getName()
   {
      return super.getName();
   }

   public void setId(String id)
   {
      super.setId(id);
   }

   public void setName(String name)
   {
      super.setName(name);
   }


   public void checkCredentials()
   {
      super.checkCredentials();
   }

   public void calculateEstimate(){
      super.calculateEstimate();
   }

   public void checkAvailableSlot()
   {
      super.checkAvailableSlot();
   }

   public void generateOutput(){
      super.generateOutput();
   }

   public void acceptInput(){
      super.acceptInput();
   }

   public void saveToCSV(){
      super.saveToCSV();
   }



   public void showAllAppointments(){
   }

   public void showAllFreeSlots(){
   }

}//class
