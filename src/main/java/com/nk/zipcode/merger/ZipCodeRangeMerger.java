package com.nk.zipcode.merger;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Neha Kumbhani
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ZipCodeRangeMerger {

	String zipCodeRangeWithoutMerge;

	/**
	 * This is a single argument constructor
	 * 
	 * @param zipCodeRangeWithoutMerge
	 */
	public ZipCodeRangeMerger(String zipCodeRangeWithoutMerge) {
		this.zipCodeRangeWithoutMerge = zipCodeRangeWithoutMerge;
	}

	/**
	 * This method compares the lower and upper bounds from the input zipcode ranges
	 * and merges the valid ranges as well as validates the input zipcode ranges
	 * 
	 * @param zipcodeInputRanges
	 * @return
	 * @throws ZipCodeValidationException
	 */
	public List<ZipCode> mergeZipcode(String zipcodeInputRanges) throws ZipCodeValidationException {
		List<ZipCode> zipcodesList = new LinkedList();
		List<ZipCode> finalZipcodesList;
		String[] zipcodeInputRange = zipcodeInputRanges.split(" ");
		for (int i = 0; i < zipcodeInputRange.length; i++) {
			String[] eachZipRange = zipcodeInputRange[i].replaceAll("\\[|\\]", "").split(",");
			int lowerBound = stringToInt(eachZipRange[0].trim());
			int upperBound = stringToInt(eachZipRange[1].trim());

			listZipcodeIntoUpperAndLowerbound(zipcodesList, lowerBound, upperBound);

			if (eachZipRange.length != 2) {
				throw new ZipCodeValidationException(ZipCodeConstant.ZIP_CODE_LB_UB);
			}
		}
		Comparator<ZipCode> zipcodeComprator = Comparator.comparing(ZipCode::getLowerBound);
		Collections.sort(zipcodesList, zipcodeComprator);

		finalZipcodesList = mergeZipcodes(zipcodesList);

		return finalZipcodesList;
	}

	/**
	 * This method compares all the lower and upper bounds within the input zipcode
	 * ranges
	 * 
	 * @param zipcodesList
	 * @param lowerBound
	 * @param upperBound
	 * @throws ZipCodeValidationException
	 */
	private void listZipcodeIntoUpperAndLowerbound(List<ZipCode> zipcodesList, int lowerBound, int upperBound)
			throws ZipCodeValidationException {
		if (checkZipLength(lowerBound) && checkZipLength(upperBound)) {
			throw new ZipCodeValidationException(lowerBound + " " + upperBound + ": " + ZipCodeConstant.ZIP_CODE_LENGTH);
		}
		if (compareZipcodeRange(lowerBound, upperBound)) {
			throw new ZipCodeValidationException(
					lowerBound + " " + upperBound + ":  " + ZipCodeConstant.ZIP_CODE_UB_LESS_THEN_UP);
		}
		ZipCode zipcode = new ZipCode(lowerBound, upperBound);
		zipcodesList.add(zipcode);
	}

	/**
	 * This method compares lower and upper bounds
	 * 
	 * @param lowerBound
	 * @param upperBound
	 * @return
	 */
	public boolean compareZipcodeRange(int lowerBound, int upperBound) {
		return lowerBound > upperBound;
	}

	/**
	 * This method converts zipcode from String to int datatpe
	 * 
	 * @param zipcode
	 * @return
	 */
	public int stringToInt(String zipcode) {
		return Integer.parseInt(zipcode.trim());
	}

	/**
	 * This method checks the length of the zipcode
	 * 
	 * @param zipcode
	 * @return
	 */
	public boolean checkZipLength(int zipcode) {
		return ((int) (Math.log10(zipcode) + 1)) != 5;
	}

	/**
	 * This method compares and merges the zipcodes from the given sorted zipcode
	 * ranges
	 * 
	 * @param sortedZipCodeList
	 * @return
	 */
	public List<ZipCode> mergeZipcodes(List<ZipCode> sortedZipCodeList) {
		List<ZipCode> mergedZipcodeList = new LinkedList();
		ZipCode zipcode = null;
		for (ZipCode zipcodeInterval : sortedZipCodeList) {
			if (zipcode == null)
				zipcode = zipcodeInterval;
			else {
				if (zipcode.getUpperBound() >= zipcodeInterval.getLowerBound()) {
					zipcode.setUpperBound(Math.max(zipcode.getUpperBound(), zipcodeInterval.getUpperBound()));
				} else {
					mergedZipcodeList.add(zipcode);
					zipcode = zipcodeInterval;
				}
			}
		}
		mergedZipcodeList.add(zipcode);
		return mergedZipcodeList;
	}
}
