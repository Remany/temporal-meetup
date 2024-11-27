package ru.aston.orchestrator.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;

import java.time.Duration;

public class WorkflowUtil {

    private static final String ORDER_QUEUE = "OrderTaskQueue";

    private static final String PAYMENT_QUEUE = "PaymentTaskQueue";

    private static final String NOTIFICATION_QUEUE = "NotificationTaskQueue";

    private static final String WAREHOUSE_QUEUE = "WarehouseTaskQueue";

    private static final String CUSTOMER_QUEUE = "CustomerTaskQueue";

    public static RetryOptions retryOptions() {
        return RetryOptions.newBuilder()
                .setMaximumAttempts(3)
                .setInitialInterval(Duration.ofSeconds(1))
                .setBackoffCoefficient(2.0)
                .setMaximumInterval(Duration.ofSeconds(10))
                .build();
    }

    public static ActivityOptions orderOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(2))
                .setTaskQueue(ORDER_QUEUE)
                .setRetryOptions(retryOptions())
                .build();
    }

    public static ActivityOptions paymentOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(2))
                .setTaskQueue(PAYMENT_QUEUE)
                .setRetryOptions(retryOptions())
                .build();
    }

    public static ActivityOptions notificationOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(2))
                .setTaskQueue(NOTIFICATION_QUEUE)
                .setRetryOptions(retryOptions())
                .build();
    }

    public static ActivityOptions warehouseOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(2))
                .setTaskQueue(WAREHOUSE_QUEUE)
                .setRetryOptions(retryOptions())
                .build();
    }

    public static ActivityOptions customerOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(2))
                .setTaskQueue(CUSTOMER_QUEUE)
                .setRetryOptions(retryOptions())
                .build();
    }
}
