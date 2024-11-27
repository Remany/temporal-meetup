package ru.aston.orchestrator.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import ru.aston.orchestrator.dto.OrderRequest;

@WorkflowInterface
public interface ProcessOrderWorkflow {
    @WorkflowMethod
    String processOrder(OrderRequest req);
}
