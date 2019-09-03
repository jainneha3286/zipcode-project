package com.nk.zipcode.merger;

import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws ZipCodeValidationException {
		Scanner scan = new Scanner(System.in);
		String zipcodeInputRanges = scan.nextLine();

		ZipCodeRangeMerger zipCodeRangeMerger = new ZipCodeRangeMerger(zipcodeInputRanges);
		List<ZipCode> mergedZipCodeList = zipCodeRangeMerger.mergeZipcode(zipcodeInputRanges);

		for (ZipCode zipcode : mergedZipCodeList) {
			System.out.println("[" + zipcode.getLowerBound() + "," + zipcode.getUpperBound() + "]");
		}
		scan.close();
	}

}
