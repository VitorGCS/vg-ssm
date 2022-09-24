package guru.springframework.vgssm.services;

import guru.springframework.vgssm.domain.Payment;
import guru.springframework.vgssm.domain.PaymentEvent;
import guru.springframework.vgssm.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

public interface PaymentService {
    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long payementId);
    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long payementId);
    StateMachine<PaymentState, PaymentEvent> declineAuth(Long payementId);

}
