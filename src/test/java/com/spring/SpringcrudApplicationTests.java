package com.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.entity.Department;
import com.spring.repository.DepartmentRepository;
import com.spring.service.DepartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest  // put this if you wanna spin up full spring container.
@TestMethodOrder(OrderAnnotation.class)  // if we have multiple classes then follow we need to specify test order
class SpringcrudApplicationTests {
	
	@Autowired
	private DepartmentService service;
	
	@Autowired
	private DepartmentRepository repository;
	
	// service has 4 methods defined by us. So lets use this 

    @Test
    @Disabled // this test will not run
	public void fetchDepartmentListTest() {
		when(repository.findAll()).thenReturn(Stream.of(new Department("ETC","C-Block","EC-02"), new Department("ETC","C-Block","EC-02")).collect(Collectors.toList()));
		assertEquals(2,service.fetchDepartmentList().size());
	}
    
    //test create data
    @Test
    @Order(1)
    public void saveDepartmentTest() {
    	Long id = (long) 54;
    	Department dpt = new Department();
    	dpt.setDepartmentId(id);
    	dpt.setDepartmentAddress("21/A");
    	dpt.setDepartmentCode("1121");
    	dpt.setDepartmentName("Vijay");
    	
    	service.saveDepartment(dpt);
    	
    	assertNotNull(repository.findById(id).get());
    }
    
    //test read all records
    @Test
    @Order(2)
    public void fetchDepartmentListTest1() {
    	List<Department> deptlist = service.fetchDepartmentList();
    	assertThat(deptlist).size().isGreaterThan(0);
    	
    }
    
    //test read single record.
    @Test
    @Order(4)
    public void fetchingSingleRecordNameTest() {
    	Long id = (long) 54;
    	Department dept = repository.findById(id).get();
    	assertEquals("Vijay",dept.getDepartmentName());
    	
    }
    
    @Test
    @Order(3)
    public void fetchingSingleRecordAddressTest() {
    	Long id = (long) 54;
    	Department dept = repository.findById(id).get();
    	assertEquals("21/A",dept.getDepartmentAddress());
    	
    }
    
    @Test
    @Order(5)
    public void fetchingSingleRecordCodeTest() {
    	Long id = (long) 54;
    	Department dept = repository.findById(id).get();
    	assertEquals("1121",dept.getDepartmentCode());
    	
    }
    
    // test for update operation
    @Test
    @Order(6)
    public void updateDepartmentTest() {
    	Long id = (long) 54;
    	
    	Department dpt =repository.findById(id).get() ;
    	
    	dpt.setDepartmentId(id);
    	dpt.setDepartmentAddress("21/A-ipdate");
    	dpt.setDepartmentCode("1121-update");
    	dpt.setDepartmentName("Vijay-update");
    	
    	service.updateDepartment(dpt, id);
    	 assertNotEquals("Vijay",repository.findById(id).get().getDepartmentName());
    }
    
    // Test for delete operation
    @Test
    @Order(7)
    public void deleteDepartmentById() {
    	Long id = (long) 54;
    	service.deleteDepartmentById(id);
    	assertThat(repository.existsById(id)).isFalse();
    	
    }

}
