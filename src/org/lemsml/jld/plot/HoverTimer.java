package org.lemsml.jld.plot;

import org.lemsml.jld.io.E;
 
public class HoverTimer implements Runnable {


   PickHandler pickHandler;

   boolean active;
   Thread thread;

   int ntick = 0;


   public HoverTimer(PickHandler ph) {
      pickHandler = ph;
      active = false;

      thread = new Thread(this);
      thread.setDaemon(true);
      thread.start();
   }


   public void clear() {
      active = false;
      ntick = 0;
   }

   public void start() {
      active = true;
      ntick = 0;
   }



   public void run() {
      while (true) {
         if (active) {
            ntick += 1;
         }

         if (ntick == 3) {
            pickHandler.hovered();
         }

         try {
            Thread.sleep(100);

         } catch (Exception ex) {
        	 E.info("Hover interrupted?");
         }
      }

   }


}
