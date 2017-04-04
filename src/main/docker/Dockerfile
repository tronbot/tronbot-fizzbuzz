FROM java:openjdk-8-jre-alpine

ADD /* /tmp/

VOLUME /tmp/
CMD echo "Start building tronbot-fizzbuzz app" && \
    gradlew clean build test -i