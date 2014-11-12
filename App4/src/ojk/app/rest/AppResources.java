package ojk.app.rest;

import id.co.hanoman.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ojk.app.poc5.POC5;
import ojk.app.poc5.client.POC5Client;
import ojk.dao.impl.DaoPOC5Impl;

import org.apache.log4j.Logger;

@Path("/poc5")
public class AppResources {
	static Logger log = LoggerUtil.getLog();
	List<POC5> pocs = new ArrayList<POC5>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListPOC5")
	public List<POC5> getListPOC5() throws Exception {
		return DaoPOC5Impl.read();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("submitPOC5/{IDTransaksi}/{TanggalTransaksi}/{OriginalAmount}/{ApprovedAmount}/{TaxAmount}")
	public int submitPOC(@PathParam("IDTransaksi") String IDTransaksi,
			@PathParam("TanggalTransaksi") String TanggalTransaksi,
			@PathParam("OriginalAmount") String OriginalAmount,
			@PathParam("ApprovedAmount") String ApprovedAmount,
			@PathParam("TaxAmount") String TaxAmount) throws Exception {

		POC5Client p = new POC5Client();
		if (p.sendData("submit", IDTransaksi, TanggalTransaksi, OriginalAmount,
				ApprovedAmount, TaxAmount) == 0) {
			return 0;
		} else {
			log.error("Kemungkinan proses insert ke DB gagal");
			return 1; // return gagal
		}

	}
}
