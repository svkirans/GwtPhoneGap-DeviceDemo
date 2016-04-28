package com.codingsqaure.cordova.device.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.device.Device;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DeviceDemo implements EntryPoint {
	
	 private Logger log = Logger.getLogger(getClass().getName());


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		 GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

		      @Override
		      public void onUncaughtException(Throwable e) {
		        Window.alert("uncaught: " + e.getLocalizedMessage());
		        Window.alert(e.getMessage());
		        log.log(Level.SEVERE, "uncaught exception", e);
		      }
		    });

		    final PhoneGap phoneGap = GWT.create(PhoneGap.class);

		    phoneGap.addHandler(new PhoneGapAvailableHandler() {

		      @Override
		      public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
		    	    createUI(phoneGap);
		      }
		    });

		    phoneGap.addHandler(new PhoneGapTimeoutHandler() {

		      @Override
		      public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
		        Window.alert("can not load phonegap");

		      }
		    });
		    phoneGap.initializePhoneGap();
	}
	
	private void createUI(final PhoneGap phoneGap) {
		try {
			FlowPanel container = new FlowPanel();
			Form widgetList = new Form();
			widgetList.setHeader(new Label("Device"));

			Device device = phoneGap.getDevice();

			widgetList.add(new FormEntry("Platform", createTextBox(device
					.getPlatform())));
			widgetList.add(new FormEntry("OS Version", createTextBox(device
					.getVersion())));
			widgetList.add(new FormEntry("Cordova Version",
					createTextBox(device.getPhoneGapVersion())));
			widgetList.add(new FormEntry("Model", createTextBox(device
					.getModel())));
			widgetList.add(new FormEntry("Manufacturer", createTextBox(device
					.getManufacturer())));
			widgetList.add(new FormEntry("Name",
					createTextBox(device.getName())));
			widgetList.add(new FormEntry("Serial", createTextBox(device
					.getSerial())));
			widgetList.add(new FormEntry("UUID",
					createTextBox(device.getUuid())));
			widgetList.add(new FormEntry("Is Virtual", createTextBox(String
					.valueOf(device.isVirtual()))));
			container.add(widgetList);
			RootPanel.get("DeviceDemo").add(container);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MTextBox createTextBox(String value) {
		MTextBox mtb = new MTextBox();
  		mtb.setText(value);
  		mtb.setReadOnly(true);
  		return mtb;
	}
}
