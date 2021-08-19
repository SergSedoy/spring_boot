package com.example.spring_boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
	
    private static GenericContainer<?> app = new GenericContainer<>("app:1.0");
    private static GenericContainer<?> appLatest = new GenericContainer<>("app:latest"); 

    @BeforeAll
    public static void setUp() {
        app.start();
	appLatest.start();
	    
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + app.getMappedPort(8080), String.class);
        System.out.println(forEntity.getBody());
	
	    
	assertEquals("Current profile is dev", forEntity.getBody());
    }

}
