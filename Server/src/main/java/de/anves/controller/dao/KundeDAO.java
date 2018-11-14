package de.anves.controller.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.anves.Kunde;
import de.anves.controller.db.DBController;

public class KundeDAO implements CRUDInterface<Kunde>{

	DBController db = DBController.getInstance();

	Date date;
	@Override
	public Kunde create(Kunde value) {
		List<Kunde> result;




		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try{
			date = simpleDateFormat.parse(value.getGeburtsdatum());
			System.out.println(date);

		}catch(ParseException e){
			e.printStackTrace();
		}


		String createsql = "INSERT INTO kunde(kundeid, nachname, vorname, Gebdat, Bankverbindung, PLZ, ort, Strasse, Hausnummer) VALUES ("
				+ "'"+ value.getId()+ "',"
					+ "'"+ value.getNachname()+ "',"
						+ "'"+ value.getVorname()+ "',"
							+ "'"+ date.getTime()+ "',"
								+ "'"+ value.getBankverbindung()+ "',"
									+ "'"+ value.getPlz()+ "',"
										+ "'"+ value.getOrt()+ "',"
											+ "'"+ value.getStrasse()+ "',"						
				+ ""+ value.getHausnummer() +")";
		
		String selectsql = "SELECT * FROM user WHERE kundeid = max(kundeid)";
		
		db.connect();
		try {
			db.executeUpdate(createsql);
			ResultSet rs = db.executeQuery(selectsql);
			
			result = convertRsToKunde(rs);
			
			return result.get(0);
		} catch (SQLException e) {
			System.err.println("Kunde: CreateKunde: Fehler");
			e.printStackTrace();
		} finally {
			try {
				db.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Kunde read(long id) {
	String readsql = "SELECT * FROM kunde WHERE kundeid = '" + id + "'";
		
		db.connect();
		try {
			ResultSet rs = db.executeQuery(readsql);
			
			Kunde result = convertRsToKunde(rs).get(0);
			
			return result;
		} catch (SQLException e) {
			System.err.println("KundeDao: Read: Fehler");
			e.printStackTrace();
		} finally {
			try {
				db.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Kunde update(Kunde value) {
		
		String updatesql = "UPDATE user SET "
				+ "id=" + value.getId() + ","
				
				+ "nachname="+ value.getNachname()+ "',"
					+ "Vorname="+ value.getVorname()+ "',"
						+ "Gebdat="+ date.getTime()+ "',"
							+ "Bankverbindung="+ value.getBankverbindung()+ "',"
								+ "Plz="+ value.getPlz()+ "',"
									+ "Ort="+ value.getOrt()+ "',"
										+ "Strasse="+ value.getStrasse()+ "',"
											
			+ "Hausnummer="+ value.getHausnummer() +")";
		
		String selectsql = "SELECT * FROM kunde WHERE kundeid = " + value.getId();
		
		db.connect();
		try {
			db.executeUpdate(updatesql);
			ResultSet rs = db.executeQuery(selectsql);
			
			List<Kunde> result = convertRsToKunde(rs);
			
			return result.get(0);
		} catch (SQLException e) {
			System.err.println("Kunde: upodateKunde: Fehler");
			e.printStackTrace();
		} finally {
			try {
				db.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean delete(Kunde value) {
       String deletesql = "DELETE FROM kunde WHERE kundeid=" + value.getId();
		
		db.connect();
		try {
			if (db.executeUpdate(deletesql) == 0)
			{
				return true;
			}
			
		} catch (SQLException e) {
			System.err.println("UserDao:DELETE FROM kunde: Fehler");
			e.printStackTrace();
		} finally {
			try {
				db.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	private List<Kunde> convertRsToKunde(ResultSet rs) throws SQLException {
		List<Kunde> result = new ArrayList<Kunde>();
		
		while(rs.next()) {
			Kunde kunde = new Kunde();
			kunde.setId(rs.getLong("kundeid"));
			kunde.setNachname(rs.getString("nachname"));
			kunde.setVorname(rs.getString("vorname"));
			kunde.setGeburtsdatum( Long.toString(rs.getLong("Gebdat")) );
			kunde.setBankverbindung(rs.getString("Bankverbindung"));
			kunde.setPlz(rs.getString("Plz"));
			kunde.setOrt(rs.getString("Ort"));
			kunde.setStrasse(rs.getString("Strasse"));
			kunde.setHausnummer(rs.getString("Hausnummer"));
			
			
			
			result.add(kunde);
		}
		
		return result;
	}

	
}
