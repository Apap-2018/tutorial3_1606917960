package service;

import java.util.List;

import model.PilotModel;

public interface PilotService {
	void addPilot (PilotModel pilot);
	List<PilotModel> getPilotList();
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	void updateFlyHourPilotByLicenseNumber(String licenseNumber, int flyHour);
	PilotModel deletePilotDetailById(String id);
}
