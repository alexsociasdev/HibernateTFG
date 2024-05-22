package com.dam.tfg.MotoMammiApplicationASG;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import com.dam.tfg.MotoMammiApplicationASG.services.impl.CustomerServiceImpl;
import com.dam.tfg.MotoMammiApplicationASG.tasks.CustomerFileTask;
import com.dam.tfg.MotoMammiApplicationASG.tasks.VehicleFileTask;
import com.dam.tfg.MotoMammiApplicationASG.tasks.InvoiceFileTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableScheduling
public class MotoMammiApplicationAsgApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MotoMammiApplicationAsgApplication.class, args);
		CustomerService customerService = context.getBean(CustomerServiceImpl.class);
		CustomerFileTask customerFileTask = context.getBean(CustomerFileTask.class);
		VehicleFileTask vehicleFileTask = context.getBean(VehicleFileTask.class);
		InvoiceFileTask invoiceFileTask = context.getBean(InvoiceFileTask.class);

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Seleccione una opción:");
			System.out.println("1. Crear cliente");
			System.out.println("2. Obtener todos los clientes");
			System.out.println("3. Obtener cliente por ID");
			System.out.println("4. Obtener cliente por DNI");
			System.out.println("5. Actualizar cliente");
			System.out.println("6. Eliminar cliente");
			System.out.println("7. Ejecutar carga diaria de clientes");
			System.out.println("8. Ejecutar carga diaria de vehículos");
			System.out.println("9. Ejecutar generación mensual de facturas");
			System.out.println("0. Salir");
			int opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					crearCliente(scanner, customerService);
					break;
				case 2:
					obtenerTodosLosClientes(customerService);
					break;
				case 3:
					obtenerClientePorId(scanner, customerService);
					break;
				case 4:
					obtenerClientePorDni(scanner, customerService);
					break;
				case 5:
					actualizarCliente(scanner, customerService);
					break;
				case 6:
					eliminarCliente(scanner, customerService);
					break;
				case 7:
					ejecutarCargaDiariaClientes(customerFileTask);
					break;
				case 8:
					ejecutarCargaDiariaVehiculos(vehicleFileTask);
					break;
				case 9:
					ejecutarGeneracionMensualFacturas(invoiceFileTask);
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println("Opción no válida");
			}
		}
	}

	private static void crearCliente(Scanner scanner, CustomerService customerService) {
		System.out.println("Ingrese los datos del cliente:");
		System.out.print("Nombre: ");
		String name = scanner.nextLine();
		System.out.print("Apellido: ");
		String firstName = scanner.nextLine();
		System.out.print("Segundo Apellido: ");
		String lastName = scanner.nextLine();
		System.out.print("Fecha de Nacimiento (yyyy-mm-dd): ");
		Date birthDate = Date.valueOf(scanner.nextLine());
		System.out.print("Código Postal: ");
		String postalCode = scanner.nextLine();
		System.out.print("Tipo de Calle: ");
		String streetType = scanner.nextLine();
		System.out.print("Ciudad: ");
		String city = scanner.nextLine();
		System.out.print("Número de Calle: ");
		int numberStreet = scanner.nextInt();
		scanner.nextLine();  // Consumir la nueva línea
		System.out.print("Número de Teléfono: ");
		String phoneNumber = scanner.nextLine();
		System.out.print("DNI: (sin letra)");
		String dni = scanner.nextLine();
		System.out.print("Tipo de Licencia: ");
		String licenceType = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Género: ");
		String gender = scanner.nextLine();

		if (customerService.customerExistsByDni(dni)) {
			System.out.println("Cliente ya registrado con DNI: " + dni);
			return;
		}

		CustomerDTO customer = new CustomerDTO();
		customer.setName(name);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setBirthDate(birthDate);
		customer.setPostalCode(postalCode);
		customer.setStreetType(streetType);
		customer.setCity(city);
		customer.setNumberStreet(numberStreet);
		customer.setPhoneNumber(phoneNumber);
		customer.setDni(dni);
		customer.setLicenceType(licenceType);
		customer.setEmail(email);
		customer.setGender(gender);

		List<VehicleDTO> vehicles = new ArrayList<>();
		System.out.print("¿Desea agregar un vehículo? (s/n): ");
		String respuesta = scanner.nextLine();
		while (respuesta.equalsIgnoreCase("s")) {
			VehicleDTO vehicle = new VehicleDTO();
			vehicle.setDni(dni);
			System.out.print("Placa del Vehículo: ");
			vehicle.setCarPlate(scanner.nextLine());
			System.out.print("Tipo de Vehículo: ");
			vehicle.setVehicleType(scanner.nextLine());
			System.out.print("Marca: ");
			vehicle.setBrand(scanner.nextLine());
			System.out.print("Modelo: ");
			vehicle.setModel(scanner.nextLine());
			vehicles.add(vehicle);
			System.out.print("¿Desea agregar otro vehículo? (s/n): ");
			respuesta = scanner.nextLine();
		}

		customerService.createCustomer(customer, vehicles);
		System.out.println("Cliente creado exitosamente.");
	}

	private static void obtenerTodosLosClientes(CustomerService customerService) {
		List<CustomerDTO> customers = customerService.getAllCustomers();
		customers.forEach(System.out::println);
	}

	private static void obtenerClientePorId(Scanner scanner, CustomerService customerService) {
		System.out.print("Ingrese el ID del cliente: ");
		int id = scanner.nextInt();
		scanner.nextLine();  // Consumir la nueva línea
		CustomerDTO customer = customerService.getCustomerById(id);
		if (customer != null) {
			System.out.println(customer);
		} else {
			System.out.println("Cliente no encontrado.");
		}
	}

	private static void obtenerClientePorDni(Scanner scanner, CustomerService customerService) {
		System.out.print("Ingrese el DNI del cliente: ");
		String dni = scanner.nextLine();
		CustomerDTO customer = customerService.getCustomerByDni(dni);
		if (customer != null) {
			System.out.println(customer);
		} else {
			System.out.println("Cliente no encontrado.");
		}
	}

	private static void actualizarCliente(Scanner scanner, CustomerService customerService) {
		System.out.print("Ingrese el ID del cliente a actualizar: ");
		int id = scanner.nextInt();
		scanner.nextLine();  // Consumir la nueva línea
		CustomerDTO existingCustomer = customerService.getCustomerById(id);
		if (existingCustomer == null) {
			System.out.println("Cliente no encontrado.");
			return;
		}

		System.out.println("Ingrese los nuevos datos del cliente (deje en blanco para mantener el valor actual):");
		System.out.print("Nombre (" + existingCustomer.getName() + "): ");
		String name = scanner.nextLine();
		if (!name.isEmpty()) existingCustomer.setName(name);

		System.out.print("Apellido (" + existingCustomer.getFirstName() + "): ");
		String firstName = scanner.nextLine();
		if (!firstName.isEmpty()) existingCustomer.setFirstName(firstName);

		System.out.print("Segundo Apellido (" + existingCustomer.getLastName() + "): ");
		String lastName = scanner.nextLine();
		if (!lastName.isEmpty()) existingCustomer.setLastName(lastName);

		System.out.print("Fecha de Nacimiento (" + existingCustomer.getBirthDate() + ", yyyy-mm-dd): ");
		String birthDate = scanner.nextLine();
		if (!birthDate.isEmpty()) existingCustomer.setBirthDate(Date.valueOf(birthDate));

		System.out.print("Código Postal (" + existingCustomer.getPostalCode() + "): ");
		String postalCode = scanner.nextLine();
		if (!postalCode.isEmpty()) existingCustomer.setPostalCode(postalCode);

		System.out.print("Tipo de Calle (" + existingCustomer.getStreetType() + "): ");
		String streetType = scanner.nextLine();
		if (!streetType.isEmpty()) existingCustomer.setStreetType(streetType);

		System.out.print("Ciudad (" + existingCustomer.getCity() + "): ");
		String city = scanner.nextLine();
		if (!city.isEmpty()) existingCustomer.setCity(city);

		System.out.print("Número de Calle (" + existingCustomer.getNumberStreet() + "): ");
		String numberStreet = scanner.nextLine();
		if (!numberStreet.isEmpty()) existingCustomer.setNumberStreet(Integer.parseInt(numberStreet));

		System.out.print("Número de Teléfono (" + existingCustomer.getPhoneNumber() + "): ");
		String phoneNumber = scanner.nextLine();
		if (!phoneNumber.isEmpty()) existingCustomer.setPhoneNumber(phoneNumber);

		System.out.print("DNI (" + existingCustomer.getDni() + "): ");
		String dni = scanner.nextLine();
		if (!dni.isEmpty()) existingCustomer.setDni(dni);

		System.out.print("Tipo de Licencia (" + existingCustomer.getLicenceType() + "): ");
		String licenceType = scanner.nextLine();
		if (!licenceType.isEmpty()) existingCustomer.setLicenceType(licenceType);

		System.out.print("Email (" + existingCustomer.getEmail() + "): ");
		String email = scanner.nextLine();
		if (!email.isEmpty()) existingCustomer.setEmail(email);

		System.out.print("Género (" + existingCustomer.getGender() + "): ");
		String gender = scanner.nextLine();
		if (!gender.isEmpty()) existingCustomer.setGender(gender);

		customerService.updateCustomer(id, existingCustomer);
		System.out.println("Cliente actualizado exitosamente.");
	}

	private static void eliminarCliente(Scanner scanner, CustomerService customerService) {
		System.out.print("Ingrese el ID del cliente a eliminar: ");
		int id = scanner.nextInt();
		scanner.nextLine();  // Consumir la nueva línea
		customerService.deleteCustomer(id);
		System.out.println("Cliente eliminado exitosamente.");
	}

	private static void ejecutarCargaDiariaClientes(CustomerFileTask customerFileTask) {
		System.out.println("Ejecutando carga diaria de clientes...");
		customerFileTask.loadCustomerFile();
		System.out.println("Carga diaria de clientes completada.");
	}

	private static void ejecutarCargaDiariaVehiculos(VehicleFileTask vehicleFileTask) {
		System.out.println("Ejecutando carga diaria de vehículos...");
		vehicleFileTask.loadVehicleFile();
		System.out.println("Carga diaria de vehículos completada.");
	}

	private static void ejecutarGeneracionMensualFacturas(InvoiceFileTask invoiceFileTask) {
		System.out.println("Ejecutando generación mensual de facturas...");
		invoiceFileTask.generateMonthlyInvoiceFile();
		System.out.println("Generación mensual de facturas completada.");
	}
}
