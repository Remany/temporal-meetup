package ru.aston.orchestrator.controller;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.orchestrator.dto.OrderRequest;
import ru.aston.orchestrator.workflow.ProcessOrderWorkflow;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final WorkflowClient client;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody OrderRequest request) {
        ProcessOrderWorkflow workflow =
                client.newWorkflowStub(
                        ProcessOrderWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("OrchestratorTaskQueue")
                                .setWorkflowId("CreateOrder")
                                .build());
        var orderId = workflow.processOrder(request);
        return ResponseEntity.ok("Order created, id: " + orderId);
    }
}
