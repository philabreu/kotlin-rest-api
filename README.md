# Preset:
1.Install mysql with username / password = root (check in application.yml file)


# Run as standalone application:
2.execute commands

	mvn clean install (only the first time)
	mvn spring-boot: run

# Run with jenkins:
3.Install plugin cobertura
4.Create job project maven, with code management in github, with goal maven cobertura:cobertura
