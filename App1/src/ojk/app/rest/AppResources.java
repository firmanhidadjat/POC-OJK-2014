package ojk.app.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import ojk.app.poc1.POC1;
import ojk.app.poc1.client.POC1Client;
import ojk.dao.impl.DaoPOC1Impl;

@Path("/poc1")
public class AppResources {
	static Logger log = Logger.getRootLogger();
	List<POC1> pocs = new ArrayList<POC1>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListPOC1")
	public List<POC1> getListPOC1() throws Exception {
		return DaoPOC1Impl.read();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("submitPOC1/{IDTransaksi}/{FullName}/{Keterangan}/{Status}/{NilaiTransaksi}/{NilaiDisetujui}/{Pin}")
	public int submitPOC(@PathParam("IDTransaksi") String IDTransaksi,
			@PathParam("FullName") String FullName,
			@PathParam("Keterangan") String Keterangan,
			@PathParam("Status") String Status,
			@PathParam("NilaiTransaksi") String NilaiTransaksi,
			@PathParam("NilaiDisetujui") String NilaiDisetujui,
			@PathParam("Pin") String Pin) throws Exception {

		POC1Client p = new POC1Client();
		
		if (p.sendData("submit", IDTransaksi, FullName, Keterangan, Status,
				NilaiTransaksi, NilaiDisetujui, Pin) == 0) {
			return 0;
		} else {
			log.error("Kemungkinan proses insert ke DB gagal");
			return 1; // return gagal
		}

	}
}
