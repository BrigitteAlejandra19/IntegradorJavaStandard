package dnrpa;

import driver.type.Owner;
import exceptions.ChangeOwnerException;
import exceptions.InvalidDataException;
import exceptions.SectionalRegisterException;
import exceptions.VehicleException;
import vehicle.model.Vehicle;
import vehicle.type.*;
import vehicle.use.CarUse;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

	Scanner sc = new Scanner(System.in);

	public Menu() throws InvalidDataException {
	}


	public void showMenu() throws InvalidDataException {
		DNRPA dnrpa = new DNRPA();
		boolean exit = false;
		int op = 0;
		do {
			System.out.println("\n Menu: \n");
			System.out.println("1) Insert many Vehicles");
			System.out.println("2) List all registered Vehicles in all Sectionals Register");
			System.out.println("3) List all Truck Owners (in alphabetical order).");
			System.out.println("4) Change of Owner");
			System.out.println("5) Register a new Vehicle");
			System.out.println("6) Consult by Patent");
			System.out.println("0) Exit");
			System.out.print("Enter option: ");
			op = sc.nextInt();
			switch (op) {
				case 1:
					insertManyVehicles(dnrpa);
					break;
				case 2:
					listAllVehiclesAllSectionalsRegister(dnrpa);
					break;
				case 3:
					listAllTruckOwnersAlphabeticalOrder(dnrpa);
					break;
				case 4:
					changeOfOwner(dnrpa);
					break;
				case 5:
					registerNewVehicle(dnrpa);
					break;
				case 6:
					showCarDetailsByPatent(dnrpa);
					break;
				case 0:
					exit = true;
					System.out.println("Come back when you want a register many cars!");
					break;
			}
		} while (!exit);
	}


	private void insertManyVehicles(DNRPA dnrpa) throws InvalidDataException {

		SectionalRegister sr1 = new SectionalRegister(1);
		SectionalRegister sr2 = new SectionalRegister(2);
		dnrpa.addSeccionalRegister(sr1);
		dnrpa.addSeccionalRegister(sr2);
		Owner o1 = new Owner("ALuis", "10101929", "los flores");
		Owner o2 = new Owner("Ramon", "14072010", "Catia");
		Owner o3 = new Owner("Melquiades", "10091968", "los magallanes");
		Owner o4 = new Owner("Segundo", "01122016", "Catia");
		Owner o5 = new Owner("Lucrecia", "24091965", "san martin");
		Owner o6 = new Owner("Sandra", "10091965", "Avellaneda");
		Owner o7 = new Owner("Victoria", "28081934", "Barlovento");
		Owner o8 = new Owner("Kel", "23101984", "Vicente Lopez");
		Owner o9 = new Owner("Bri", "19061992", "Vicente Lopez");
		sr1.addVehicle(new Truck(CarUse.PARTICULAR, o2, LocalDate.of(2020, 12, 01)));
		sr2.addVehicle(new Truck(CarUse.PROFESSIONAL, o3, LocalDate.of(2021, 01, 13)));
		sr2.addVehicle(new Truck(CarUse.PARTICULAR, o4, LocalDate.of(2019, 12, 21)));
		sr1.addVehicle(new Truck(CarUse.PARTICULAR, o5, LocalDate.of(2018, 10, 10)));
		sr1.addVehicle(new Truck(CarUse.PARTICULAR, o6, LocalDate.of(2017, 03, 21)));
		sr2.addVehicle(new Bus(CarUse.PARTICULAR, o6, LocalDate.of(2016, 06, 13)));
		sr1.addVehicle(new CombustionCar(CarUse.PROFESSIONAL, o6, LocalDate.of(2015, 9, 10)));
		sr2.addVehicle(new CombustionMotorcycle(CarUse.PARTICULAR, o6, LocalDate.of(2014, 8, 28)));
		System.out.println("Many Vehicles have been registered!");
	}


	private void listAllVehiclesAllSectionalsRegister(DNRPA dnrpa) {
		/**
		 * 1)Se desea poder listar todos los autos registrados en todas las seccionales.
		 */

		dnrpa.showAllVehiclesInSectionalRegisters();

	}

	private void listAllTruckOwnersAlphabeticalOrder(DNRPA dnrpa) {
		/**
		 * 2)Se desea poder listar a todos los propietarios (en orden alfabético) de camiones.
		 */
		dnrpa.showTruckOwnerNamesInAlphabeticOrder();
	}

	private void changeOfOwner(DNRPA dnrpa) {
		/**
		 * 3)Los automotores pueden cambiar de propietario.
		 * 4)Se debe registrar la fecha de cambio de propietario.
		 * 6)No se puede cambiar de propietario si pasó menos de 1 año desde la fecha del último cambio de propietario.
		 */

		sc = new Scanner(System.in);
		System.out.println("Enter the Patent: ");
		try {
			String patent = sc.nextLine();
			final Vehicle vehicle = dnrpa.searchVehicleByPatent(patent);
			System.out.println("This is the Vehicle do you want to change of Owner?");
			System.out.println("Patent:" + vehicle.getPatent());
			System.out.println("Vehicle type:" + vehicle.getVehicleType());
			System.out.println("Owner Name:" + vehicle.getOwner().getName());
			System.out.println("Legacy owner register date: " + vehicle.getRegistrationDate().toString());
			System.out.println("Legacy owner date change: " + Optional.ofNullable(vehicle.getChangeDate()).orElse(null));
			System.out.println("Enter : [1] Yes [2] No  ");
			try {
				int save = sc.nextInt();
				Scanner scName = new Scanner(System.in);
				Scanner scDni = new Scanner(System.in);
				Scanner scDirection = new Scanner(System.in);

				switch (save) {
					case 1:
						Owner temporalOwner = addOwner(scName, scDni, scDirection);
						System.out.println("Estos son los datos del nuevo Owner, es correcto?");
						System.out.println("Name:" + temporalOwner.getName());
						System.out.println("Dni: " + temporalOwner.getDni());
						System.out.println("Direction:" + temporalOwner.getDirection());
						System.out.println("Enter : [1] Yes [2] No  ");
						int save2 = sc.nextInt();
						if (save2 == 1) {
							changeOwner(temporalOwner, vehicle);
							System.out.println("The Vehicle has been modifed successfully");
							break;
						} else {
							System.out.println("The Vehicle has not been modify");
							showMenu();

						}
					case 2:
						System.out.println("The Vehicle has not been modify");
						showMenu();
						break;
					default: {
						System.out.println("Please select a opcion between 1 to 2");
						return;
					}
				}
			} catch (InputMismatchException | InvalidDataException e) {
				System.out.println("Debes insertar un número");
				return;
			}
		} catch (VehicleException e) {
			showError(e.getCodeError(), e.getDescriptionError());
		}

	}

	private void registerNewVehicle(DNRPA dnrpa) {


		/**
		 * 5)Se debe poder dar de alta un nuevo automotor. Registrar esa fecha también.
		 */
		//Ask for registration number
		System.out.print("Enter the ID of the Sectional Register where you want to add the new Vehicle");
		System.out.println("");
		dnrpa.showSectionalRegistrer();
		System.out.print("Enter the ID: ");
		Integer sectionalRegisterId = sc.nextInt();
		SectionalRegister temporalSectionalRegister = null;
		try {
			temporalSectionalRegister = dnrpa.getSectionalRegister(sectionalRegisterId);
		} catch (SectionalRegisterException e) {
			showError(e.getCodeError(), e.getDescriptionError());
			return;
		}
		if (temporalSectionalRegister == null) {
			System.out.println("A ocurrido un error");
			return;
		}


		System.out.println("You will add the new vehicle in the Sectional Register Nº: " + sectionalRegisterId);

		Scanner scName = new Scanner(System.in);
		Scanner scDni = new Scanner(System.in);
		Scanner scDirection = new Scanner(System.in);

		//Add owner
		Owner temporalOwner = addOwner(scName, scDni, scDirection);


		//Add vehicle
		System.out.println("Ingrese el uso del automotor:");
		System.out.println("[1] Particular [2] Professional");
		CarUse temporalCarUse;

		try {
			int carUse = sc.nextInt();
			switch (carUse) {
				case 1:
					temporalCarUse = CarUse.PARTICULAR;
					break;
				case 2:
					temporalCarUse = CarUse.PROFESSIONAL;
					break;
				default: {
					System.out.println("Please select a opcion between 1 to 2");
					return;
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Debes insertar un número");
			return;
		}
		if (temporalCarUse == null) {
			System.out.println("A ocurrido un error");
			return;
		}


		System.out.println("Ingrese el numero del tipo de automotor que desea registrar:");
		System.out.println("[1] Bus [2] Combustion Car [3] Combustion Motorcycle [4] Electric Car [5] Electric Motorcycle [6] Truck [7] Utilitary");
		Vehicle temporalVehicle;

		try {
			int type = sc.nextInt();
			switch (type) {
				case 1:
					temporalVehicle = new Bus(temporalCarUse, temporalOwner, LocalDate.now());
					break;
				case 2:
					temporalVehicle = new CombustionCar(temporalCarUse, temporalOwner, LocalDate.now());
					break;
				case 3:
					temporalVehicle = new CombustionMotorcycle(temporalCarUse, temporalOwner, LocalDate.now());
					break;
				case 4:
					temporalVehicle = new ElectricCar(temporalCarUse, temporalOwner, LocalDate.now());
					break;
				case 5:
					temporalVehicle = new ElectricMotorcycle(temporalCarUse, temporalOwner, LocalDate.now());
					break;
				case 6:
					temporalVehicle = new Truck(temporalCarUse, temporalOwner, LocalDate.now());
					break;
				case 7:
					temporalVehicle = new Utilitary(temporalCarUse, temporalOwner, LocalDate.now());
					break;
				default: {
					System.out.println("Please select a option between 1 to 7");
					return;
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Debes insertar un número");
			return;
		}
		if (temporalVehicle == null) {
			System.out.println("A ocurrido un error");
			return;
		}

		// temporalSectionalRegister, temporalOwner, temporalCarUse, temporalVehicle


		System.out.println("Are you sure do you want to register: ");
		System.out.println("In Sectional Register: " + temporalSectionalRegister.getIdSR());
		System.out.println("The Owner: " + temporalOwner);
		System.out.println("The Vehicle: " + temporalVehicle.getVehicleType());
		System.out.println("With the Use: " + temporalCarUse);
		System.out.println("Enter : [1] Yes [2] No  ");
		try {
			int save = sc.nextInt();
			switch (save) {
				case 1:
					temporalSectionalRegister.addVehicle(temporalVehicle);
					System.out.println("The Vehicle has been saved successfully");
					break;
				case 2:
					System.out.println("The Vehicle has not been saved");
					showMenu();
					break;
				default: {
					System.out.println("Please select a opcion between 1 to 2");
					return;
				}
			}
		} catch (InputMismatchException | InvalidDataException e) {
			System.out.println("Debes insertar un número");
			return;
		}


	}

	private Owner addOwner(Scanner scName, Scanner scDni, Scanner scDirection) {
		System.out.println("Enter the Owner information:");

		System.out.println("Enter the Owner name:");
		String name = scName.nextLine();
		System.out.println("Enter the Owner dni:");
		String dniLine = scDni.nextLine();
		System.out.println("Enter the Owner direction:");
		String direction = scDirection.nextLine();
		Owner temporalOwner;
		try {
			temporalOwner = new Owner(name, dniLine, direction);
		} catch (InvalidDataException e) {
			showError(e.getCodeError(), e.getDescriptionError());
			return null;
		}
		if (temporalOwner == null) {
			System.out.println("A ocurrido un error");
			return null;
		}
		return temporalOwner;
	}

	private void showError(String codeError, String descriptionError) {
		System.out.println(codeError);
		System.out.println(descriptionError);
	}


	private void showCarDetailsByPatent(DNRPA dnrpa) {
		sc = new Scanner(System.in);
		System.out.println("Search by Patent");
		System.out.println("Enter the Patent: ");
		String patent = sc.nextLine();
		try {
			final Vehicle vehicle = dnrpa.searchVehicleByPatent(patent);
			final LocalDate oneYearLater = vehicle.getRegistrationDate().plusYears(1);
			boolean wasRegisterOneYearAgo = oneYearLater.isBefore(LocalDate.now());
			boolean wasOwnerChanged = vehicle.getChangeDate() != null;
			if (wasRegisterOneYearAgo || wasOwnerChanged) {
				System.out.println("Patent: " + vehicle.getPatent() + " - Register Date: " + vehicle.getRegistrationDate() + " - Change Date: " + vehicle.getChangeDate());
				return;
			}
			System.out.println("The conditions for consulting the vehicle are not met");


		} catch (VehicleException e) {
			showError(e.getCodeError(), e.getDescriptionError());
		}

	}


	private static void changeOwner(Owner owner, Vehicle vehicle) {
		try {
			// First change of owner
			System.out.println("Legacy owner name: " + vehicle.getOwner().getName());
			System.out.println("Legacy owner register date: " + vehicle.getRegistrationDate().toString());
			System.out.println("Legacy owner date change: " + Optional.ofNullable(vehicle.getChangeDate()).orElse(null));

			vehicle.changeOwner(owner, LocalDate.now());
			System.out.println("Successful change: ");
			System.out.println("New owner name: " + vehicle.getOwner().getName());
			System.out.println("New owner register date: " + vehicle.getRegistrationDate().toString());
			System.out.println("New owner date change: " + Optional.ofNullable(vehicle.getChangeDate()).orElse(null));
		} catch (ChangeOwnerException e) {
			System.out.println("There is a error in owner change: 6");
			System.out.println(e.toString());
		}

	}
}
