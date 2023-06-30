package org.moisiadis.stattrackercore.database;

import org.moisiadis.stattrackercore.CC;
import org.moisiadis.stattrackercore.model.Driver;
import org.moisiadis.stattrackercore.model.DriverResult;
import org.moisiadis.stattrackercore.model.DriverStats;
import org.moisiadis.stattrackercore.model.GrandPrix;
import org.moisiadis.stattrackercore.model.Race;
import org.moisiadis.stattrackercore.model.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MarioKartDB extends DB {

	public MarioKartDB(String path) throws SQLException {
		super(path);
	}

	public GrandPrix createGrandPrix(String grandPrixName) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Grand Prix Names\" (Name) VALUES (?)");
		statement.setString(1, grandPrixName);
		statement.execute();

		Optional<ResultSet> result = runQuery("SELECT ID FROM \"Grand Prix Names\" WHERE rowid = last_insert_rowid();");
		if (result.isEmpty()) {
			throw new SQLException("Could not create Grand Prix");
		}

		ResultSet resultSet = result.get();

		return new GrandPrix(resultSet.getInt("ID"), grandPrixName);
	}

	public List<GrandPrix> createGrandPrix(String... grandPrixNames) throws SQLException {
		List<GrandPrix> grandPrixList = new LinkedList<>();

		for (String name : grandPrixNames) {
			grandPrixList.add(createGrandPrix(name));
		}

		return grandPrixList;
	}

	public void deleteGrandPrix(String grandPrixName) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Grand Prix Names\" WHERE Name = ?");
		statement.setString(1, grandPrixName);
		statement.execute();
	}

	public void deleteGrandPrix(int grandPrixId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Grand Prix Names\" WHERE ID = ?");
		statement.setInt(1, grandPrixId);
		statement.execute();
	}

	public void deleteGrandPrix(String... grandPrixNames) throws SQLException {
		for (String name : grandPrixNames) {
			deleteGrandPrix(name);
		}
	}

	public void deleteGrandPrix(int... grandPrixIds) throws SQLException {
		for (int id : grandPrixIds) {
			deleteGrandPrix(id);
		}
	}

	public GrandPrix getGrandPrix(String name) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT ID FROM \"Grand Prix Names\" WHERE Name = ?");
		statement.setString(1, name);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet == null) {
			throw new SQLException("Could not find Grand Prix");
		}

		if (!resultSet.next()) {
			throw new SQLException("Could not find Grand Prix");
		}

		return new GrandPrix(resultSet.getInt("ID"), name);
	}

	public GrandPrix getGrandPrix(int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Name FROM \"Grand Prix Names\" WHERE ID = ?");
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet == null) {
			throw new SQLException("Could not find Grand Prix");
		}

		if (!resultSet.next()) {
			throw new SQLException("Could not find Grand Prix");
		}

		return new GrandPrix(id, resultSet.getString("Name"));
	}

	public Race createRace(int grandPrixId, Instant date, String cc) throws SQLException {
		PreparedStatement raceStatement = connection.prepareStatement("INSERT INTO Races (\"Grand Prix ID\", Date, CC) VALUES (?, ?, ?)");
		raceStatement.setInt(1, grandPrixId);
		raceStatement.setLong(2, date.toEpochMilli());
		raceStatement.setString(3, cc);

		raceStatement.execute();

		Optional<ResultSet> result = runQuery("SELECT ID FROM Races WHERE rowid = last_insert_rowid();");
		if (result.isEmpty()) {
			throw new SQLException("Could not create race");
		}

		ResultSet raceIdResultSet = result.get();

		if (!raceIdResultSet.next()) {
			throw new SQLException("Could not create race");
		}

		return new Race(raceIdResultSet.getInt("ID"), getGrandPrix(grandPrixId).getName(), date.toString(), cc, null);
	}

	public void deleteRace(int raceId) throws SQLException {
		PreparedStatement deleteRaceStatement = connection.prepareStatement("DELETE FROM Races WHERE ID = ?");
		deleteRaceStatement.setInt(1, raceId);
		deleteRaceStatement.execute();
	}

	public void deleteRaces(int... raceIds) throws SQLException {
		for (int raceId : raceIds) {
			deleteRace(raceId);
		}
	}

	public Result createResult(int raceId, int driverId, int position, int points) throws SQLException {
		PreparedStatement raceStatement = connection.prepareStatement("INSERT INTO Results (\"Race ID\", \"Driver ID\", Position, Points) VALUES (?, ?, ?, ?)");
		raceStatement.setInt(1, raceId);
		raceStatement.setInt(2, driverId);
		raceStatement.setInt(3, position);
		raceStatement.setInt(4, points);

		raceStatement.execute();

		Optional<ResultSet> result = runQuery("SELECT ID FROM Results WHERE rowid = last_insert_rowid();");
		if (result.isEmpty()) {
			throw new SQLException("Could not create result");
		}

		ResultSet raceIdResultSet = result.get();

		if (!raceIdResultSet.next()) {
			throw new SQLException("Could not create result");
		}

		return new Result(raceIdResultSet.getInt("ID"), getDriver(driverId).getName(), position, points);
	}

	public void deleteResult(int resultId) throws SQLException {
		PreparedStatement deleteResultStatement = connection.prepareStatement("DELETE FROM Results WHERE ID = ?");
		deleteResultStatement.setInt(1, resultId);
		deleteResultStatement.execute();
	}

	public void deleteResults(int... resultIds) throws SQLException {
		for (int resultId : resultIds) {
			deleteResult(resultId);
		}
	}

	public Result getResult(int resultId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM Results WHERE ID = ?");
		statement.setInt(1, resultId);

		ResultSet resultSet = statement.executeQuery();

		int driverId = resultSet.getInt("Driver ID");
		String driver = getDriver(driverId).getName();
		int position = resultSet.getInt("Position");
		int points = resultSet.getInt("Points");

		return new Result(resultId, driver, position, points);
	}

	public Race getRaceResults(int raceId) throws SQLException {
		PreparedStatement raceStatement = connection.prepareStatement("SELECT  \"Grand Prix Names\".Name, Date, CC FROM Races INNER JOIN \"Grand Prix Names\" on \"Grand Prix Names\".ID = Races.\"Grand Prix ID\"WHERE Races.ID = ?;");
		raceStatement.setInt(1, raceId);
		ResultSet raceResultSet = raceStatement.executeQuery();
		String grandPrixName = raceResultSet.getString("Name");
		long date = raceResultSet.getLong("Date");
		String cc = raceResultSet.getString("CC");

		PreparedStatement resultStatement = connection.prepareStatement("SELECT Results.ID, Drivers.Name, Position, Points FROM Results INNER JOIN \"Drivers\" ON Results.\"Driver ID\" = Drivers.ID WHERE \"Race ID\" = ? ORDER BY Position ASC;");
		resultStatement.setInt(1, raceId);

		ResultSet resultSet = resultStatement.executeQuery();

		List<Result> results = new LinkedList<>();

		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			String driverName = resultSet.getString("Name");
			int position = resultSet.getInt("Position");
			int points = resultSet.getInt("Points");
			results.add(new Result(id, driverName, position, points));
		}

		return new Race(raceId, grandPrixName, Instant.ofEpochMilli(date).toString(), cc, results);
	}

	public Driver createDriver(String driverName) throws SQLException {
		PreparedStatement driverStatement = connection.prepareStatement("INSERT INTO Drivers (Name) VALUES (?)");
		driverStatement.setString(1, driverName);
		driverStatement.execute();

		Optional<ResultSet> result = runQuery("SELECT ID FROM Drivers WHERE rowid = last_insert_rowid();");
		if (result.isEmpty()) {
			throw new SQLException("Could not create driver");
		}
		ResultSet driverIdResultSet = result.get();

		return new Driver(driverIdResultSet.getInt("ID"), driverName);
	}

	public void deleteDriver(int driverId) throws SQLException {
		PreparedStatement deleteDriverStatement = connection.prepareStatement("DELETE FROM Drivers WHERE ID = ?");
		deleteDriverStatement.setInt(1, driverId);
		deleteDriverStatement.execute();
	}

	public void deleteDriver(String driverName) throws SQLException {
		PreparedStatement deleteDriverStatement = connection.prepareStatement("DELETE FROM Drivers WHERE Name = ?");
		deleteDriverStatement.setString(1, driverName);
		deleteDriverStatement.execute();
	}

	public void deleteDrivers(int... driverIds) throws SQLException {
		for (int driverId : driverIds) {
			deleteDriver(driverId);
		}
	}

	public void deleteDrivers(String... driverNames) throws SQLException {
		for (String driverName : driverNames) {
			deleteDriver(driverName);
		}
	}

	public Driver getDriver(String driverName) throws SQLException {
		PreparedStatement driverStatement = connection.prepareStatement("SELECT ID FROM Drivers WHERE Name = ?");
		driverStatement.setString(1, driverName);
		ResultSet driverIdResultSet = driverStatement.executeQuery();
		if (!driverIdResultSet.next()) {
			throw new SQLException("Could not find driver");
		}

		return new Driver(driverIdResultSet.getInt("ID"), driverName);
	}

	public Driver getDriver(int driverId) throws SQLException {
		PreparedStatement driverStatement = connection.prepareStatement("SELECT Name FROM Drivers WHERE ID = ?");
		driverStatement.setInt(1, driverId);
		ResultSet driverIdResultSet = driverStatement.executeQuery();
		if (!driverIdResultSet.next()) {
			throw new SQLException("Could not find driver");
		}

		return new Driver(driverId, driverIdResultSet.getString("Name"));
	}

	public Race getRaceNoResults(int raceId) throws SQLException {
		PreparedStatement raceStatement = connection.prepareStatement("SELECT  \"Grand Prix Names\".Name, Date, CC FROM Races INNER JOIN \"Grand Prix Names\" on \"Grand Prix Names\".ID = Races.\"Grand Prix ID\"WHERE Races.ID = ?;");
		raceStatement.setInt(1, raceId);
		ResultSet raceResultSet = raceStatement.executeQuery();
		String grandPrixName = raceResultSet.getString("Name");
		Instant date = Instant.ofEpochMilli(raceResultSet.getLong("Date"));
		String cc = raceResultSet.getString("CC");

		return new Race(raceId, grandPrixName, date.toString(), cc, null);
	}

	public List<Race> getAllRacesWithResults() throws SQLException {
		List<Race> raceList = new LinkedList<>();

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM Races");
		ResultSet resultSet = statement.executeQuery();

		while(resultSet.next()) {
			int raceId = resultSet.getInt("ID");
			raceList.add(getRaceResults(raceId));
		}

		return raceList;
	}

	public List<Race> getAllRaces() throws SQLException {
		List<Race> raceList = new LinkedList<>();

		PreparedStatement allRacesStatement = connection.prepareStatement("SELECT * FROM Races");
		ResultSet allRacesResultSet = allRacesStatement.executeQuery();

		while (allRacesResultSet.next()) {
			int raceId = allRacesResultSet.getInt("ID");
			raceList.add(getRaceNoResults(raceId));
		}

		return raceList;
	}

	public List<DriverResult> getAllRacesOfDriver(int driverId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Races.Date, \"Grand Prix Names\".Name, Races.CC, Position, Points\n" +
				"FROM Results\n" +
				"INNER JOIN Races on Results.\"Race ID\" = Races.ID\n" +
				"INNER JOIN \"Grand Prix Names\" on Races.\"Grand Prix ID\"= \"Grand Prix Names\".ID\n" +
				"WHERE \"Driver ID\" = ? ORDER BY Date DESC;");
		statement.setInt(1, driverId);
		ResultSet resultSet = statement.executeQuery();

		List<DriverResult> driverResults = new LinkedList<>();

		while (resultSet.next()) {
			Instant date = Instant.ofEpochMilli(resultSet.getLong("Date"));
			String grandPrixName = resultSet.getString("Name");
			String cc = resultSet.getString("CC");
			int position = resultSet.getInt("Position");
			int points = resultSet.getInt("Points");
			driverResults.add(new DriverResult(date, grandPrixName, cc, position, points));
		}

		return driverResults;
	}

	public DriverStats getDiverStats(int driverId) throws SQLException {
		List<DriverResult> driverResults = getAllRacesOfDriver(driverId);

		Driver driver = getDriver(driverId);
		double averagePoints;
		double averagePosition = 0;
		int totalPoints = 0;
		int numSweeps = 0;
		double last10AveragePoints;
		double last10AveragePosition = 0;
		int last10Points = 0;
		int last10NumSweeps = 0;

		int counter = 0;

		for (DriverResult driverResult : driverResults) {
			if (driverResult.getPoints() == 60) {
				numSweeps++;
			}
			totalPoints += driverResult.getPoints();
			averagePosition += driverResult.getPosition();

			if (counter < 10) {
				last10Points += driverResult.getPoints();
				last10AveragePosition += driverResult.getPosition();
				if (driverResult.getPoints() == 60) {
					last10NumSweeps++;
				}
			}

			counter++;
		}

		averagePoints = (double) totalPoints / driverResults.size();
		averagePosition /= driverResults.size();

		last10AveragePoints = (double) last10Points / 10;
		last10AveragePosition /= 10;

		return new DriverStats(driver, averagePoints, averagePosition, totalPoints, numSweeps, last10AveragePoints, last10AveragePosition, last10Points, last10NumSweeps);
	}
}
