FROM openjdk:11
EXPOSE 8585
ADD build/libs/Movie-Booking-0.0.1-SNAPSHOT.jar Movie-Booking.jar
ENTRYPOINT ["java","-jar","/Movie-Booking.jar"]

