package com.nk.zipcode.merger;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
/**
 * @author Neha Kumbhani
 *
 */
public class AppTest extends TestCase {
	/**
	 * @param testName
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite(AppTest.class);
		return suite;
	}

	/**
	 * @throws ZipCodeValidationException
	 */
	public void testLoadedList() throws ZipCodeValidationException {
		String inputDataSet = "[10000,20000] [20000,30000] [50000,60000] [80000,90000]";
		ZipCodeRangeMerger zipCodeRangeMerger = new ZipCodeRangeMerger(inputDataSet);
		List<ZipCode> zipcodeList = zipCodeRangeMerger.mergeZipcode(inputDataSet);
		assertTrue(zipcodeList.size() > 0);
	}

	/**
	 * @throws ZipCodeValidationException
	 */
	public void testfinalResultToMatch() throws ZipCodeValidationException {
		String inputDataSet = "[10000,20000] [20000,30000]";
		ZipCodeRangeMerger zipcodeProcessor = new ZipCodeRangeMerger(inputDataSet);
		List<ZipCode> zipcodeList = zipcodeProcessor.mergeZipcode(inputDataSet);
		assertTrue(zipcodeList.size() == 1);
	}

	/**
	 * 
	 */
	public void testZipCodeValidationException() {
		try {
			String inputDataSet = "[92004,92002] [92003,92004]";
			ZipCodeRangeMerger zipcodeProcessor = new ZipCodeRangeMerger(inputDataSet);

			zipcodeProcessor.mergeZipcode(inputDataSet);
		} catch (ZipCodeValidationException e) {
			assertEquals("ZipCodeValidationException", e.getClass().getSimpleName());
		}
	}

	/**
	 * 
	 */
	public void testExceptionWhenMoreRanges() {
		try {
			String inputDataSet = "[92004,92002,92003] [92003,92004]";
			ZipCodeRangeMerger zipcodeProcessor = new ZipCodeRangeMerger(inputDataSet);
			zipcodeProcessor.mergeZipcode(inputDataSet);
		} catch (ZipCodeValidationException e) {
			assertEquals("ZipCodeValidationException", e.getClass().getSimpleName());
		}
	}

	/**
	 * 
	 */
	public void testExceptionMessageWhenLowerBoundGreater() {
		try {
			String inputDataSet = "[92004,92002] [92003,92004]";
			ZipCodeRangeMerger zipcodeProcessor = new ZipCodeRangeMerger(inputDataSet);
			zipcodeProcessor.mergeZipcode(inputDataSet);
		} catch (ZipCodeValidationException e) {
			String expectedMessage = "92004 92002:  Zipcode lower bound should be less" + " than upper bound";
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	/**
	 * 
	 */
	public void testExceptionMessageWhenMoreRangeGiven() {
		try {
			String inputDataSet = "[92004,92002,92003] [92003,92004]";
			ZipCodeRangeMerger zipcodeProcessor = new ZipCodeRangeMerger(inputDataSet);
			zipcodeProcessor.mergeZipcode(inputDataSet);
		} catch (ZipCodeValidationException e) {
			String expectedMessage = "92004 92002:  Zipcode lower bound should be less than upper bound";
			assertEquals(expectedMessage, e.getMessage());
		}
	}

}
