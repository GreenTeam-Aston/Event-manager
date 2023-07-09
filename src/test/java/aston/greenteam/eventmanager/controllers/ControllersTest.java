package aston.greenteam.eventmanager.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControllersTest {

    @Autowired
    AuthController authController;

    @Autowired
    BucketController bucketController;

    @Autowired
    EmailController emailController;

    @Autowired
    EventCategoryController eventCategoryController;

    @Autowired
    EventController eventController;

    @Autowired
    UserController userController;

    @Autowired
    ParameterController parameterController;

    @Autowired
    ProductController productController;

    @Autowired
    ValueController valueController;

    @Test
    public void checkIfAuthControllerNotNull() throws Exception {
        assertThat(authController).isNotNull();
    }

    @Test
    public void checkIfBucketControllerNotNull() throws Exception {
        assertThat(bucketController).isNotNull();
    }

    @Test
    public void checkIfEmailControllerNotNull() throws Exception {
        assertThat(emailController).isNotNull();
    }

    @Test
    public void checkIfEventCategoryControllerNotNull() throws Exception {
        assertThat(eventCategoryController).isNotNull();
    }

    @Test
    public void checkIfEventControllerNotNull() throws Exception {
        assertThat(eventController).isNotNull();
    }

    @Test
    public void checkIfUserControllerNotNull() throws Exception {
        assertThat(eventController).isNotNull();
    }

    @Test
    public void checkIfParameterControllerNotNull() throws Exception {
        assertThat(parameterController).isNotNull();
    }

    @Test
    public void checkIfProductControllerNotNull() throws Exception {
        assertThat(productController).isNotNull();
    }

    @Test
    public void checkIfValueControllerNotNull() throws Exception {
        assertThat(valueController).isNotNull();
    }
}
