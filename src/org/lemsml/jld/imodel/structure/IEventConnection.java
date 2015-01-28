package org.lemsml.jld.imodel.structure;

public interface IEventConnection {

	String getFrom();

	String getTo();

	String getSourcePort();

	String getTargetPort();

	String getDelay();

	String getReceiver();

	String getReceiverContainer();

}
