cd /d c:/thrift
thrift-0.12.0.exe --gen java D:\developer\IdeaProjects\k8s-ms\user-thrift-service-api\thrift\user-service.thrift

xcopy  /s /h /y gen-java\*.* D:\developer\IdeaProjects\k8s-ms\user-thrift-service-api\src\main\java\

rd /s /q gen-java\

exit