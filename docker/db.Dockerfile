# Using latest mysql docker image
FROM mysql:latest

# Environment variables to configure MySQL
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=intuit-db

# Expose the MySQL port
EXPOSE 3306

# Setting the character set and collation
ENV MYSQL_CHARSET=utf8mb4
ENV MYSQL_COLLATION=utf8mb4_unicode_ci

# Start MySQL
CMD ["mysqld"]

