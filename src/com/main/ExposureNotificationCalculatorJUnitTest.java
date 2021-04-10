package com.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Software Entwicklung 2 Praktikum (SWE2P). SoSe 2021
 * @author Prof. Dr. Stefan Kugele, Technische Hochschule Ingolstadt
 * @version 2021-03-23
 */
public class ExposureNotificationCalculatorJUnitTest {

	public static final int DEFAULT_DAYS = 5;
	private static final double FLOAT_PRECISION = 0.001;
	
	private ExposureNotificationCalculator defaultCalculator;
	
	@Before
	public void initTest() {
		this.defaultCalculator = new ExposureNotificationCalculator(DEFAULT_DAYS);
	}
	

	@Test
	public void testInvalidContactType() {
		Exposure exposure = defaultCalculator.getExposure();
		assertFalse("Adding a non-existent contact type to an exposure should return false", exposure.addContact("low"));
		assertFalse("Adding a non-existent contact type to an exposure should return false", exposure.addContact(""));
		assertFalse("Adding a non-existent contact type to an exposure should return false", exposure.addContact(null));
	}
	
	@Test
	public void testConstantUsage() {
		ExposureNotificationApp.CONTACT_SPEC_HIGH_NAME = "sdfgsdfg"; // NOTE: DO NOT DO THIS IN YOUR IMPLEMENTATION!
		assertTrue("CONTACT_SPEC_* constants were maybe not utilized, otherwise this test should succeed",
				defaultCalculator.getExposure().addContact(ExposureNotificationApp.CONTACT_SPEC_HIGH_NAME));

		ExposureNotificationApp.CONTACT_SPEC_LOW_NAME = "sdfg.234ds"; // NOTE: DO NOT DO THIS IN YOUR IMPLEMENTATION!
		assertTrue("CONTACT_SPEC_* constants were maybe not utilized, otherwise this test should succeed",
				defaultCalculator.getExposure().addContact(ExposureNotificationApp.CONTACT_SPEC_LOW_NAME));

		ExposureNotificationApp.CONTACT_SPEC_NO_NAME = "12345"; // NOTE: DO NOT DO THIS IN YOUR IMPLEMENTATION!
		assertTrue("CONTACT_SPEC_* constants were maybe not utilized, otherwise this test should succeed",
				defaultCalculator.getExposure().addContact(ExposureNotificationApp.CONTACT_SPEC_NO_NAME));
	}
	
	@Test
	public void testIsExposedNo() {
		assertFalse("An empty exposure should not represent a high exposure", defaultCalculator.isExposure());
		
		for (int i = 1; i <= DEFAULT_DAYS; i++) {
			defaultCalculator.addNoRiskContact();
			assertFalse("Adding " + i + " contacts with " + ExposureNotificationApp.CONTACT_SPEC_NO_NAME + " risk to an exposure, should not lead to a HIGH overall exposure", defaultCalculator.isExposure());
		}
	}
	
	@Test
	public void testIsExposed() {
		assertFalse("An empty exposure should not represent a high exposure", defaultCalculator.isExposure());
		
		float sum = 0.f;
		
		defaultCalculator.addHighRiskContact();
		sum = ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_6/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		assertEquals(sum > 0.85f, defaultCalculator.isExposure());
		
		defaultCalculator.addNoRiskContact();
		sum = ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_8/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		assertEquals(sum > 0.85f, defaultCalculator.isExposure());
		
		defaultCalculator.addNoRiskContact();
		defaultCalculator.addNoRiskContact();
		defaultCalculator.addNoRiskContact();
		sum = ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_5/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		assertEquals(sum > 0.85f, defaultCalculator.isExposure());
	}

	@Test
	public void testAddMixed() {
		defaultCalculator.addHighRiskContact(); // H----
		defaultCalculator.addNoRiskContact();   // -H---
		defaultCalculator.addNoRiskContact();   // --H--
		defaultCalculator.addNoRiskContact();   // ---H-
		defaultCalculator.addNoRiskContact();   // ----H
		defaultCalculator.addNoRiskContact();   // -----
		double sum = 0.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		
		defaultCalculator.addLowRiskContact();  // L----
		sum = ExposureNotificationApp.CONTACT_SPEC_LOW_VALUE * ExposureNotificationApp.TRL_6/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		
		defaultCalculator.addNoRiskContact();   // -L---
		sum = ExposureNotificationApp.CONTACT_SPEC_LOW_VALUE * ExposureNotificationApp.TRL_8/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		
		defaultCalculator.addHighRiskContact();   // H-L--
		sum = ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_6/5.f
				+ ExposureNotificationApp.CONTACT_SPEC_LOW_VALUE * ExposureNotificationApp.TRL_8/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		
		defaultCalculator.addHighRiskContact();   // HH-L-
		sum = ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_6/5.f
				+ ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_8/5.f
				+ ExposureNotificationApp.CONTACT_SPEC_LOW_VALUE * ExposureNotificationApp.TRL_8/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		
		defaultCalculator.addNoRiskContact();   // -HH-L
		sum = ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_8/5.f
				+ ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_8/5.f
				+ ExposureNotificationApp.CONTACT_SPEC_LOW_VALUE * ExposureNotificationApp.TRL_5/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
		
		defaultCalculator.addNoRiskContact();   // --HH-
		sum = ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_8/5.f
				+ ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE * ExposureNotificationApp.TRL_8/5.f;
		assertEquals(sum, defaultCalculator.getWeightedExposureSum(), FLOAT_PRECISION);
	}
}
