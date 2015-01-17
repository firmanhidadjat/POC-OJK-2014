package ojk.app.rest;

import id.co.hanoman.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ojk.app.poc7.POC7;
import ojk.app.poc7.client.POC7Client;
import ojk.dao.impl.DaoPOC7Impl;

import org.apache.log4j.Logger;

@Path("/poc7")
public class AppResources {
	static Logger log = LoggerUtil.getLog();
	List<POC7> pocs = new ArrayList<POC7>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListPOC7")
	public List<POC7> getListPOC7() throws Exception {
		return DaoPOC7Impl.read();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("submitPOC7/{IDTransaksi}/{TanggalTransaksi}/{OriginalAmount}/{TaxAmount}")
	public int submitPOC(@PathParam("IDTransaksi") String IDTransaksi,
			@PathParam("TanggalTransaksi") String TanggalTransaksi,
			@PathParam("OriginalAmount") String OriginalAmount,
			@PathParam("TaxAmount") String TaxAmount) throws Exception {

		POC7Client p = new POC7Client();
		if (p.sendData("StringUsername", "StringPassword", "submit",
				IDTransaksi, TanggalTransaksi, OriginalAmount, TaxAmount) == 0) {
			return 0;
		} else {
			log.error("Kemungkinan proses insert ke DB gagal");
			return 1; // return gagal
		}

	}
}
