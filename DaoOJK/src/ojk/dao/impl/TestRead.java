package ojk.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ojk.app.poc1.POC1;
import ojk.app.poc2.POC2;
import ojk.app.poc3.POC3;
import ojk.app.poc4.POC4;
import ojk.app.poc5.POC5;
import ojk.app.poc6.POC6;
import ojk.app.poc7.POC7;

public class TestRead {
	public static void main(String[] ars) throws Exception {

		for (int i = 0; i < 1; i++) {
			List<POC1> lp1 = new ArrayList<POC1>();
			List<POC2> lp2 = new ArrayList<POC2>();
			List<POC3> lp3 = new ArrayList<POC3>();
			List<POC4> lp4 = new ArrayList<POC4>();
			List<POC5> lp5 = new ArrayList<POC5>();
			List<POC6> lp6 = new ArrayList<POC6>();
			List<POC7> lp7 = new ArrayList<POC7>();

			lp1 = DaoPOC1Impl.read();
			lp2 = DaoPOC2Impl.read();
			lp3 = DaoPOC3Impl.read();
			lp4 = DaoPOC4Impl.read();
			lp5 = DaoPOC5Impl.read();
			lp6 = DaoPOC6Impl.read();
			lp7 = DaoPOC7Impl.read();

			for (Iterator iterator = lp1.iterator(); iterator.hasNext();) {
				POC1 poc1 = (POC1) iterator.next();
				System.out.println("POC1 ==================");
				
				System.out.println(poc1.getIDTransaksi());
				System.out.println(poc1.getKeterangan());
				System.out.println(poc1.getNilaiDisetujui());
				System.out.println(poc1.getNilaiTransaksi());
				System.out.println(poc1.getPin());
				System.out.println(poc1.getStatus());
			}

			for (Iterator iterator = lp2.iterator(); iterator.hasNext();) {
				POC2 poc2 = (POC2) iterator.next();
				
				System.out.println("POC2 ==================");
				System.out.println(poc2.getTransactionID());
				System.out.println(poc2.getStatus());
				System.out.println(poc2.getNotes());
				System.out.println(poc2.getFirstName());
				System.out.println(poc2.getLastName());
				System.out.println(poc2.getTransactionValue());
			}

			for (Iterator iterator = lp3.iterator(); iterator.hasNext();) {
				POC3 poc3 = (POC3) iterator.next();
				
				System.out.println("POC3 ==================");
				System.out.println(poc3.getIDTransaksi());
				System.out.println(poc3.getKeterangan());
				System.out.println(poc3.getNilaiDisetujui());
				System.out.println(poc3.getNilaiTransaksi());
				System.out.println(poc3.getPin());
				System.out.println(poc3.getStatus());
			}

			for (Iterator iterator = lp4.iterator(); iterator.hasNext();) {
				POC4 poc4 = (POC4) iterator.next();
				
				
				System.out.println("POC4 ==================");
				System.out.println(poc4.getIDTransaksi());
				System.out.println(poc4.getTanggalTransaksi());
			}

			for (Iterator iterator = lp5.iterator(); iterator.hasNext();) {
				POC5 poc5 = (POC5) iterator.next();
				
				System.out.println("POC5 ==================");
				System.out.println(poc5.getIDTransaksi());
				System.out.println(poc5.getOriginalAmount());
				System.out.println(poc5.getTanggalTransaksi());
				System.out.println(poc5.getTaxAmount());
			}

			for (Iterator iterator = lp6.iterator(); iterator.hasNext();) {
				POC6 poc6 = (POC6) iterator.next();
				
				System.out.println("POC6 ==================");
				System.out.println(poc6.getIDTransaksi());
				System.out.println(poc6.getApprovedAmount());
				System.out.println(poc6.getOriginalAmount());
				System.out.println(poc6.getTanggalTransaksi());
			}

			for (Iterator iterator = lp7.iterator(); iterator.hasNext();) {
				POC7 poc7 = (POC7) iterator.next();
				
				System.out.println("POC7 ==================");
				System.out.println(poc7.getIDTransaksi());
				System.out.println(poc7.getOriginalAmount());
				System.out.println(poc7.getTanggalTransaksi());
				System.out.println(poc7.getTaxAmount());
			}

			// lp = DaoPOC1Impl.read();
			// for (Iterator<POC1> iterator = lp.iterator();
			// iterator.hasNext();) {
			// POC1 poc1 = (POC1) iterator.next();
			// System.out.println(poc1.getIDTransaksi() + "  "
			// + poc1.getKeterangan() + "  " + poc1.getStatus() + "  "
			// + poc1.getNilaiDisetujui() + "  "
			// + poc1.getNilaiTransaksi() + "  " + poc1.getPin());
			// }

			if (i % 1000 == 0) {
				System.out.println(i);
			}
		}

	}
}
