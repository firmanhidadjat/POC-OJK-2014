package ojk.app.rest;

import id.co.hanoman.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ojk.app.poc3.POC3;
import ojk.app.poc3.client.POC3Client;
import ojk.dao.impl.DaoPOC3Impl;

import org.apache.log4j.Logger;

@Path("/poc3")
public class AppResources {
	static Logger log = LoggerUtil.getLog();
	List<POC3> pocs = new ArrayList<POC3>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListPOC3")
	public List<POC3> getListPOC3() throws Exception {
		return DaoPOC3Impl.read();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("submitPOC3/{IDTransaksi}/{Keterangan}/{Status}/{NilaiTransaksi}/{NilaiDisetujui}/{Pin}")
	public int submitPOC(@PathParam("IDTransaksi") String IDTransaksi,
			@PathParam("Keterangan") String Keterangan,
			@PathParam("Status") String Status,
			@PathParam("NilaiTransaksi") String NilaiTransaksi,
			@PathParam("NilaiDisetujui") String NilaiDisetujui,
			@PathParam("Pin") String Pin) throws Exception {
		try {
			POC3Client p = new POC3Client();
			if (p.sendData("submit", IDTransaksi, Keterangan, Status, NilaiTransaksi,
					NilaiDisetujui, Pin) == 0) {
				return 0;
			} else {
				log.error("Kemungkinan proses insert ke DB gagal");
				return 1; // return gagal
			}

		} catch (Exception e) {
			log.error("sendData error");
			return 1; // return gagal
		}
	}
}
