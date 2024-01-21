# <h1 align="center"> Health Care Appointment System </h1>

<p align="left">

## Overview

The Health Care Appointment System is a Spring Boot-based system designed to facilitate doctor-patient appointments. It offers a set of RESTful API endpoints for booking and managing appointments, user authentication, doctor and patient details .

## Project Structure

The project follows a structured and organized architecture:

- **Entities:** This includes entities such as `Doctor`, `Patient`, `Appointment`, `Receptionist`, `User`, `Role` and `Token` to model the data.
- **Repository:** This includes repository such as `AppointmentRepo`, `PatientRepo`, `DoctorRepo`, `ReceptionistRepo`, `UserRepo` and `TokenRepo` to access the entity data.
- **Services:** logic is implemented in service classes such as `AppointmentService`, `PatientService`, `DoctorService`, `ReceptionistService` and `UserService`.
- **Controller Classes:** These classes define and document RESTful API endpoints includes `DoctorController`, `PatientController`, `ReceptionistController` and `UserController`.

## Auth Controller

- **Create User:** `POST /api/auth/register`
- **Authenticate User:** `POST /api/auth/authenticate`

## Patient Controller

- **View Patient:** `GET /patient/{Id}`
- **Update Patient:** `PUT /patient/update/{Id}`
- **View All Patient:** `GET /patient/getAll`
- **Remove Patient:** `DELETE /patient/delete/{Id}`

### Appointment Controller

- **Book Appointment:** `POST /api/appointment/book`
- **Cancel Appointment:** `DELETE /api/appointment/cancel/{appointmentId}`
- **Update Appointment By AppointmentId:** `PUT /api/appointment/updateById/{appointmentId}`
- **Update Appointment By DoctorId:** `PUT /api/appointment/updateByDoctorId/{doctorId}`
- **Update Appointment By PatientId:** `PUT /api/appointment/updateByPatientId/{patientId}`
- **View Patient Appointment:** `GET /appointment/patientAppointment/{patientId}`
- **View Doctor Appointment:** `GET /appointment/doctorAppointment/{doctorId}`
- **View All Appointment:** `GET /appointment/getAll`

### Doctor Controller

- **View Doctor:** `GET /doctor/{DoctorId}`
- **Update Doctor:** `PUT /doctor/update/{DoctorId}`
- **Remove Doctor:** `DELETE /doctor/delete/{DoctorId}`
- **View Doctor Appointments:** `GET /doctor/appointmentDetails/{doctorId}`
- **Get All Doctor:** `GET /doctor/getAll`

### Receptionist Controller

- **Update Appointment Status:** `PUT /receptionist/updateAppointmentStatus/{appointmentId}/{status}`

### User Controller

- **View User:** `GET /user/{userId}`
- **View All User:** `GET /user/getAll`
- **Remove User:** `DELETE /user/delete/{UserId}`

### Doctor Table Description

- `doctorId`: Unique identifier for each doctor.
- `doctorName`: Name of the doctor.
- `specialization`: The doctor's specialization.
- `appointments`: A list of appointments associated with the doctor.

### Patient Table Description

- `patientId`: Unique identifier for each patient.
- `patientFirstName`: First name of the patient.
- `patientLastName`: Last name of the patient.
- `patientEmail`: Unique email address for the patient.
- `patientContact`: Contact information for the patient.
- `appointment`: The patient's appointment.

### Receptionist Table Description

- `ReceptionistId`: Unique identifier for each receptionist.
- `ReceptionistFristName`: First name of the receptionist.
- `ReceptionistLastName`: Last name of the receptionist.
- `ReceptionistEmail`: Unique email address for the receptionist.

### Appointment Table Description

- `appointmentId`: Unique identifier for each appointment.
- `time`: Appointment date and time.
- `doctor`: It has doctor details for the appointment.
- `patient`: It has patient details for the appointment.
- `appointmentStatus`: Appointment is completed or not.

### Token Table Description

- `id`: Unique identifier for each token.
- `token`: A unique token for user authentication.
- `tokenType`: stores token type.
- `expired`: token whether token is expired or not in bit type.
- `revoked`: token revoked status is stored in bit type.
- `user`: Many-to-one relationship with the patient contains user details.

### User Table Description

- `id`: Unique identifier for each user.
- `firstname`: User first name.
- `lastname`: User last name.
- `email`: User email.
- `password`: Encrypted user password.
- `role`: It has roles of users like [PATIENT,ADMIN, DOCTOR, RECEPTIONIST].

### Detailed Data Structures

1. **Entities**:

   - **Doctor Entity**:

     - `doctorId`: Unique identifier for each doctor.
     - `doctorName`: Name of the doctor.
     - `specialization`: Specialization of the doctor (e.g., "Cardiology," "Dermatology").
     - `appointments`: A list of `Appointment` entities representing the appointments associated with the doctor.

   - **Patient Entity**:

     - `patientId`: Unique identifier for each patient.
     - `patientFirstName`: First name of the patient.
     - `patientLastName`: Last name of the patient.
     - `patientEmail`: Email address of the patient (unique).
     - `patientPassword`: Password of the patient (hashed and securely stored).
     - `patientContact`: Contact information of the patient.
     - `appointment`: A reference to the `Appointment` entity representing the patient's appointment.

   - **Appointment Entity**:

     - `id`: An embedded primary key that includes `appointmentId` (unique identifier) and `time` (appointment time).
     - `doctor`: A reference to the associated doctor for the appointment.
     - `patient`: A reference to the associated patient for the appointment.

   - **AuthenticationToken Entity**:
     - `tokenId`: Unique identifier for each authentication token.
     - `token`: A unique token generated for user authentication.
     - `tokenCreationDate`: Date when the token was created.
     - `patient`: A reference to the associated patient for whom the token is issued.

2. **Repositories**:

   - **IAppointmentRepo**:

     - Extends `JpaRepository` for the `Appointment` entity. It allows for data access operations related to appointments.

   - **IDoctorRepo**:

     - Extends `JpaRepository` for the `Doctor` entity. It enables data access related to doctors.

   - **IPatientRepo**:

     - Extends `JpaRepository` for the `Patient` entity, allowing data access operations related to patients.

   - **ITokenRepo**:
     - Extends `JpaRepository` for the `AuthenticationToken` entity. It facilitates data access for authentication tokens.

### ArrayLists

ArrayLists are used in your application to efficiently manage lists of entities. Here's how ArrayLists are used in the context of your entities:

- **Doctor Entity**:

  - `appointments`: An ArrayList to manage the list of `Appointment` entities associated with a doctor. It allows for easy retrieval and manipulation of a doctor's appointments.

- **Patient Entity**:

  - There isn't a direct ArrayList in the `Patient` entity, but the patient's appointments are managed using a reference to the `Appointment` entity.

- **Appointment Entity**:

  - The `Appointment` entity itself doesn't contain an ArrayList, but it's utilized to establish relationships between doctors and patients. The list of appointments for a doctor can be managed using a collection of `Appointment` entities in the `Doctor` entity.

- **AuthenticationToken Entity**:
  - There isn't an ArrayList in the `AuthenticationToken` entity, but it's associated with a patient using a reference. The token and associated data are stored as individual records in the database.

configuration for MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/helathcareappointment
spring.datasource.username=root
spring.datasource.password=`your password`
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

```
