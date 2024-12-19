package com.bank.marketing.notification_service.controller;

import com.bank.marketing.notification_service.model.Notification;
import com.bank.marketing.notification_service.model.NotificationRequest;
import com.bank.marketing.notification_service.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationControllerTests {

    private MockMvc mockMvc;

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void testCreateNotification() throws Exception {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Test message");

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        mockMvc.perform(post("/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\": \"Test message\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"message\":\"Test message\"}"));
    }

    @Test
    public void testGetNotifications() throws Exception {
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setMessage("Test message 1");

        Notification notification2 = new Notification();
        notification2.setId(2L);
        notification2.setMessage("Test message 2");

        when(notificationRepository.findAll()).thenReturn(Arrays.asList(notification1, notification2));

        mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"message\":\"Test message 1\"},{\"id\":2,\"message\":\"Test message 2\"}]"));
    }

    @Test
    public void testSendNotification() throws Exception {
        NotificationRequest request = new NotificationRequest();
        request.setRecipient("test@example.com");
        request.setMessage("Test message");

        mockMvc.perform(post("/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipient\": \"test@example.com\", \"message\": \"Test message\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Notification sent to:test@example.com"));
    }
}