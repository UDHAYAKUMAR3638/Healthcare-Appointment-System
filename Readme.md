# <h1 align="center"> Health Care Appointment System </h1>

---

<p align="left">

## Overview

The Health Care Appointment System is a Spring Boot-based system designed to facilitate doctor-patient appointments. It offers a set of RESTful API endpoints for booking and managing appointments, user authentication, doctor and patient details .

## Project Structure

The project follows a structured and organized architecture:

- **Entities:** This includes entities such as `Doctor`, `Patient`, `Appointment`, `Receptionist`, `User`and `Token` to model the data.
- **Repository:** This includes repository such as `AppointmentRepo`, `PatientRepo`, `DoctorRepo`, `ReceptionistRepo`, `UserRepo` and `TokenRepo` to access the entity data.
- **Services:** logic is implemented in service classes such as `AppointmentService`, `PatientService`, `DoctorService`, `ReceptionistService` and `UserService`.
- **Controller Classes:** These classes define and document RESTful API endpoints includes `DoctorController`, `PatientController`, `ReceptionistController` and `UserController`.

## User Creation

- **Create User:** `POST /api/auth/register`
- **Authenticate User:** `POST /api/auth/authenticate`

## Patient Controller

- **Get Patient:** `GET /patient/{Id}`
- **Update Patient:** `PUT /patient/update/{Id}`
- **Get All Patient:** `GET /patient/getAll`
- **Delete Patient:** `DELETE /patient/delete/{Id}`

### Appointment Controller

- **Book Appointment:** `POST /api/appointment/book`
- **Cancel Appointment:** `DELETE /api/appointment/cancel/{appointmentId}`
- **Update Appointment By AppointmentId:** `PUT /api/appointment/updateById/{appointmentId}`
- **Update Appointment By DoctorId:** `PUT /api/appointment/updateByDoctorId/{doctorId}`
- **Update Appointment By PatientId:** `PUT /api/appointment/updateByPatientId/{patientId}`
- **View Patient Appointment:** `GET /appointment/patientAppointment/{patientId}`
- **View Doctor Appointment:** `GET /appointment/doctorAppointment/{doctorId}`
- **View All Appointment:** `GET /appointment/getAll`

### Doctor Profiles

- **Get Doctor:** `GET /doctor/{DoctorId}`
- **Update Doctor:** `PUT /doctor/update/{DoctorId}`
- **Delete Doctor:** `DELETE /doctor/delete/{DoctorId}`
- **Get Doctor Appointments:** `GET /doctor/appointmentDetails/{doctorId}`

The API endpoints are documented, adhering to REST principles, and provide the core features of the Doctor's Appointment Application.

## Database Design

The application uses a relational database to store data, including doctor and patient information, appointments, and authentication tokens. Key attributes and tables include:

### Doctor Table

| Column Name    | Data Type    | Description                   |
| -------------- | ------------ | ----------------------------- |
| doctorId       | INT          | Unique identifier for doctors |
| doctorName     | VARCHAR(255) | Name of the doctor            |
| specialization | VARCHAR(255) | Doctor's specialization       |

### Doctor Table Description

- `doctorId`: Unique identifier for each doctor.
- `doctorName`: Name of the doctor.
- `specialization`: The doctor's specialization.
- `appointments`: A list of appointments associated with the doctor.

### Patient Table

| Column Name      | Data Type    | Description                         |
| ---------------- | ------------ | ----------------------------------- |
| patientId        | INT          | Unique identifier for patients      |
| patientFirstName | VARCHAR(255) | First name of the patient           |
| patientLastName  | VARCHAR(255) | Last name of the patient            |
| patientEmail     | VARCHAR(255) | Email address of the patient        |
| patientPassword  | VARCHAR(255) | Encrypted password of the patient   |
| patientContact   | VARCHAR(20)  | Contact information for the patient |

### Patient Table Description

- `patientId`: Unique identifier for each patient.
- `patientFirstName`: First name of the patient.
- `patientLastName`: Last name of the patient.
- `patientEmail`: Unique email address for the patient.
- `patientPassword`: Encrypted password for authentication.
- `patientContact`: Contact information for the patient.
- `appointment`: The patient's appointment.

### Appointment Table

| Column Name   | Data Type | Description                        |
| ------------- | --------- | ---------------------------------- |
| appointmentId | INT       | Unique identifier for appointments |
| time          | DATETIME  | Date and time of the appointment   |
| doctor_doc_id | INT       | Foreign key referencing the doctor |

### Appointment Table Description

- `id`: Embedded primary key consisting of appointmentId and time.
- `doctor`: A many-to-one relationship with the doctor.
- `patient`: A one-to-one relationship with the patient.

### AuthenticationToken Table

| Column Name       | Data Type    | Description                         |
| ----------------- | ------------ | ----------------------------------- |
| tokenId           | INT          | Unique identifier for tokens        |
| token             | VARCHAR(255) | Authentication token value          |
| tokenCreationDate | DATE         | Date of token creation              |
| fk_patient_ID     | INT          | Foreign key referencing the patient |

### Authentication Token Table Description

- `tokenId`: Unique identifier for each token.
- `token`: A unique token for user authentication.
- `tokenCreationDate`: The date when the token was created.
- `patient`: A one-to-one relationship with the patient.

## Data Structures Used

1. **Entities**:

   - **Doctor**: Represents a doctor with attributes like `doctorId`, `doctorName`, `specialization`, and a list of `appointments`.

   - **Patient**: Represents a patient with attributes like `patientId`, `patientFirstName`, `patientLastName`, `patientEmail`, `patientPassword`, `patientContact`, and an `appointment`.

   - **Appointment**: Represents an appointment between a doctor and a patient. It contains an embedded primary key `id`, a reference to the associated `doctor`, and a reference to the `patient`.

   - **Authentication Token**: Represents an authentication token with attributes like `tokenId`, `token`, `tokenCreationDate`, and a reference to the associated `patient`.

2. **Repositories**:
   - JPA repositories for data access, including repositories for doctors, patients, appointments, and authentication tokens.

In your Doctor's Appointment Application, you have various data structures, including entities and repositories. Additionally, ArrayLists are utilized for efficiently managing lists of entities. Let's delve into the detailed data structures used, with a specific focus on ArrayLists:

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

> ArrayLists, as dynamic lists, provide flexibility for storing and managing multiple entities efficiently within your application.

> The actual storage and retrieval of data are typically handled by the JPA repositories and underlying database systems.

> The use of data structures like entities, repositories, and authentication tokens ensures efficient data management and data integrity within the application.

## Database Configuration

The database connection properties, including the URL, username, and password, are specified in the `application.properties` file. Ensure that these properties are correctly configured to connect to your MySQL database.

Example configuration for MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/HospitalManagement
spring.datasource.username=root
spring.datasource.password=9892321787@As
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

Please replace `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` with your database connection details.

## Project Summary

The Doctor's Appointment Application is a Spring Boot-based system that simplifies doctor-patient appointment management. It provides RESTful API endpoints for booking and canceling appointments, user authentication, and doctor profiles.

The application is built on a solid foundation, utilizing Spring Boot and MySQL for data storage, and it follows best practices for clean code, separation of concerns, and secure user data handling.
