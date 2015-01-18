package org.lemsml.jld.model.core;

import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.expression.ParseError;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.Unit;
 
public final class QuantityReader {


	// static Pattern pat = Pattern.compile("(^[\\d\\.\\+-]*(?:[Ee][\\+-]?[\\d]+)?)(.*?)$");

	Lems lems;
	
	HashSet<String> numHS = null;
	 	
	HashMap<String, Unit> unitHM = null;
	
	
	
	public QuantityReader(Lems lems) {
		this.lems = lems;
		
		numHS = new HashSet<String>();
		for (int i = 0; i <= 9; i++) {
			numHS.add("" + i);
		}
		numHS.add(".");
		numHS.add("+");
		numHS.add("-");

		unitHM = new HashMap<String, Unit>();
		for (Unit u : lems.getUnits()) {
			unitHM.put(u.getSymbol(), u);
		}
	}
	
	 
	
		
	public DimensionalQuantity parseValue(String aarg) throws ParseError {
		String arg = aarg.trim();
		
		DimensionalQuantity ret = new DimensionalQuantity();
		if (arg.trim().length() == 0) {
			ret.setNoValue();

		} else {
			
			String[] snv = split(arg);
			
			String snum = snv[0];
			String su = snv[1];
			
				double d = Double.parseDouble(snum);
	 			
				
				// E.info("Val is " +d+", unit is "+su);

				if (su == null || su.length() <= 0) {
					ret.setValue(d, lems.NONE_DIMENSION);

				} else {
					su = su.trim();
					if (unitHM.containsKey(su)) {
						Unit u = unitHM.get(su);
				
						double dlocal = getAbsoluteValue(d, u);
 						ret.setValue(dlocal, unitHM.get(su).getDimensionObject());

					} else {
						String msg = "Unrecognized units: " + su + " when parsing " + aarg;
						E.info(msg + "\n" + "known units: " + unitHM);
						throw new ParseError("Unrecognized units " + su);
					}
				}

				ret.setOriginalText(arg);

			 
		}
		return ret;
	}

	
	public double getAbsoluteValue(double val, Unit u) {
		return ((val + u.getOffset()) * u.getScale() * Math.pow(10, u.getPower()));
	}

	    
	public double getLocalValue(double siVal, Unit u) {
		return siVal / (u.getScale() * Math.pow(10, u.getPower())) - u.getOffset();
	}

	

	private double getLocalValue(Unit u) {
		// TODO Auto-generated method stub
		return 0;
	}




	private String[] split(String arg) {
		int ild = 0;
		if (arg.indexOf(" ") > 0) {
			ild = arg.indexOf(" ");
		} else {
			ild = lastNumIndex(arg);
		}
		
		String snum = arg;
		String su = "";
		if (ild > 0) {
			snum = arg.substring(0, ild); 
			su = arg.substring(ild, arg.length());
		}
		String[] ret = new String[2];
		ret[0] = snum;
		ret[1] = su;
 		return ret;
	}
		
		
	private int lastNumIndex(String str) {
		int ret = 0;
		int sl = str.length();
		boolean doneE = false;
		
		while (true) {
			if (ret >= sl) {
				break;
			}
			String sn = str.substring(ret, ret + 1);
			if (ret < sl && numHS.contains(sn)) {
				// continue
				ret += 1;
			} else if (!doneE && (sn.equals("e") || sn.equals("E")) && ret < sl - 2 && numHS.contains(str.substring(ret + 1, ret + 2))) {
				doneE = true;
				ret += 1;
				
			} else {
				break;
			}
		}
		return ret;
	}
	
	
	
	public static void main(String[] argv) {
		
		QuantityReader qr = null;
		qr.runChecks();
	}
	
		
	public void runChecks() {	
		E.info("numhs is " + numHS);
		String[] qs = {"1 mV", "1.234mV", "1.2e-4 mV", "1.23e-5A", "1.23e4A", "1.45E-8 m", "1.23E-8m2", "60", "6000", "123000"};
		for (String s : qs) {
			splitOne(s);
		}
	}
	
	private void splitOne(String s) {
		String[] snv = split(s);
		E.info("Split " + s + " into: " + snv[0] + " | " + snv[1]);
	}
	
}
