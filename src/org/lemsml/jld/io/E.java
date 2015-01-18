package org.lemsml.jld.io;
 


public final class E {

   private static boolean debug = true;

   public static long time0 = 0;

   static String lastShortSource;
   static String cachedAction;

   static String lastErr;
   static int nrep;

   static String lastWarning;
   static int nwrep;
 
   
   private E() {
	   
   }

   public static void setDebug(boolean b) {
	   debug = b;
	   E.trace();
   }
  
   
   private static long getTime() {
      return System.currentTimeMillis();
   }


   public static void zeroTime() {
      time0 = getTime();
   }

   public static String getStringTime() {
      if (time0 == 0) {
	 zeroTime();
      }
      long dt = (getTime() - time0);
      return "" + dt;
   }

   public static void info(String s) {
	  out("INFO", s + getShortSource());
   } 

   public static void out(String typ, String s) {
	   System.out.println(typ + " " + s);	   
   }
 
    
  public static void missing(String s) {
	   out("MISSING", s + getShortSource());
   }


   public static void missing() {
	   out("MISSING", "" + getShortSource());
   }

   public static void error(String s) {
	   out("ERROR", "" + s +  getShortSource());
   }


   public static void stackTrace() {
      (new Exception()).printStackTrace();
   }


   public static void stackTrace(String msg) {
	      (new Exception(msg)).printStackTrace();
   }

   public static void showSource() {
      showSource(10);
   }

   public static void showShortSource() {
      showSource(2);
   }

   public static void showSource(int n) {
      StackTraceElement[] stea = new Exception().getStackTrace();
      for (int i = 2; i < 2 + n && i < stea.length; i++) {
       out("", "  at " + stea[i].toString());
 
      }
   }


   public static String getShortSource() {
	   StackTraceElement[] stea = new Exception().getStackTrace();
	   int iel = 0;
	   String ss = stea[iel].toString();
	   while (ss.indexOf(".E.") > 0 && iel < stea.length - 1) {
		   iel += 1;
		   ss = stea[iel].toString();
	   }
      if (ss.equals(lastShortSource)) {
          	 ss = "";
      } else {
	         lastShortSource = ss;
      }
      return " at: " + ss;
   }

  
  
 

	private static String printArray(double[] da) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < da.length; i++) {
			// sb.append(String.format("%8.3g, ", da[i]));

			// String.format not available in GWT
			sb.append("" + (float)da[i]);
		}
		return sb.toString();
	}

 
	public static void report(String msg, Exception ex) {
		E.error(msg);
		ex.printStackTrace();
		
	}

	public static void trace() {
		stackTrace();
		
	}

	public static void warning(String s) {
		out("WARNING ",  s);
	}
	

	
}
