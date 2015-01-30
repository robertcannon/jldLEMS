package org.lemsml.jld.plot;
 

import java.awt.Color;
import java.awt.FlowLayout;
 

public final class CornerPanel extends BasePanel {  
   static final long serialVersionUID = 1001;
 
   
   public CornerPanel() {
      super();
  
      setLayout(new FlowLayout(FlowLayout.RIGHT));


      // ibut = new IconButton("frame", 0, this);
     //   add(ibut);

      setBg(Color.black);
   }


   public void setBg(Color c) {
      setBackground(c);
      
   }

 


}
