# Help Documentation for Company User Token Top-Up Processor

This Spring Boot project processes user and company data from JSON files to generate an output file recording token allocations and email statuses.

## Project Overview

The main objective of this application is to read user and company data from JSON files, process the data to allocate tokens based on specified conditions, and generate an output file.

### Files and Directories
- **Input Files**:
    - `users.json`: Contains user details.
    - `companies.json`: Contains company details.

- **Generated Output File**:
    - `output.txt`: File generated in the project directory after processing.

### Processing Criteria

- **Token Top-Up**: Active users receive a token increase specified in their company’s `top_up` field.
- **Email Status**:
    - If the company’s `email_status` is `true` and the user’s `email_status` is `true`, the user is marked as "emailed."
    - Users with `email_status` set to `false` are not marked as "emailed," regardless of the company’s email status.

- **Ordering**:
    - Companies are ordered by `company_id`.
    - Users are ordered alphabetically by `last_name`.

### Additional Information

- **Error Handling**: The application handles potentially malformed data and file read/write errors.
- **Code Structure**:
    - `Challenge.java`: Core file handling token allocation, sorting, and output generation.
    - `FileManager.java`: Manages reading from and writing to JSON and text files.
    - **Custom Exceptions**: Handle file read/write issues.

## Requirements

- **Java Version**: Java 11+
- **Build Tool**: Maven
- **Dependencies**:
    - Spring Boot (Core dependencies)
    - Jackson (for JSON parsing)

## Running the Application in IntelliJ

1. **Open the Project**:
    - In IntelliJ, select **File > Open** and navigate to the project directory.

2. **Install Dependencies**:
    - Maven dependencies should be imported automatically. If not, right-click the `pom.xml` file and select **Maven > Reload Project**.

3. **Run the Application**:
    - Run the Spring Boot main class (`ProcessorApplication.java`) to start the application.
    - The program will process the JSON files and generate `output.txt` in the specified directory.

## Sample Commands for Running from Command Line

If running directly from the command line, navigate to the project root and use:

```bash
./mvnw spring-boot:run
