
# Clinic-Spring


Spring application to health care records control developed in <code>Java</code>,
with authentication and authorization system.

<h4 align="center"> 
	🚧 Under construction... 🚧
</h4>

### Prerequisites

Before starting, you will need to have the following tools installed on your machine:
[Git](https://git-scm.com) and [Java 11](https://www.oracle.com/us/java/technologies/javase/jdk11-archive-downloads.html).
Also it's nice to have an editor to work with the code like [VSCode](https://code.visualstudio.com/).

### 🎲 Running the Project

```bash
# Clone this repository
$ git clone https://github.com/MarceloAlvaroVieira/Clinic-Spring

# Access the project folder in terminal/cmd
$ cd Clinic-Spring

# Set the value of the environment variables referring to the database
$ DB=Foo      		# Link
$ DB_USER=Foo		# User
$ PASSWORD=Foo 		# Password
$ DIALECT=Foo 		# Dialect

# Also define those referring to encryption and session duration
$ SECRET=Foo 		# Encryption key
$ EXPIRATION=Foo 	# Session duration

# Default new users password
$ DEFAULT_PASSWORD=Foo # Password must be reset after login

# Export the environment variables
$ export DB;
$ export DB_USER;
$ export PASSWORD;
$ export DIALECT;
$ export SECRET;
$ export EXPIRATION;
$ export DEFAULT_PASSWORD;


# Open the ClinicApplication.java file

# Run the application in development mode

# The server will start on port 8080 - go to <http://localhost:8080>
```


### 🛠 Technologies

The following tools were used in building the project:

- [Spring Boot](https://spring.io/)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Sessions](https://docs.spring.io/spring-session/reference/index.html)
- [Spring Devtools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
- [Liquibase](https://www.liquibase.org/)
- [JSON Web Token](https://jwt.io/)
- [Lombok](https://projectlombok.org)