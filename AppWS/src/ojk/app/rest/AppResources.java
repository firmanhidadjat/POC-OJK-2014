package ojk.app.rest;

import id.co.hanoman.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ojk.app.poc4.POC4;
import ojk.app.poc4.client.POC4Client;
import ojk.dao.impl.DaoPOC4Impl;

import org.apache.log4j.Logger;

@Path("/poc4")
public class AppResources {
	static Logger log = LoggerUtil.getLog();
	List<POC4> pocs = new ArrayList<POC4>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListPOC4")
	public List<POC4> getListPOC4() throws Exception {
		return DaoPOC4Impl.read();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("submitPOC4/{IDTransaksi}/{TanggalTransaksi}")
	public int submitPOC(@PathParam("IDTransaksi") String IDTransaksi,
			@PathParam("TanggalTransaksi") String TanggalTransaksi)
			throws Exception {

		POC4Client p = new POC4Client();

		if (p.sendData("submit", IDTransaksi, TanggalTransaksi) == 0) {
			return 0;
		} else {
			log.error("Kemungkinan proses insert ke DB gagal");
			return 1; // return gagal
		}

	}
}
