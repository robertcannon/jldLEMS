package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.lite.model.Recording;
import org.lemsml.jlems.core.type.LemsCollection;

public class Simulation {

	
		public String name;
		
		public double dt;
		
		public double endTime;
		
		
		public LemsCollection<OutputFiles> outputFiless = new LemsCollection<OutputFiles>();
		
		public LemsCollection<Recording> recordings = new LemsCollection<Recording>();

		public LemsCollection<Display> displays = new LemsCollection<Display>();
		
		public double getRuntime() {
			return endTime;
		}
		
		public double getTimestep() {
			return dt;
		}

		public Recording getRecording() {
			 Recording ret = null;
			 if (recordings.size() > 0) {
				 ret = recordings.get(0);
			 }
			 return ret;
		}

		public LemsCollection<File> getFiles() {
			LemsCollection<File> ret = new LemsCollection<File>();
			for (OutputFiles ofiles : outputFiless) {
				ret.addAll(ofiles.getFiles());
			}
			return ret;
		}

		public LemsCollection<Display> getDisplays() {
			return displays;
		}
		
		
	
}
