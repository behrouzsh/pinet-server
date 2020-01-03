package edu.uc.eh;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import edu.uc.eh.uniprot.UniprotRepositoryH2;


@SpringBootApplication
public class Application {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    UniprotRepositoryH2 repository;
    private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        logger.info("Student id 10001 -> {}", repository.findById(10001L));
//
//        logger.info("Inserting -> {}", repository.insert(new Uniprot(10010L, "John", "A1234657")));
//
//        logger.info("Inserting -> {}", repository.insert(new Uniprot(10011L, "John2", "A1234658")));
//
//        logger.info("Update 10003 -> {}", repository.update(new Uniprot(10001L, "Name-Updated", "New-Passport")));
//
//        repository.deleteById(10002L);
//
//        logger.info("All users -> {}", repository.findAll());
//    }
    //Tomcat large file upload connection reset
    //http://www.mkyong.com/spring/spring-file-upload-and-connection-reset-issue/
//    @Bean
//    public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
//
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//
//        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
//            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
//                //-1 means unlimited
//                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
//            }
//        });
//
//        return tomcat;
//
//    }
}
