# <h1 align="center"> Health Care Appointment System </h1>

<p align="left">

## Overview

The Health Care Appointment System is a Spring Boot-based system designed to facilitate doctor-patient appointments. It offers a set of RESTful API endpoints for booking and managing appointments, user authentication, doctor and patient details.

## Project Structure

The project follows a structured and organized architecture:

- **Entities:** This includes entities such as `Doctor`, `Patient`, `Appointment`, `Receptionist`, `User`, `Role` and `Token` to model the data.
- **Repository:** This includes repository such as `AppointmentRepo`, `PatientRepo`, `DoctorRepo`, `ReceptionistRepo`, `UserRepo` and `TokenRepo` to access the entity data.
- **Services:** logic is implemented in service classes such as `AppointmentService`, `PatientService`, `DoctorService`, `ReceptionistService` and `UserService`.
- **Controller Classes:** These classes define and document RESTful API endpoints includes `AuthController`, `AppointmentController`, `DoctorController`, `PatientController`, `ReceptionistController`and`UserController`.

## User Roles

- **Admin :** Admin can access all user entities
- **Doctor:**
- **Patient:**
- **Receptionist**

## Token Table Description

- `id`: Unique identifier for each token.
- `token`: A unique token for user authentication.
- `tokenType`: stores token type.
- `expired`: token whether token is expired or not in bit type.
- `revoked`: token revoked status is stored in bit type.
- `user`: Many-to-one relationship and a reference to the associated patient for whom the token is issued.

## Auth Controller

- **Create user:** `POST /api/auth/register`
- **Authenticate user:** `POST /api/auth/authenticate`

## Appointment Table Description

- `appointmentId`: Unique identifier for each appointment.
- `time`: Appointment date and time.
- `doctor`: A reference to the associated doctor for the appointment.
- `patient`: It has patient details for the appointment.
- `appointmentStatus`: A reference to the associated patient for the appointment.

## Appointment Controller

- **Book appointment:** `POST /api/appointment/book`
- **Cancel appointment:** `DELETE /api/appointment/cancel/{appointmentId}`
- **Update appointment by appointmentId:** `PUT /api/appointment/updateById/{appointmentId}`
- **Update appointment by doctorId:** `PUT /api/appointment/updateByDoctorId/{doctorId}`
- **Update appointment by patientId:** `PUT /api/appointment/updateByPatientId/{patientId}`
- **View patient appointment:** `GET /appointment/patientAppointment/{patientId}`
- **View doctor appointment:** `GET /appointment/doctorAppointment/{doctorId}`
- **View all appointments:** `GET /appointment/getAll`

## Doctor Table Description

- `doctorId`: Unique identifier for each doctor.
- `doctorFirstName`: First name of the doctor.
- `doctorLastName`: Last name of the doctor.
- `doctorEmail`: Unique email address for the doctor(unique).
- `specialization`: The doctor's specialization(e.g., "Cardiology," "Dermatology")..
- `appointments`: A list of `Appointment` entities representing the appointments associated with the doctor.

## Doctor Controller

- **View doctor:** `GET /doctor/{DoctorId}`
- **View doctor appointments:** `GET /doctor/appointmentDetails/{doctorId}`
- **View all doctors:** `GET /doctor/getAll`
- **Update doctor:** `PUT /doctor/update/{DoctorId}`
- **Remove doctor:** `DELETE /doctor/delete/{DoctorId}`

## Patient Table Description

- `patientId`: Unique identifier for each patient.
- `patientFirstName`: First name of the patient.
- `patientLastName`: Last name of the patient.
- `patientEmail`: Unique email address for the patient(unique).
- `patientContact`: Contact information for the patient.
- `appointment`: A reference to the `Appointment` entity representing the patient's appointment.

## Patient Controller

- **View patient:** `GET /patient/{Id}`
- **View all patients:** `GET /patient/getAll`
- **View patient appointment:** `GET /patient/appointment/{patientId}`
- **Update patient:** `PUT /patient/update/{Id}`
- **Remove patient:** `DELETE /patient/delete/{Id}`

## Receptionist Table Description

- `ReceptionistId`: Unique identifier for each receptionist.
- `ReceptionistFristName`: First name of the receptionist.
- `ReceptionistLastName`: Last name of the receptionist.
- `ReceptionistEmail`: Unique email address for the receptionist.

## Receptionist Controller

- **View receptionist:** `GET /receptionist/get/{receptionistId}`
- **View all receptionists:** `GET /receptionist/getAll`
- **View all appointments:** `GET /receptionist/getAllAppointments`
- **Update receptionist:** `GET /receptionist/update/{receptionistId}`
- **Update appointment status:** `PUT /receptionist/updateAppointmentStatus/{appointmentId}/{status}`

## User Table Description

- `id`: Unique identifier for each user.
- `firstname`: User first name.
- `lastname`: User last name.
- `email`: User email.
- `password`: Encrypted user password.
- `role`: It has roles of users like [PATIENT,ADMIN, DOCTOR, RECEPTIONIST].

## User Controller

- **View user:** `GET /user/{userId}`
- **View all users:** `GET /user/getAll`
- **Remove user:** `DELETE /user/delete/{UserId}`
- **Update user:** `PUT /user/update/{userId}`

## Repositories

- **AppointmentRepo**:

  - Extends `JpaRepository` for the `Appointment` entity. It allows for data access operations related to appointments.

- **TokenRepo**:

  - Extends `JpaRepository` for the `Token` entity. It facilitates data access for authentication tokens.

- **DoctorRepo**:

  - Extends `JpaRepository` for the `Doctor` entity. It enables data access related to doctors.

- **PatientRepo**:

  - Extends `JpaRepository` for the `Patient` entity, allowing data access operations related to patients.

- **ReceptionistRepo**:

  - Extends `JpaRepository` for the `Receptionist` entity, allowing data access operations related to receptionist.

- **UserRepo**:

  - Extends `JpaRepository` for the `User` entity, allowing data access operations related to users.

## ArrayLists

ArrayLists are used in this application to efficiently manage lists of entities.

- **Appointment Entity**:

  - The `Appointment` entity itself doesn't contain an ArrayList, but it's utilized to establish relationships between doctors and patients. The list of appointments for a doctor can be managed using a collection of `Appointment` entities in the `Doctor` entity.

- **Token Entity**:

  - There isn't an ArrayList in the `AuthenticationToken` entity, but it's associated with a user using a reference. The token and associated data are stored as individual records in the database.

- **Doctor Entity**:

  - `appointments`: An ArrayList to manage the list of `Appointment` entities associated with a doctor. It allows for easy retrieval and manipulation of a doctor's appointments.

- **Patient Entity**:

  - There isn't a direct ArrayList in the `Patient` entity, but the patient's appointments are managed using a reference to the `Appointment` entity.

## configuration for MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/helathcareappointment
spring.datasource.username=root
spring.datasource.password=`your password`
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

```
