call java -Dwebdriver.ie.driver="D:\autotest\autotest\autotest\IEDriverServer.exe" -Dwebdriver.chrome.driver="D:\autotest\autotest\autotest\chromedriver.exe" -jar selenium-server-standalone-3.0.1.jar  -role node  -hub http://localhost:4444/grid/register  -maxSession 10 -browser browserName=firefox,version=3.6,maxInstances=5,platform=VISTA -browser "browserName=internet explorer,version=11,setjavascriptEnabled=true,maxInstances=5,acceptSslCerts=true,platform=WINDOWS" -browser "browserName=chrome,maxInstances=5,setjavascriptEnabled=true,acceptSslCerts=true,platform=WINDOWS" 