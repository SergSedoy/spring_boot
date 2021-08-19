package com.example.spring_boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
	
    private static GenericContainer<?> devapp = new GenericContainer<>("devapp");
    private static GenericContainer<?> prodapp = new GenericContainer<>("prodapp"); 

    @BeforeAll
    public static void setUp() {
        devapp.start();
	prodapp.start();
	    
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080), String.class);
        System.out.println(forEntityDev.getBody());
	
	ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081), String.class);
        System.out.println(forEntityProd.getBody());
	
	    
	assertEquals("Current profile is dev", forEntityDev.getBody());
	assertEquals("Current profile is production", forEntityProd.getBody());
    }

}
