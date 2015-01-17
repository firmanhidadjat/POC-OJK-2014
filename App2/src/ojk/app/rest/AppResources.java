package ojk.app.rest;

import id.co.hanoman.LoggerUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import ojk.app.poc2.POC2;
import ojk.app.poc2.client.POC2Client;
import ojk.dao.impl.DaoPOC2Impl;

@Path("/poc2")
public class AppResources {
	static Logger log = LoggerUtil.getLog();
	List<POC2> pocs = new ArrayList<POC2>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListPOC2")
	public List<POC2> getListPOC2() throws Exception {
		return DaoPOC2Impl.read();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("submitPOC2/{TransactionID}/{FirstName}/{LastName}/{Notes}/{Status}/{TransactionValue}/{ApprovedAmount}/{Pin}")
	public int submitPOC(@PathParam("TransactionID") String TransactionID,
			@PathParam("FirstName") String FirstName,
			@PathParam("LastName") String LastName,
			@PathParam("Notes") String Notes,
			@PathParam("Status") String Status,
			@PathParam("TransactionValue") String TransactionValue,
			@PathParam("ApprovedAmount") String ApprovedAmount,
			@PathParam("Pin") String Pin) throws Exception {

		POC2Client p = new POC2Client();
		if (p.sendData("StringUsername", "StringPassword", "update",
				TransactionID, FirstName, LastName, Notes, Status,
				TransactionValue, ApprovedAmount, Pin) == 0) {
			return 0;
		} else {
			log.error("Kemungkinan proses insert ke DB gagal");
			return 1; // return gagal
		}

	}
}
