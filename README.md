# pinet-server
http://pinet-server.org 

### To compile and run
gradle clean build 
 
java -jar build/libs/*.jar 


### Three main comments:
##### 1- For deepPhos prediction, pinet is listening to port 5000
The file to change that is in /src/main/resources/application.yml

  deepPhosUrl: http://localhost:5000/api/predict/organism/%s/%s

##### 2- pinet reads indexed protein files from this folder:
 
/opt/raid10/genomics/behrouz/PeptideMatchCMD_src_1.0/jan-30-2019/

To change that, whatever is in this folder should be copied to the new folder and the following file should be changed as well:

/src/main/java/edu.uc.eh/peptideMatch/PeptideMatchCMD.java


##### 3- The usage submission increment file is in the /src/main/resources/increment/increment.json 