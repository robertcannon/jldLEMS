package org.lemsml.jld.plot;
 

public interface BuildPaintInstructor {


     boolean antialias();


     void instruct(Painter p, Builder b);


	Box getLimitBox(Painter p);


}
