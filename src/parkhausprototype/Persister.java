package parkhausprototype;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Persister implements PersisterIF {

	private final File file;

	Persister(String filename) {
		this.file = new File(filename);
		try {
			if (filename.contains("/")) {
				//noinspection ResultOfMethodCallIgnored
				file.getParentFile().mkdirs();
			}
			//noinspection ResultOfMethodCallIgnored
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("could not create File" + filename);
		}
	}

	@Override
	public boolean saveToFile(Parkhaus parkhaus) {
		JsonObject root = new JsonObject();
		root.add("size", new JsonPrimitive(parkhaus.plaetze.length));
		root.add("plaetze", convertPlatzArrayToJson(parkhaus));
		root.add("bills", convertBillArrayToJson(parkhaus));
		root.add("umsatz", new JsonPrimitive(parkhaus.getUmsatz()));
		root.add("pricePerSek", new JsonPrimitive(parkhaus.getPricePerSek()));

		return Utils.writeStringToFile(file, root.toString());
	}

	private JsonArray convertBillArrayToJson(Parkhaus parkhaus) {
		JsonArray bills = new JsonArray();

		for (Bill bill : parkhaus.getBills().values()) {
			JsonObject jbill = new JsonObject();
			jbill.add("fahrzeugname", new JsonPrimitive(bill.getFahrzeugName()));
			jbill.add("kosten", new JsonPrimitive(bill.getKosten()));
			jbill.add("zeit", new JsonPrimitive(bill.getZeit()));
			bills.add(jbill);
		}

		return bills;
	}

	private JsonArray convertPlatzArrayToJson(Parkhaus parkhaus) {
		JsonArray plaetze = new JsonArray();
		for (Fahrzeug fahrzeug : parkhaus.plaetze) {
			if (fahrzeug == null) {
				continue;
			}
			JsonObject fz = new JsonObject();
			fz.add("kfz", new JsonPrimitive(fahrzeug.kfz));
			fz.add("begin", new JsonPrimitive(Utils.getDateTimeString(fahrzeug.begin)));
			fz.add("parkNr", new JsonPrimitive(fahrzeug.parkNR));
			plaetze.add(fz);
		}
		return plaetze;
	}

	@Override
	public Parkhaus loadFromFile() {
		JsonParser json = new JsonParser();
		JsonObject jpark = json.parse(Utils.readFileToString(file)).getAsJsonObject();
		Fahrzeug[] plaetze = getFahrzeugArray(jpark.get("size").getAsInt(), jpark.getAsJsonArray("plaetze"));
		HashMap<String, Bill> bills = getBillsArray(jpark.getAsJsonArray("bills"));
		double umsatz = jpark.get("umsatz").getAsDouble();
		double pricePerSek = jpark.get("pricePerSek").getAsDouble();

		return new Parkhaus(plaetze, bills, umsatz, pricePerSek);
	}

	private HashMap<String, Bill> getBillsArray(JsonArray jBills) {
		HashMap<String, Bill> bills = new HashMap<>();
		for (JsonElement j : jBills) {
			JsonObject jBill = j.getAsJsonObject();
			Bill bill = new Bill(jBill.get("fahrzeugname").getAsString(), jBill.get("kosten").getAsDouble(), jBill.get("zeit").getAsDouble());
			bills.put("bill_" + bill.getFahrzeugName(), bill);
		}
		return bills;
	}

	private Fahrzeug[] getFahrzeugArray(int size, JsonArray fahrzeuge) {
		Fahrzeug[] plaetze = new Fahrzeug[size];
		for (JsonElement j : fahrzeuge) {
			JsonObject jfahrzeug = j.getAsJsonObject();
			int parkNr = jfahrzeug.get("parkNr").getAsInt();
			Fahrzeug fahrzeug = new Fahrzeug(jfahrzeug.get("kfz").getAsString(), parkNr, Utils.getDateTimeFromString(jfahrzeug.get("begin").getAsString()));
			plaetze[parkNr] = fahrzeug;
		}
		return plaetze;
	}

}
