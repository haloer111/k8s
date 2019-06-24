cd /d c:/thrift
thrift-0.12.0.exe --gen java D:\developer\IdeaProjects\k8s-ms\message-thrift-service-api\thrift\message.thrift

xcopy  /s /h /y gen-java\*.* D:\developer\IdeaProjects\k8s-ms\message-thrift-service-api\src\main\java\

rd /s /q gen-java\

exit