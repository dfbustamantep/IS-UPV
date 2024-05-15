
package persistence;

import java.util.List;

import domain.*;


public class Main {
	
	private static DataAccessInterface dataAccess;

	public static void main(String[] args) {
		dataAccess = new DataAccess();
		

		List<City> l0=(List<City>) dataAccess.hugeCities(2000000);
		System.out.println(l0);
		
		List<Country> l1=(List<Country>) dataAccess.countriesWithHugeCapitals1(2000000);
		System.out.println(l1);

		List<Country> l2=(List<Country>) dataAccess.countriesWithHugeCapitals2(2000000);
		System.out.println(l2);

		List<Country> l3=(List<Country>) dataAccess.countriesWithHugeCapitals3(2000000);
		System.out.println(l3);

		List<Country> l4=(List<Country>) dataAccess.countriesWithHugeCapitals4(2000000);
		System.out.println(l4);
	
	}

}

