package com.cne.spring.security.controller;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-security.xml")
public class HelloControllerTest {

    private static TJWSEmbeddedJaxrsServer server;
    
        
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        server = new TJWSEmbeddedJaxrsServer();
        server.setPort(8081);
        server.setRootResourcePath("/com.cne.spring.security");
        server.start();
        server.getDeployment().getDispatcher().getRegistry().addSingletonResource(new HelloController());
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        assertNotNull(server);
        
        server.stop();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void test() {
        try {
            assertNotNull(server);
            
            Client client = ClientBuilder.newClient();
            
            Response response = client.target("http://localhost:8081/com.cne.spring.security")
                    .request().header("Content-Type", MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON).get();
            
            System.out.println("\nResponse Payload>>> " + response.readEntity(String.class));
            System.out.println();
            
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
