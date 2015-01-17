package ojk.app.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ojk.app.poc6.POC6;
import ojk.app.poc6.client.POC6Client;
import ojk.dao.impl.DaoPOC6Impl;

import org.apache.log4j.Logger;

@Path("/poc6")
public class AppResources {
	static Logger log = Logger.getRootLogger();
	List<POC6> pocs = new ArrayList<POC6>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListPOC6")
	public List<POC6> getListPOC6() throws Exception {
		return DaoPOC6Impl.read();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("submitPOC6/{IDTransaksi}/{TanggalTransaksi}/{OriginalAmount}/{ApprovedAmount}")
	public int submitPOC(@PathParam("IDTransaksi") String IDTransaksi,
			@PathParam("TanggalTransaksi") String TanggalTransaksi,
			@PathParam("OriginalAmount") String OriginalAmount,
			@PathParam("ApprovedAmount") String ApprovedAmount)
			throws Exception {

		POC6Client p = new POC6Client();

		if (p.sendData("StringUsername", "StringPassword", "submit",
				IDTransaksi, TanggalTransaksi, OriginalAmount, ApprovedAmount) == 0) {
			return 0;
		} else {
			log.error("Kemungkinan proses insert ke DB gagal");
			return 1; // return gagal
		}

	}
}
