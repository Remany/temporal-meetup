package ru.aston.orchestrator.workflow.impl;

import io.temporal.failure.ActivityFailure;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import ru.aston.orchestrator.activity.CustomerService;
import ru.aston.orchestrator.activity.NotificationService;
import ru.aston.orchestrator.activity.OrderService;
import ru.aston.orchestrator.activity.PaymentService;
import ru.aston.orchestrator.activity.WarehouseService;
import ru.aston.orchestrator.dto.OrderRequest;
import ru.aston.orchestrator.workflow.ProcessOrderWorkflow;
import ru.aston.orchestrator.workflow.WorkflowUtil;

@WorkflowImpl(taskQueues = "OrchestratorTaskQueue")
public class ProcessOrderWorkflowImpl implements ProcessOrderWorkflow {

    // temporal logger
    private static final Logger log = Workflow.getLogger(ProcessOrderWorkflowImpl.class);

    // activity options
    private final OrderService orderService = Workflow.newActivityStub(OrderService.class, WorkflowUtil.orderOptions());

    private final WarehouseService warehouseService = Workflow.newActivityStub(WarehouseService.class, WorkflowUtil.warehouseOptions());

    private final PaymentService paymentService = Workflow.newActivityStub(PaymentService.class, WorkflowUtil.paymentOptions());

    private final NotificationService notificationService = Workflow.newActivityStub(NotificationService.class, WorkflowUtil.notificationOptions());

    private final CustomerService customerService = Workflow.newActivityStub(CustomerService.class, WorkflowUtil.customerOptions());

    // saga options
    private final Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();

    @Override
    public String processOrder(OrderRequest req) {
        Saga saga = new Saga(sagaOptions);
        try {
            log.info("Обработка платежа");
            var customerBalance = customerService.checkBalance(req.getCustomerId());
            saga.addCompensation(() -> customerService.updateCustomerBalance(req.getCustomerId(), customerBalance));
            var newBalance = paymentService.processPayment(customerBalance, req.getPrice());
            customerService.updateCustomerBalance(req.getCustomerId(), newBalance);

            log.info("Создание заказа");
            var orderId = orderService.createOrder(req);

            log.info("Проверка наличия на складе");
            saga.addCompensation(() -> orderService.cancelOrder(orderId));
            warehouseService.stockCheck(req);

            log.info("Оповещение клиента");
            notificationService.notifyCustomer(req.getCustomerId());
            return orderId;
        } catch (ActivityFailure e) {
            log.error("Ошибка в процессе обработки заказа, выполняем компенсации", e);
            Workflow.newDetachedCancellationScope(saga::compensate).run();
            throw e;
        }
    }
}
