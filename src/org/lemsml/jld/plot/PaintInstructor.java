package org.lemsml.jld.plot;
 


public interface PaintInstructor {


     boolean antialias();

     void instruct(Painter p);
     
     Box getLimitBox();
   

}
