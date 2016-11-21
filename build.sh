#!/bin/bash
mvn clean package
mv ./target/bus-route-problem*.jar ./target/service.jar