package packt.java11.mybusiness.book;

import packt.java11.mybusiness.product.Order;

import java.util.concurrent.Flow;

public class InvoiceCalculator implements Flow.Subscriber<Order> {
    private Flow.Subscription subscription = null;
    private static final long INFINITE = Long.MAX_VALUE;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(INFINITE);
    }

    @Override
    public void onNext(Order item) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
